package servlets;

import Configuracion.Configuracion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PImagen;
import servicios.PImagenService;

@WebServlet(name = "SImagen", urlPatterns = {"/SImagen"})
public class SImagen extends HttpServlet {

    PImagen port;

    public SImagen() {
        Configuracion.cargar();
        cargar();
    }

    private void cargar() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PImagen"));
            PImagenService serviceImg = new PImagenService(url);
            port = serviceImg.getPImagenPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }

        if (request.getParameter("usuario") == null && request.getParameter("album") == null && request.getParameter("lista") == null) {
            request.setAttribute("mensaje_error", "No ingreso nada");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }
        byte[] img = null;

        String quitar = "WEB-INF/classes/servlets/SImagen.class";
        String path = SImagen.class.getResource("/servlets/SImagen.class").getPath().replace(quitar, "media/");

        if (request.getParameter("usuario") != null) {
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            try {
                img = port.getFile("Usuario", request.getParameter("usuario"));
                response.setContentLength((int) img.length);
                out.write(img);
            } catch (Exception e) {
                BufferedImage bi = ImageIO.read(new File(path + "userDefault.png"));
                ImageIO.write(bi, "png", out);

            }
            out.close();
        }
        if (request.getParameter("album") != null) {
            BufferedImage bi = null;
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            try {
                img = port.getFile("Album", request.getParameter("album"));
                response.setContentLength((int) img.length);
                out.write(img);
            } catch (Exception e) {
                bi = ImageIO.read(new File(path + "albumDefault.png"));
                ImageIO.write(bi, "png", out);

            }
            out.close();
        }
        if (request.getParameter("lista") != null) {
            BufferedImage bi = null;
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            try {
                img = port.getFile("Lista", request.getParameter("lista"));
                response.setContentLength((int) img.length);
                out.write(img);
            } catch (Exception e) {
                bi = ImageIO.read(new File(path + "albumDefault.png"));
                ImageIO.write(bi, "png", out);

            }
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
