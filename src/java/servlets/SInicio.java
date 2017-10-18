package servlets;

import servicios.DtCliente;
import servicios.DtArtista;
import servicios.DtSuscripcion;
import servicios.DtUsuario;
import Logica.Fabrica;
//import Logica.IContenido;
//import Logica.IUsuario;
import Properties.PropertyManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PInicioService;
import servicios.PInicio;

@WebServlet(name = "SInicio", urlPatterns = {"/SInicio"})
public class SInicio extends HttpServlet {

    PropertyManager properties;
    String ip;
    String puerto;
    String servicio;
    PInicio port;
    //private IUsuario iUsuario;
    //private IContenido iContenido;

    public SInicio() throws MalformedURLException {
        //properties = PropertyManager.getInstance();
        //ip = properties.getProperty("ip");
        //puerto = properties.getProperty("puerto");
        //servicio = "inicio";
        //iUsuario = Fabrica.getIControladorUsuario();
        //iContenido = Fabrica.getIControladorContenido();
        // URL url = new URL("http://" + ip + ":" + puerto + "/" + servicio);
        URL url = new URL("http://localhost:1234/inicio");
        PInicioService webserv = new PInicioService(url);
        port = webserv.getPInicioPort();
    }

    /*@Override
    public void init() throws ServletException {
        Fabrica.inicializarControladores();
        try {
            Fabrica.levantarDatos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("cargarDatosPrueba") != null) {
            if (request.getSession().getAttribute("usuario") == null) {
                request.setAttribute("mensaje_error", "Usted no puede cargar los datos de prueba");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                return;
            }
            try {
                port.cargarDatosPrueba();
                request.getSession().removeAttribute("usuario");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //Cuadros de buqueda de clientes y artistas
        request.setAttribute("generos", (ArrayList) port.obtenerGeneros().getString());
        ArrayList<DtUsuario> clientes = (ArrayList) port.listarClientes().getUsuarios();
        ArrayList<DtUsuario> artistas = (ArrayList) port.listarArtistas().getUsuarios();

        String busqueda = request.getParameter("busqueda");
        String pestania = request.getParameter("pestania");

        if (busqueda != null && pestania != null) {
            ArrayList<DtUsuario> resultado = new ArrayList<>();
            if (pestania.equals("Clientes")) {
                for (DtUsuario cliente : clientes) {
                    if (cliente.getNickname().toLowerCase().contains(busqueda.toLowerCase()) || cliente.getNombre().toLowerCase().contains(busqueda.toLowerCase()) || cliente.getApellido().toLowerCase().contains(busqueda.toLowerCase())) {
                        resultado.add(cliente);
                    }
                }
                request.setAttribute("clientes", resultado);
                request.setAttribute("artistas", artistas);
                request.setAttribute("busquedaCliente", busqueda);

            } else if (pestania.equals("Artistas")) {
                for (DtUsuario artista : artistas) {
                    if (artista.getNickname().toLowerCase().contains(busqueda.toLowerCase()) || artista.getNombre().toLowerCase().contains(busqueda.toLowerCase()) || artista.getApellido().toLowerCase().contains(busqueda.toLowerCase())) {
                        resultado.add(artista);

                    }
                }
                request.setAttribute("artistas", resultado);
                request.setAttribute("clientes", clientes);
                request.setAttribute("busquedaArtista", busqueda);

            }

        } else {
            request.setAttribute("clientes", clientes);
            request.setAttribute("artistas", artistas);
        }

        //Chequear si la suscripcion actual esta vencida
        if (request.getSession().getAttribute("usuario") != null) {

            DtUsuario u = (DtUsuario) request.getSession().getAttribute("usuario");

            if (u instanceof DtCliente) {

                DtSuscripcion s = ((DtCliente) u).getActual();
                ArrayList<DtSuscripcion> dts = (ArrayList) ((DtCliente) u).getSuscripciones();

                if (s != null) {

                    if (s.getEstado().equals("Vigente")) {

                        port.chequearSuscripcion(u.getNickname());
                        DtUsuario usr = port.getDataUsuario(u.getNickname());
                        request.getSession().setAttribute("usuario", usr);
                        request.setAttribute("suscripcion", ((DtCliente) usr).getActual());

                    }

                }

                if (dts != null) {
                    DtUsuario usr = port.getDataUsuario(u.getNickname());
                    request.setAttribute("suscripciones", ((DtCliente) usr).getSuscripciones());
                }
                // hasta aca
                request.setAttribute("seguidos", port.listarSeguidosDe(u.getNickname()));
            }
        }

        if (request.getParameter("pestania") != null) {
            request.setAttribute("pestania", request.getParameter("pestania"));
        }

        if (request.getParameter("mensaje") != null) {
            request.setAttribute("mensaje", request.getParameter("mensaje"));
        }

        request.getRequestDispatcher("vistas/inicio.jsp").forward(request, response);
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
