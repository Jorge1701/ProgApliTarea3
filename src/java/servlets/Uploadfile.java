package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "Uploadfile", urlPatterns = {"/Uploadfile"})
public class Uploadfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ruta = getServletContext().getRealPath("/");
        String[] parte = ruta.split("Tarea2");
        String tarea1 = parte[0] + "Tarea1" + File.separator;

        response.setContentType("text/html;charset=UTF-8");

        FileItemFactory fif = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(fif);
        String archivourl = "";
        try {
            List<FileItem> items = sfu.parseRequest(request);
            //Obtener la accion a realizar y colocar el path correcto
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String campo = item.getFieldName();
                    String valor = item.getString();

                    if (campo.equals("accion")) {
                        String accion = valor;

                        if (accion != null) {
                            switch (accion) {
                                case "registro":
                                    archivourl = tarea1 + "Recursos\\Imagenes\\Usuarios";
                                    break;
                                case "album":
                                    archivourl = tarea1 + "Recursos\\Imagenes\\Albumes";
                                    break;
                                case "lista":
                                    archivourl = tarea1 + "Recursos\\Imagenes\\Listas";
                                    break;
                                case "tema":
                                    archivourl = tarea1 + "Recursos\\Musica";
                                    break;
                            }
                        }
                    }
                }
            }
            
            //Obtener los archivos y almacenarlos
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = archivourl + File.separator + fileName;
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(Uploadfile.class.getName()).log(Level.SEVERE, null, ex);
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
