package servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DownloadFile", urlPatterns = {"/DownloadFile"})
public class DownloadFile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
            if (request.getSession().getAttribute("usuario") == null || request.getParameter("Ruta") == null) {
                request.setAttribute("mensaje_error", "Lo siento, no se puede acceder aqui de esa forma");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                return;
            }

            String ruta = getServletContext().getRealPath("/");
            String[] parte = ruta.split("Tarea2");
            String tarea1 = parte[0] + "Tarea1" + File.separator;

            String filename = request.getParameter("Ruta");
            String filepath = tarea1 + "Recursos/Musica/";
            
            ServletOutputStream stream = null;
            BufferedInputStream buf = null;

            try {

                stream = response.getOutputStream();
                File mp3 = new File(filepath + filename);

                //headers
                response.setContentType("audio/mpeg");
                response.addHeader("Content-Disposition","attachment; filename=" + filename);
                response.setContentLength((int) mp3.length());
                FileInputStream input = new FileInputStream(mp3);
                buf = new BufferedInputStream(input);
                int readBytes = 0;
                while ((readBytes = buf.read()) != -1) 
                    stream.write(readBytes);
                

            } catch (IOException ioe) {
                throw new ServletException(ioe.getMessage());
            } finally {
                if (stream != null) 
                    stream.close();
                
                if (buf != null) 
                    buf.close();
                
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
