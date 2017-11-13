package servlets;

import Configuracion.Configuracion;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.DtArtista;
import servicios.DtCliente;
import servicios.DtSuscripcion;
import servicios.DtUsuario;
import servicios.PSeguir;
import servicios.PSeguirService;

@WebServlet(name = "SSeguir", urlPatterns = {"/SSeguir"})
public class SSeguir extends HttpServlet {

    private PSeguir port;

    public SSeguir() {
        Configuracion.cargar();
        try {
            PSeguirService ws = new PSeguirService(new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PSeguir")));
            port = ws.getPSeguirPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSeguir.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        DtUsuario dtuSeguido = port.getDataUsuario(seguido);

        if (dtuSeguido == null) {
            request.setAttribute("mensaje_error", "El usuario que se desea seguir no existe");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        switch (accion) {
            case "seguir":
                port.seguirUsuario(dtc.getNickname(), seguido);
                request.getRequestDispatcher("/SInicio?pestania=" + (dtuSeguido instanceof DtCliente ? "Clientes" : "Artistas")).forward(request, response);

                break;
            case "dejarSeguir":
                port.dejarSeguirUsuario(dtc.getNickname(), seguido);
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
