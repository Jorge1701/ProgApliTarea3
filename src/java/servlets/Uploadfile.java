package servlets;

import Configuracion.Configuracion;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import servicios.PUploadfileService;
import servicios.PUploadfile;

@WebServlet(name = "Uploadfile", urlPatterns = {"/Uploadfile"})
public class Uploadfile extends HttpServlet {

    PUploadfile port;

    public Uploadfile() {
        Configuracion.cargar();
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PUploadfile"));
            PUploadfileService webserv = new PUploadfileService(url);
            port = webserv.getPUploadfilePort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Uploadfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        FileItemFactory fif = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(fif);

        String accion = "";
        try {
            List<FileItem> items = sfu.parseRequest(request);
            //Obtener la accion a realizar
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String campo = item.getFieldName();
                    String valor = item.getString();

                    if (campo.equals("accion")) {
                        accion = valor;
                    }
                }
            }

            //Obtener los archivos y llamar a funcion para almacenar
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String filename = item.getName();
                    FileInputStream streamer = (FileInputStream) item.getInputStream();
                    byte[] byteArray = new byte[streamer.available()];
                    streamer.read(byteArray);
                    port.uploadfile(byteArray, filename, accion);

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
