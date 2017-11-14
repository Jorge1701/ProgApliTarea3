package servlets;

import Configuracion.Configuracion;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.DtCliente;
import servicios.DtFecha;
import servicios.DtLista;
import servicios.DtListaDeListas;
import servicios.DtListaParticular;
import servicios.DtUsuario;
import servicios.PLista;
import servicios.PListaService;
import servicios.SoapSeviciosFaultException_Exception;

@WebServlet(name = "SLista", urlPatterns = {"/SLista"})
public class SLista extends HttpServlet {

    PLista port;

    public SLista() {
        Configuracion.cargar();
        cargar();
    }

    private void cargar() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PLista"));
            PListaService webserv = new PListaService(url);
            port = webserv.getPListaPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SLista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }

        request.setAttribute("mensaje_error", "Ups, usted no deberia estar aqui :s");
        request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }

        DtUsuario dtUs = (DtUsuario) request.getSession().getAttribute("usuario");
        if (dtUs == null) {            // 
            request.setAttribute("mensaje_error", "Debe iniciar sesion");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        //Chequeo suscripcion
        if (dtUs instanceof DtCliente) {

            DtCliente dtC = (DtCliente) dtUs;
            if (dtC.getActual() != null && dtC.getActual().getEstado() != null && dtC.getActual().getEstado().equals("Vigente")) {
                request.getRequestDispatcher("vistas/crear_lista_reproduccion.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje_error", "Usuario sin suscripcion");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("mensaje_error", "Solo para Clientes");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }

        DtUsuario dtUs = (DtUsuario) request.getSession().getAttribute("usuario");

        String existe;
        String accion = request.getParameter("accion");
        String nombreL = request.getParameter("nombreLst");

        if ("nombreLst".equals(accion)) {
            log("Aasdasd");
            if (port == null) {
                log("PORT NULL");
            }
            if (nombreL == null) {
                log("NOMBREL NULL");
            }
            if (dtUs.getNickname() == null) {
                log("NICK NULL");
            }
            if (port.existeLista(nombreL, dtUs.getNickname())) {
                existe = "si";
            } else {
                existe = "no";
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(existe);

        } else if ("registroLst".equals(accion)) {
            Calendar c = new GregorianCalendar();
            DtFecha actual = new DtFecha();
            //c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)
            actual.setAnio(c.get(Calendar.YEAR));
            actual.setDia(c.get(Calendar.DATE));
            actual.setMes(c.get(Calendar.MONTH) + 1);

            DtListaParticular lista = new DtListaParticular();
            //true, nombreL, new ArrayList<>(), null, actual, dtUs.getNickname()
            lista.setFecha(actual);
            lista.setImagen(null);
            lista.setNickDuenio(dtUs.getNickname());
            lista.setNombre(nombreL);
            lista.setPrivada(true);

            port.crearListaReproduccion(lista, dtUs.getNickname());

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
