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
import servicios.DtArtista;
import servicios.DtCliente;
import servicios.DtPerfilArtista;
import servicios.DtPerfilCliente;
import servicios.DtUsuario;
import servicios.PConsultaPerfil;
import servicios.PConsultaPerfilService;
import servicios.SoapSeviciosFaultException_Exception;

@WebServlet(name = "SConsultarPerfil", urlPatterns = {"/SConsultarPerfil"})
public class SConsultarPerfil extends HttpServlet {

    PConsultaPerfil port;

    public SConsultarPerfil() {
        Configuracion.cargar();
        cargar();
    }

    private void cargar() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PConsultaPerfil"));
            PConsultaPerfilService service = new PConsultaPerfilService(url);
            port = service.getPConsultaPerfilPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SConsultarPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }

        if (request.getParameter("nickUs") == null) {
            request.setAttribute("mensaje_error", "No se ingreso que usuario quiere consultar");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String nickUs = "";
        if (request.getParameter("nickUs") != null) {
            nickUs = request.getParameter("nickUs");
        } else {
            nickUs = (String) request.getAttribute("nickUs");
        }
        DtUsuario DtUs = null;
        try {
            DtUs = port.getDataUsuario(nickUs);
        } catch (SoapSeviciosFaultException_Exception ex) {
            request.setAttribute("mensaje_error", "No existe el usuario " + nickUs);
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
        } catch (Exception exc) {
            request.setAttribute("mensaje_error", "Error de conexion con el servidor ");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
        }

        if (DtUs instanceof DtCliente) {

            DtPerfilCliente DtPerfilC = (DtPerfilCliente) port.obtenerPerfilCliente(nickUs);//iUsuario.obtenerPerfilCliente(nickUs);
            request.setAttribute("DtPerfilCliente", DtPerfilC);

            request.getRequestDispatcher("/vistas/consultaPerfilCliente.jsp").
                    forward(request, response);
        } else if (DtUs instanceof DtArtista) {
            DtPerfilArtista dtPerfilArtista = (DtPerfilArtista) port.obtenerPerfilArtista(nickUs);//iUsuario.obtenerPerfilArtista(nickUs);
            request.setAttribute("dtPerfilArtista", dtPerfilArtista);
            request.getRequestDispatcher("/vistas/consultaPerfilArtista.jsp").
                    forward(request, response);
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
