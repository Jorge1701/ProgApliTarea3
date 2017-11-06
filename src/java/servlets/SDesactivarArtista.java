package servlets;

import Configuracion.Configuracion;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PDesactivarArtista;
import servicios.PDesactivarArtistaService;

@WebServlet(name = "SDesactivarArtista", urlPatterns = {"/SDesactivarArtista"})
public class SDesactivarArtista extends HttpServlet {

    private PDesactivarArtista port;

    public SDesactivarArtista() {
        try {
            String path = "http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PDesactivarArtista");
            PDesactivarArtistaService ws = new PDesactivarArtistaService(new URL(path));
            port = ws.getPDesactivarArtistaPort();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
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
        port.desactivar(request.getParameter("artista"));

        request.getSession().removeAttribute("usuario");
        request.getSession().removeAttribute("suscripcion");
        request.getSession().removeAttribute("suscripciones");
        request.getRequestDispatcher("SInicio").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
