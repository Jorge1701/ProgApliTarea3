package servlets;

import Logica.Fabrica;
import Logica.IContenido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SBuscador", urlPatterns = {"/SBuscador"})
public class SBuscador extends HttpServlet {

    private IContenido iContenido;

    public SBuscador() {
        iContenido = Fabrica.getIControladorContenido();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("busqueda") == null) {
            request.setAttribute("mensaje_error", "Debe de ingresar el parametro busqueda");
            request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            return;
        }

        String busqueda = request.getParameter("busqueda");
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("resultado", iContenido.buscar(busqueda, (request.getParameter("orden") != null ? request.getParameter("orden") : "")));
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
