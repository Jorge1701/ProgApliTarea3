package servlets;

import Configuracion.Configuracion;
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
import servicios.PContadores;
import servicios.PContadoresService;

@WebServlet(name = "SContadores", urlPatterns = {"/SContadores"})
public class SContadores extends HttpServlet {

    private PContadores port;

    public SContadores() {
        Configuracion.cargar();
        try {
            PContadoresService ws = new PContadoresService(new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PContadores")));
            port = ws.getPContadoresPort();
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
        String accion = request.getParameter("accion");

        String nickArt = URLDecoder.decode(request.getParameter("nickArt"), "UTF-8");
        String nomAlbum = URLDecoder.decode(request.getParameter("nomAlbum"), "UTF-8");
        String nomTema = URLDecoder.decode(request.getParameter("nomTema"), "UTF-8");

        switch (accion) {
            case "reproducir":
                port.reproducirTema(nickArt, nomAlbum, nomTema);
                break;
            case "descarga":
                port.descargaTema(nickArt, nomAlbum, nomTema);
                break;
            default:
                request.setAttribute("mensaje_error", "Accion no valida");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
