package servlets;

import Configuracion.Configuracion;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
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
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PImagen"));
            PImagenService serviceImg = new PImagenService(url);
            port = serviceImg.getPImagenPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("usuario") == null && request.getParameter("album") == null && request.getParameter("lista") == null) {
            request.setAttribute("mensaje_error", "No ingreso nada");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }
        byte[] img = null;
        String ruta = getServletContext().getRealPath("/");
        String[] parte = ruta.split("ProgApliTarea3");
        String tarea1 = parte[0] + "ProgApliTarea1" + File.separator;

        if (request.getParameter("usuario") != null) {
            BufferedImage bi = null;
            OutputStream out = response.getOutputStream();
            try {
                //bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Usuarios/" + request.getParameter("usuario")));
                img = port.getFile("Usuario", request.getParameter("usuario"));
                out.write(img);
                //bi = ImageIO.read(new ByteArrayInputStream(img));
            } catch (Exception e) {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Usuarios/userDefaullt.png"));
                ImageIO.write(bi, "png", out);
            }

            out.close();
        }
        if (request.getParameter("album") != null) {
            BufferedImage bi = null;
            OutputStream out = response.getOutputStream();
            try {
                //bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Albumes/" + request.getParameter("album")));
                img = port.getFile("Album", request.getParameter("album"));
                out.write(img);
            } catch (Exception e) {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Albumes/albumDefault.png"));
                ImageIO.write(bi, "png", out);
            }

            out.close();
        }
        if (request.getParameter("lista") != null) {
            BufferedImage bi = null;
            OutputStream out = response.getOutputStream();
            try {
                //bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Listas/" + request.getParameter("lista")));
                img = port.getFile("Lista", request.getParameter("lista"));
                out.write(img);
            } catch (Exception e) {
                bi = ImageIO.read(new File(tarea1 + "Recursos/Imagenes/Listas/listaDefault.png"));
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
