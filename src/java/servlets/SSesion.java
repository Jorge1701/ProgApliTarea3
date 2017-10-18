package servlets;

import Logica.DtCliente;
import Logica.DtUsuario;
import Logica.Fabrica;
import Logica.IUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SSesion", urlPatterns = {"/SSesion"})
public class SSesion extends HttpServlet {

    private IUsuario iUsuario;

    public SSesion() {
        iUsuario = Fabrica.getIControladorUsuario();
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
            case "cerrarSesion":
                if (request.getSession().getAttribute("usuario") == null) {
                    request.setAttribute("mensaje_error", "Debe haber un usuario logueado");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                request.getSession().removeAttribute("usuario");
                request.getSession().removeAttribute("suscripcion");
                request.getSession().removeAttribute("suscripciones");
                request.getRequestDispatcher("SInicio").forward(request, response);

                break;
            case "redirigir":
                if (request.getSession().getAttribute("usuario") != null) {
                    request.setAttribute("mensaje_error", "Ya hay un usuario logueado");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }
                request.getRequestDispatcher("vistas/iniciar_sesion.jsp").forward(request, response);

                break;
            case "error":
                if (request.getParameter("mensaje") == null) {
                    request.setAttribute("error", "Error desconocido");
                    request.getRequestDispatcher("vistas/iniciar_sesion.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", request.getParameter("mensaje"));
                    request.getRequestDispatcher("vistas/iniciar_sesion.jsp").forward(request, response);
                }

                break;
            default:
                request.setAttribute("mensaje_error", "El resto de acciones van por POST");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("accion") == null) {
            request.setAttribute("mensaje_error", "No hay una accion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String accion = request.getParameter("accion");

        switch (accion) {
            case "iniciarSesion":
                if (request.getSession().getAttribute("usuario") != null) {
                    request.setAttribute("mensaje_error", "Ya hay un usuario logueado");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                if (request.getParameter("nickname") == null || request.getParameter("contrasenia") == null) {
                    request.setAttribute("mensaje_error", "Faltan parametros");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                String nickname = iUsuario.chequearLogin(request.getParameter("nickname"), request.getParameter("contrasenia"));

                if (!nickname.contains("Error")) {
                    DtUsuario dtu = iUsuario.getDataUsuario(nickname);
                    request.getSession().setAttribute("usuario", dtu);
                    if (dtu instanceof DtCliente) {
                        request.getSession().setAttribute("suscripcion", ((DtCliente) dtu).getSuscripcion());
                        request.getSession().setAttribute("suscripciones", ((DtCliente) dtu).getSuscripciones());
                    }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("correcto");
                } else {
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(nickname);
                }

                break;
            case "cerrarSesion":
                if (request.getSession().getAttribute("usuario") == null) {
                    request.setAttribute("mensaje_error", "Debe haber un usuario logueado");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                    return;
                }

                request.getSession().removeAttribute("usuario");
                request.getSession().removeAttribute("suscripcion");
                request.getSession().removeAttribute("suscripciones");
                request.getRequestDispatcher("SInicio").forward(request, response);

                break;
            case "redirigir":
                request.getRequestDispatcher("vistas/iniciar_sesion.jsp").forward(request, response);

                break;
            default:
                request.setAttribute("mensaje_error", "Accion desconocida");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);

                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
