package servlets;

import Configuracion.Configuracion;
import servicios.DtAlbum;
import servicios.DtAlbumContenido;
import servicios.DtArtista;
import servicios.DtCliente;
import servicios.DtTema;
import servicios.DtLista;
import servicios.DtSuscripcion;
import servicios.DtUsuario;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.DtListaAlbum;
import servicios.DtListaDeListas;
import servicios.PFavorito;
import servicios.PFavoritoService;

@WebServlet(name = "SFavorito", urlPatterns = {"/SFavorito"})
public class SFavorito extends HttpServlet {

    private PFavorito port;

    public SFavorito() {
        Configuracion.cargar();
        cargar();
    }

    private void cargar() {
        try {
            PFavoritoService ws = new PFavoritoService(new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PFavorito")));
            port = ws.getPFavoritoPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSeguir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }
        
        request.setAttribute("mensaje_error", "Ups, usted no deberia estar aqui   <span class=\"glyphicon glyphicon-eye-close\"></span>");
        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }
        
        if (request.getSession().getAttribute("usuario") == null) {
            request.setAttribute("mensaje_error", "No se pudo completar la tarea, no tiene permisos para entrar aqui");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        DtUsuario dtu = (DtUsuario) request.getSession().getAttribute("usuario");

        if (dtu instanceof DtArtista) {
            request.setAttribute("mensaje_error", "Debe ser un cliente");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        DtCliente dtc = (DtCliente) dtu;

        if (request.getSession().getAttribute("suscripcion") == null || !((DtSuscripcion) request.getSession().getAttribute("suscripcion")).getEstado().equals("Vigente")) {
            request.setAttribute("mensaje_error", "Debe tener suscripcion vigente");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("accion") == null || request.getParameter("objeto") == null) {
            request.setAttribute("mensaje_error", "No hay accion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        if (port.getDataUsuario(dtc.getNickname()) == null) {
            request.setAttribute("mensaje_error", "Usuario no existe");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String nickCliente = dtc.getNickname();
        String objeto = request.getParameter("objeto");
        String accion = request.getParameter("accion");

        String nomLista = "";
        String nickArtista = "";
        String nomAlbum = "";
        String mensaje = "";

        switch (objeto) {
            case "listaDefecto":
                if (request.getParameter("nomGenero") == null || request.getParameter("nomLista") == null) {
                    request.setAttribute("mensaje_error", "Faltan parametros");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                String nomGenero = request.getParameter("nomGenero");
                nomLista = URLDecoder.decode(request.getParameter("nomLista"), "UTF-8");

                boolean existeLista = false;
                DtListaDeListas listas = port.listarLisReproduccionGen(nomGenero);
                for (DtLista lista : listas.getListas()) {
                    if (lista.getNombre().equals(nomLista)) {
                        existeLista = true;
                        break;
                    }
                }

                if (!existeLista) {
                    request.setAttribute("mensaje_error", "La lista no existe");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                mensaje = "";
                switch (accion) {
                    case "agregar":
                        port.agregarLDFav(nickCliente, nomGenero, nomLista);
                        mensaje = ("Lista '" + nomLista + "' agregada a favoritos!").replaceAll(" ", "%20");
                        break;
                    case "quitar":
                        port.quitarLDFav(nickCliente, nomGenero, nomLista);
                        mensaje = ("Lista '" + nomLista + "' quidata de favoritos!").replaceAll(" ", "%20");
                        break;
                    default:
                        request.setAttribute("mensaje_error", "Accion desconocida");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                }

                request.getRequestDispatcher("/SInicio?mensaje=" + mensaje).forward(request, response);

                break;
            case "listaParticular":
                if (request.getParameter("nickDuenio") == null || request.getParameter("nomLista") == null) {
                    request.setAttribute("mensaje_error", "Faltan parametros");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                String nickDuenio = request.getParameter("nickDuenio");
                nomLista = URLDecoder.decode(request.getParameter("nomLista"), "UTF-8");

                boolean existeListaP = false;
                DtListaDeListas listasP = port.listarLisReproduccion(nickCliente);
                for (DtLista lista : listasP.getListas()) {
                    if (lista.getNombre().equals(nomLista)) {
                        existeListaP = true;
                        break;
                    }
                }

                if (!existeListaP) {
                    request.setAttribute("mensaje_error", "La lista no existe");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                mensaje = "";
                switch (accion) {
                    case "agregar":
                        port.agregarLPFav(nickCliente, nickDuenio, nomLista);
                        mensaje = ("Lista '" + nomLista + "' agregada a favoritos!").replaceAll(" ", "%20");
                        break;
                    case "quitar":
                        port.quitarLPFav(nickCliente, nickCliente, nomLista);
                        mensaje = ("Lista '" + nomLista + "' quidata de favoritos!").replaceAll(" ", "%20");
                        break;
                    default:
                        request.setAttribute("mensaje_error", "Accion desconocida");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                }

                request.getRequestDispatcher("/SInicio?mensaje=" + mensaje).forward(request, response);

                break;
            case "album":
                if (request.getParameter("nickArtista") == null || request.getParameter("nomAlbum") == null) {
                    request.setAttribute("mensaje_error", "Faltan parametros");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                nickArtista = request.getParameter("nickArtista");
                nomAlbum = URLDecoder.decode(request.getParameter("nomAlbum"), "UTF-8");

                boolean existeAlbum = false;
                DtListaAlbum albumes = port.listarAlbumesArtista(nickArtista);
                for (DtAlbum album : albumes.getAlbum()) {
                    if (album.getNombre().equals(nomAlbum)) {
                        existeAlbum = true;
                        break;
                    }
                }

                if (!existeAlbum) {
                    request.setAttribute("mensaje_error", "El album no existe");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                mensaje = "";
                switch (accion) {
                    case "agregar":
                        port.agregarAlbumFav(nickCliente, nickArtista, nomAlbum);
                        mensaje = ("Album '" + nomAlbum + "' agregado a favoritos!").replaceAll(" ", "%20");
                        break;
                    case "quitar":
                        port.quitarAlbumFav(nickCliente, nickArtista, nomAlbum);
                        mensaje = ("Album '" + nomAlbum + "' quitado de favoritos!").replaceAll(" ", "%20");
                        break;
                    default:
                        request.setAttribute("mensaje_error", "Accion desconocida");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                }

                request.getRequestDispatcher("/SInicio?mensaje=" + mensaje).forward(request, response);

                break;
            case "tema":
                if (request.getParameter("nickArtista") == null || request.getParameter("nomAlbum") == null || request.getParameter("nomTema") == null) {
                    request.setAttribute("mensaje_error", "Faltan parametros");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                nickArtista = request.getParameter("nickArtista");
                nomAlbum = URLDecoder.decode(request.getParameter("nomAlbum"), "UTF-8");
                String nomTema = request.getParameter("nomTema");

                boolean existeTema = false;
                DtListaAlbum albumesA = port.listarAlbumesArtista(nickArtista);
                loop:
                for (DtAlbum album : albumesA.getAlbum()) {
                    if (album.getNombre().equals(nomAlbum)) {
                        DtAlbumContenido albumCont = port.obtenerAlbumContenido(nickArtista, nomAlbum);
                        for (DtTema tema : albumCont.getTemas()) {
                            if (tema.getNombre().equals(nomTema)) {
                                existeListaP = true;
                                break loop;
                            }
                        }
                    }
                }

                if (!existeTema) {
                    request.setAttribute("mensaje_error", "El tema no existe");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                mensaje = "";
                switch (accion) {
                    case "agregar":
                        port.agregarTemaFav(nickCliente, nickArtista, nomAlbum, nomTema);
                        mensaje = ("Tema '" + nomTema + "' agregado a favoritos!").replaceAll(" ", "%20");
                        break;
                    case "quitar":
                        port.quitarTemaFav(nickCliente, nickArtista, nomAlbum, nomTema);
                        mensaje = ("Tema '" + nomTema + "' quitado de favoritos!").replaceAll(" ", "%20");
                        break;
                    default:
                        request.setAttribute("mensaje_error", "Accion desconocida");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                }

                if (request.getParameter("redirigir") != null) {
                    request.getRequestDispatcher(request.getParameter("redirigir")).forward(request, response);
                } else {
                    request.getRequestDispatcher("/SInicio?mensaje=" + mensaje).forward(request, response);
                }
                break;
            default:
                request.setAttribute("mensaje_error", "Objeto desconocido");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
