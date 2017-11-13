package servlets;

import servicios.DtFecha;

import servicios.DtArtista;
import servicios.DtCliente;
import servicios.DtUsuario;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PRegistro;
import servicios.PRegistroService;
import Configuracion.Configuracion;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SRegistro", urlPatterns = {"/SRegistro"})
public class SRegistro extends HttpServlet {

    PRegistro port;

    public SRegistro() {
        Configuracion.cargar();
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PRegistro"));
            PRegistroService webserv = new PRegistroService(url);
            port = webserv.getPRegistroPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje_error", "Ups, usted no deberia estar aqui :s");
        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("accion") == null) {
            request.setAttribute("mensaje_error", "No hay una accion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String accion = request.getParameter("accion");

        switch (accion) {
            case "redirigir":
                if (request.getSession().getAttribute("usuario") != null) {
                    request.setAttribute("mensaje_error", "Ya hay un usuario logueado");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }
                request.getRequestDispatcher("vistas/registrarse.jsp").forward(request, response);

                break;
            default:
                request.setAttribute("mensaje_error", "El resto de acciones van por POST");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String existe;
        String accion = request.getParameter("accion");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");

        if (null != accion) {
            switch (accion) {
                case "redirigir":
                    //Reridigir a la pagina de registro
                    this.getServletContext().getRequestDispatcher("/vistas/registrarse.jsp").forward(request, response);
                    break;
                case "nickname":
                    //Verficacion de nickname

                    if (port.nicknameExiste(nickname)) {
                        existe = "si";
                    } else {
                        existe = "no";
                    }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(existe);
                    break;
                case "email":
                    //Verficacion de email
                    if (!port.correoExiste(email)) {
                        existe = "si";
                    } else {
                        existe = "no";
                    }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(existe);
                    break;
                case "registro":
                    //Ingreso del perfil
                    String contrasenia = request.getParameter("contrasenia");
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String dia = request.getParameter("dia");
                    String mes = request.getParameter("mes");
                    String anio = request.getParameter("anio");
                    String biografia = request.getParameter("biografia");
                    String link = request.getParameter("link");
                    String artista = request.getParameter("artista");
                    String imagen = request.getParameter("imagen");

                    int nMes;
                    switch (mes) {
                        case "enero":
                            nMes = 1;
                            break;
                        case "febrero":
                            nMes = 2;
                            break;
                        case "marzo":
                            nMes = 3;
                            break;
                        case "abril":
                            nMes = 4;
                            break;
                        case "mayo":
                            nMes = 5;
                            break;
                        case "junio":
                            nMes = 6;
                            break;
                        case "julio":
                            nMes = 7;
                            break;
                        case "agosto":
                            nMes = 8;
                            break;
                        case "setiembre":
                            nMes = 9;
                            break;
                        case "octubre":
                            nMes = 10;
                            break;
                        case "noviembre":
                            nMes = 11;
                            break;
                        case "diciembre":
                            nMes = 12;
                            break;
                        default:
                            nMes = 0;
                            break;
                    }

                    DtFecha fechaNac = new DtFecha();
                    fechaNac.setDia(Integer.valueOf(dia));
                    fechaNac.setMes(nMes);
                    fechaNac.setAnio(Integer.valueOf(anio));

                    DtUsuario dtu;
                    if ("si".equals(artista)) {
                        dtu = new DtArtista();
                        ((DtArtista) dtu).setBiografia(biografia);
                        ((DtArtista) dtu).setWeb(link);
                        ((DtArtista) dtu).setActivo(true);
                    } else {
                        dtu = new DtCliente();
                    }

                    dtu.setNickname(nickname);
                    dtu.setNombre(nombre);
                    dtu.setApellido(apellido);
                    dtu.setEmail(email);
                    dtu.setFechaNac(fechaNac);
                    dtu.setImagen(imagen);
                    dtu.setContrasenia(contrasenia);

                    port.ingresarUsuario(dtu);

                    request.getSession().setAttribute("usuario", dtu);

                    //request.getRequestDispatcher("SInicio").forward(request, response);      //Redirigir utilizando el nombre del servlet
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
