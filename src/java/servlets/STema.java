package servlets;

import Configuracion.Configuracion;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PTema;
import servicios.PTemaService;

@WebServlet(name = "STema", urlPatterns = {"/STema"})
public class STema extends HttpServlet {

    PTema port;

    public STema() {
        Configuracion.cargar();
        cargar();
    }

    private void cargar() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PTema"));
            PTemaService service = new PTemaService(url);
            port = service.getPTemaPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(STema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }
        
        try {

            String track = request.getParameter("audio");
            byte[] img = null;

            img = port.getAudio(track);

            response.setContentType("audio/mpeg");
            response.setContentLength((int) img.length);

            OutputStream out = response.getOutputStream();
            out.write(img);
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(STema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
