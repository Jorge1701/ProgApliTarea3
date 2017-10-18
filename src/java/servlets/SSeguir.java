package servlets;

import Logica.DtArtista;
import Logica.DtCliente;
import Logica.DtSuscripcion;
import Logica.DtUsuario;
import Logica.Fabrica;
import Logica.IUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SSeguir", urlPatterns = {"/SSeguir"})
public class SSeguir extends HttpServlet {

    private IUsuario iUsuario;

    public SSeguir() {
        iUsuario = Fabrica.getIControladorUsuario();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje_error", "Ups, usted no deberia estar aqui   <span class=\"glyphicon glyphicon-eye-close\"></span>");
        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if (request.getParameter("accion") == null || request.getParameter("seguido") == null) {
            request.setAttribute("mensaje_error", "No hay accion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String accion = request.getParameter("accion");
        String seguido = request.getParameter("seguido");

        DtUsuario dtuSeguido = iUsuario.getDataUsuario(seguido);

        if (dtuSeguido == null) {
            request.setAttribute("mensaje_error", "El usuario que se desea seguir no existe");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        switch (accion) {
            case "seguir":
                iUsuario.seguirUsuario(dtc.getNickname(), seguido);
                request.getRequestDispatcher("/SInicio?pestania=" + (dtuSeguido instanceof DtCliente ? "Clientes" : "Artistas")).forward(request, response);

                break;
            case "dejarSeguir":
                iUsuario.dejarSeguirUsuario(dtc.getNickname(), seguido);
                request.getRequestDispatcher("/SInicio?pestania=" + (dtuSeguido instanceof DtCliente ? "Clientes" : "Artistas")).forward(request, response);

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
