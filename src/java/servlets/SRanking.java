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

@WebServlet(name = "SRanking", urlPatterns = {"/SRanking"})
public class SRanking extends HttpServlet {

//    PRanking port
//    public SRanking() {
//        try {
//            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PRanking"));
//            PRankingService webserv = new PRankingService(url);
//            port = webserv.getPRankingPort();
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(SRanking.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("redirigir") != null) {
            //DtRanking ranking = port.obtenerRanking();
            request.setAttribute("ranking", "ranking");

            request.getRequestDispatcher("/vistas/ranking.jsp").forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
