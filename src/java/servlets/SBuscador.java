package servlets;

import Configuracion.Configuracion;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PBuscador;
import servicios.PBuscadorService;

@WebServlet(name = "SBuscador", urlPatterns = {"/SBuscador"})
public class SBuscador extends HttpServlet {

    private PBuscador port;

    public SBuscador() {
        Configuracion.cargar();
        cargar();
    }

    private void cargar() {
        try {
            String path = "http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PBuscador");
            PBuscadorService ws = new PBuscadorService(new URL(path));
            port = ws.getPBuscadorPort();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (port == null) {
            cargar();
        }
        
        if (request.getParameter("busqueda") == null) {
            request.setAttribute("mensaje_error", "Debe de ingresar el parametro busqueda");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String busqueda = request.getParameter("busqueda");
        request.setAttribute("busqueda", busqueda);

        String orden = (request.getParameter("orden") != null ? request.getParameter("orden") : "");
        request.setAttribute("resultado", port.buscar(busqueda, orden).getLista());

        request.getRequestDispatcher("vistas/busqueda.jsp").forward(request, response);
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
