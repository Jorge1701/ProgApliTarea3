package servlets;

import Proxy.imagen.IOException_Exception;
import Proxy.imagen.PImagen;
import Proxy.imagen.PImagenService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SImagen", urlPatterns = {"/SImagen"})
public class SImagen extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("usuario") == null && request.getParameter("album") == null && request.getParameter("lista") == null) {
            request.setAttribute("mensaje_error", "No ingreso nada");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }
        
        byte[] img = null;
        String ruta = getServletContext().getRealPath("/");
        String[] parte = ruta.split("ProgApliTarea2");
        String tarea1 = parte[0] + "ProgApliTarea1" + File.separator;
        
        PImagenService serviceImg = new PImagenService();
        PImagen port = serviceImg.getPImagenPort();

        if (request.getParameter("usuario") != null) {
           BufferedImage bi = null;
            try {
                //bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Usuarios/" + request.getParameter("usuario")));
                img = port.getFile(tarea1 + "Recursos/Imagenes/Usuarios/", request.getParameter("usuario"));
                bi = ImageIO.read(new ByteArrayInputStream(img));
               
            } catch (IOException e) {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Usuarios/userDefaullt.png"));
                
            } 
            OutputStream out = response.getOutputStream();
            ImageIO.write(bi, "png", out);
            out.close();
        }
        if (request.getParameter("album") != null) {
            BufferedImage bi = null;
            try {
                //bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Albumes/" + request.getParameter("album")));
                img = port.getFile(tarea1 + "Recursos/Imagenes/Albumes/", request.getParameter("album"));
                bi = ImageIO.read(new ByteArrayInputStream(img));
            } catch (IOException e) {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Albumes/albumDefault.png"));
            } 
            OutputStream out = response.getOutputStream();
            ImageIO.write(bi, "png", out);
            out.close();
        }
        if (request.getParameter("lista") != null) {
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Listas/" + request.getParameter("lista")));
            } catch (IOException e) {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Listas/listaDefault.png"));
            }
            OutputStream out = response.getOutputStream();
            ImageIO.write(bi, "png", out);
            out.close();
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
