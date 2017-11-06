package servlets;

import Configuracion.Configuracion;
import servicios.DtAlbumContenido;
import servicios.DtCliente;
import servicios.DtLista;
import servicios.DtUsuario;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.DtListaParticular;
import servicios.DtListaString;
import servicios.DtTema;
import servicios.DtTime;
import servicios.PContenido;
import servicios.PContenidoService;
import servicios.DtListaTema;
import servicios.DtTemaLocal;
import servicios.DtTemaRemoto;

@WebServlet(name = "SContenido", urlPatterns = {"/SContenido"})
public class SContenido extends HttpServlet {

    PContenido port;

    public SContenido() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PContenido"));
            PContenidoService webserv = new PContenidoService(url);
            port = webserv.getPContenidoPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SContenido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("accion") == null) {
            request.setAttribute("mensaje_error", "Falta una accion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
        } else {
            String accion = request.getParameter("accion");

            switch (accion) {

                case "AltaAlbum":
                    ArrayList<String> generos = (ArrayList<String>) port.obtenerGeneros().getString();
                    request.setAttribute("Generos", generos);
                    request.getRequestDispatcher("/vistas/AltaAlbum.jsp").forward(request, response);
                    break;

                case "consultarGenero":
                    if (request.getParameter("genero") == null) {
                        request.setAttribute("mensaje_error", "Faltan parámetros");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    } else {
                        String genero = request.getParameter("genero");
                        if (port.existeGenero(genero)) {
                            if (request.getSession().getAttribute("usuario") != null) {
                                DtUsuario dtu = (DtUsuario) request.getSession().getAttribute("usuario");
                                if (dtu instanceof DtCliente) {
                                    request.setAttribute("suscripcion", ((DtCliente) dtu).getActual());
                                    request.setAttribute("suscripciones", ((DtCliente) dtu).getSuscripciones());
                                    request.setAttribute("listasFav", (ArrayList) port.obtenerListasFav(dtu.getNickname()).getListas());
                                    request.setAttribute("albumesFav", (ArrayList) port.obtenerAlbumesFav(dtu.getNickname()).getAlbum());
                                }
                            }

                            request.setAttribute("genero", genero);
                            request.setAttribute("listas", (ArrayList) port.listarLisReproduccionGen(genero).getListas());
                            request.setAttribute("albumes", (ArrayList) port.listarAlbumesGenero(genero).getAlbum());
                            request.getRequestDispatcher("vistas/consultar_genero.jsp").forward(request, response);
                        } else {
                            request.setAttribute("mensaje_error", "El genero no existe");
                            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        }
                    }
                    break;

                case "consultarAlbum":
                    if (request.getParameter("nickArtista") == null || request.getParameter("nomAlbum") == null) {
                        request.setAttribute("mensaje_error", "Faltan parametros");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                    }

                    String nickArtista = request.getParameter("nickArtista");

                    if (port.getDataUsuario(nickArtista) == null) {
                        request.setAttribute("mensaje_error", "El artista no existe");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                    }

                    String nomAlbum = URLDecoder.decode(request.getParameter("nomAlbum"), "UTF-8");
                    //controlar null
                    DtAlbumContenido dtAlbum = null;
                    try {
                        dtAlbum = port.obtenerAlbumContenido(nickArtista, nomAlbum);
                    } catch (UnsupportedOperationException e) {
                        request.setAttribute("mensaje_error", "El artista " + nickArtista + " no tiene el album " + nomAlbum);
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                    }

                    if (dtAlbum == null) {
                        request.setAttribute("mensaje_error", "El artista " + nickArtista + " no tiene el album " + nomAlbum);
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        return;
                    }

                    request.setAttribute("Album", dtAlbum);
                    request.getRequestDispatcher("/vistas/consultaAlbum.jsp").forward(request, response);
                    break;

                case "consultarListaDefecto":
                    if (request.getParameter("nomGenero") == null || request.getParameter("nomLista") == null) {
                        request.setAttribute("mensaje_error", "Faltan parámetros");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    } else {
                        String genero = request.getParameter("nomGenero");
                        if (port.existeGenero(genero)) {
                            String nomLista = request.getParameter("nomLista");
                            DtLista lEncontrada = null;
                            ArrayList<DtLista> listas = (ArrayList<DtLista>) port.listarLisReproduccionGen(genero).getListas();
                            for (DtLista l : listas) {
                                if (l.getNombre().equals(nomLista)) {
                                    lEncontrada = l;
                                    break;
                                }
                            }
                            if (lEncontrada == null) {
                                request.setAttribute("mensaje_error", "La lista no existe");
                                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                            } else {
                                request.setAttribute("lista", lEncontrada);
                                request.getRequestDispatcher("vistas/consultar_lista.jsp").forward(request, response);
                            }

                        } else {
                            request.setAttribute("mensaje_error", "El genero no existe");
                            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        }
                    }
                    break;

                case "consultarListaParticular":
                    if (request.getParameter("nickCliente") == null || request.getParameter("nomLista") == null) {
                        request.setAttribute("mensaje_error", "Faltan parámetros");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    } else {
                        String nickCliente = request.getParameter("nickCliente");
                        if (port.getDataUsuario(nickCliente) != null) {
                            String nomLista = request.getParameter("nomLista");
                            DtLista lEncontrada = null;
                            ArrayList<DtLista> listas = (ArrayList<DtLista>) port.listarLisReproduccion(nickCliente).getListas();
                            for (DtLista l : listas) {
                                if (l.getNombre().equals(nomLista)) {
                                    lEncontrada = l;
                                    break;
                                }
                            }
                            if (lEncontrada == null) {
                                request.setAttribute("mensaje_error", "La lista no existe");
                                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                            } else if (((DtListaParticular) lEncontrada).isPrivada()) {
                                if (request.getSession().getAttribute("usuario") == null) {
                                    request.setAttribute("mensaje_error", "La lista es privada");
                                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                                }
                                DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
                                if (!((DtListaParticular) lEncontrada).getNickDuenio().equals(usuario.getNickname())) {
                                    request.setAttribute("mensaje_error", "La lista es privada");
                                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                                } else {
                                    request.setAttribute("lista", lEncontrada);
                                    request.getRequestDispatcher("vistas/consultar_lista.jsp").forward(request, response);
                                }

                            } else {
                                request.setAttribute("lista", lEncontrada);
                                request.getRequestDispatcher("vistas/consultar_lista.jsp").forward(request, response);
                            }

                        } else {
                            request.setAttribute("mensaje_error", "El cliente no existe");
                            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        }
                    }

                    break;

                default:
                    request.setAttribute("mensaje_error", "Accion desconocida");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    break;

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("accion") == null) {
            request.setAttribute("mensaje_error", "Falta una accion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String accion = request.getParameter("accion");

        switch (accion) {
            case "nombreAlbum":
                if (request.getParameter("nickArtista") == null || request.getParameter("nombreAlbum") == null) {
                    request.setAttribute("mensaje_error", "Faltan parametros");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                String nickname = request.getParameter("nickArtista");
                String nombreAlbum = request.getParameter("nombreAlbum");

                String existe = "";

                if (port.existeAlbum(nickname, nombreAlbum) == true) {
                    existe = "si";
                } else {
                    existe = "no";
                }
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(existe);
                break;

            case "crearAlbum":
                String nombreA = request.getParameter("nombreAlbum");
                String nickArt = request.getParameter("nickArtista");
                String temas = request.getParameter("temas");
                String gen = request.getParameter("generos");
                String imagen = request.getParameter("imagen");
                int anio = Integer.parseInt(request.getParameter("anio"));

                DtTema dtTema;
                //ArrayList ArrayDeTemas = new ArrayList();
                DtListaTema ArrayDeTemas = new DtListaTema();
                //ArrayList<String> ArrayDeGeneros = new ArrayList();
                DtListaString ArrayDeGeneros = new DtListaString();

                String[] objGeneros = gen.split("&");
                int i;
                for (i = 0; i < objGeneros.length; i++) {
                    ArrayDeGeneros.getString().add(objGeneros[i]);
                }

                String[] todoTemas = temas.split("@");
                for (i = 0; i < todoTemas.length; i++) {
                    String[] data = todoTemas[i].split("~");
                    String[] duracion = data[3].split(":");

                    DtTime time = new DtTime();
                    //new DtTime(horas, minutos,segunos);
                    time.setHoras(Integer.parseInt(duracion[0]));
                    time.setMinutos(Integer.parseInt(duracion[1]));
                    time.setSegundos(Integer.parseInt(duracion[2]));
                    if (data[0].contains("mp3") == true) {
                        //DtTemaLocal(data[0], data[1], time, Integer.parseInt(data[2]))
                        DtTemaLocal dtLocal = new DtTemaLocal();
                        dtLocal.setDirectorio(data[0]);
                        dtLocal.setNombre(data[1]);
                        dtLocal.setDuracion(time);
                        dtLocal.setUbicacion(Integer.parseInt(data[2]));
                        dtTema = dtLocal;
                    } else {
                        DtTemaRemoto dtRemoto = new DtTemaRemoto();
                        dtRemoto.setUrl(data[0]);
                        dtRemoto.setNombre(data[1]);
                        dtRemoto.setDuracion(time);
                        dtRemoto.setUbicacion(Integer.parseInt(data[2]));
                        dtTema = dtRemoto;
                    }
                    ArrayDeTemas.getDtTemas().add(dtTema);
                }
                //Album(String nickArtista, String nombre, int anio, String imagen, HashMap<String, Tema> temas, ArrayList<Genero> generos)
                port.selectArtista(nickArt);

                port.ingresarAlbum(nombreA, anio, ArrayDeGeneros, imagen, ArrayDeTemas);
                // (nombreA, anio, ArrayDeGeneros, imagen, ArrayDeTemas)

                break;

            case "publicarLista":
                DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
                if (usuario != null) {
                    if (port.getDataUsuario(usuario.getNickname()) != null) {
                        String nomLista = request.getParameter("nomLista");
                        if (nomLista == null) {
                            request.setAttribute("mensaje_error", "Faltan parámetros");
                            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                        } else {
                            try {
                                port.publicarLista(usuario.getNickname(), nomLista);
                                request.setAttribute("pestania", "Listas");
                                request.getRequestDispatcher("/SConsultarPerfil?nickUs=" + usuario.getNickname()).forward(request, response);
                            } catch (UnsupportedOperationException e) {
                                request.setAttribute("mensaje_error", "No existe la lista '" + nomLista + "' en el sistema");
                                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                            }

                        }
                    } else {
                        request.setAttribute("mensaje_error", "El cliente no existe");
                        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    }

                } else {
                    request.setAttribute("mensaje_error", "Debe de iniciar sesion para utilizar esta opcion");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                }

                break;

            default:
                request.setAttribute("mensaje_error", "Accion desconocida en POST");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
