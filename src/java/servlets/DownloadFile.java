package servlets;

import Configuracion.Configuracion;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PDownloadFile;
import servicios.PDownloadFileService;

@WebServlet(name = "DownloadFile", urlPatterns = {"/DownloadFile"})
public class DownloadFile extends HttpServlet {

    PDownloadFile port;

    public DownloadFile() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PDownloadFile"));
            PDownloadFileService webserv = new PDownloadFileService(url);
            port = webserv.getPDownloadFilePort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(DownloadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("usuario") == null || request.getParameter("Ruta") == null) {
            request.setAttribute("mensaje_error", "Lo siento, no se puede acceder aqui de esa forma");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String filename = request.getParameter("Ruta");

        ServletOutputStream stream = null;
        BufferedInputStream buf = null;

        byte[] bytes = null;

        try {

            stream = response.getOutputStream();
            bytes = port.getFile(filename);
            response.setContentType("audio/mpeg");
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);
            response.setContentLength((int) bytes.length);
            stream.write(bytes, 0, bytes.length);

            /* buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }*/
        } catch (IOException ioe) {
            throw new ServletException(ioe.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }

            if (buf != null) {
                buf.close();
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
