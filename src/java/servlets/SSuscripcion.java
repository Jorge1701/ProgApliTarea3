package servlets;

import Logica.DtArtista;
import Logica.DtUsuario;
import Logica.DtCliente;
import Logica.DtFecha;
import Logica.DtSuscripcion;
import Logica.Fabrica;
import Logica.IUsuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SSuscripcion", urlPatterns = {"/SSuscripcion"})
public class SSuscripcion extends HttpServlet {

    IUsuario iUsuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        iUsuario = Fabrica.getIControladorUsuario();

        if (request.getParameter("accion").equals("redir")) {
            if (request.getSession().getAttribute("usuario") != null) {
                DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario");

                if (usr instanceof DtArtista) {
                    request.setAttribute("mensaje_error", "Esta página esta reservada para nuestros clientes");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("suscripcion", ((DtCliente) usr).getSuscripcion());
                    request.getSession().setAttribute("suscripciones", ((DtCliente) usr).getSuscripciones());
                    this.getServletContext().getRequestDispatcher("/vistas/suscripcion.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mensaje_error", "Debe estar logueado para ver esta página");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

        } else if (request.getParameter("accion").equals("redir1")) {
            if (request.getSession().getAttribute("usuario") != null) {
                DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario");

                if (usr instanceof DtArtista) {
                    request.setAttribute("mensaje_error", "Esta página esta reservada para nuestros clientes");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                } else {
                    request.setAttribute("suscripcion", ((DtCliente) usr).getSuscripcion());
                    request.setAttribute("suscripciones", (ArrayList<DtSuscripcion>) ((DtCliente) usr).getSuscripciones());
                    request.getRequestDispatcher("vistas/estado_sus.jsp").forward(request, response);
                    //this.getServletContext().getRequestDispatcher("/vistas/estado_sus.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mensaje_error", "Debe estar logueado para ver esta página");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

        } else if (request.getParameter("accion").equals("monto")) {
            String cuota = request.getParameter("Cuota");
            int monto = iUsuario.getMonto(cuota);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(monto));

        } else if (request.getParameter("accion").equals("registro")) {
            if (request.getSession().getAttribute("usuario") == null) {
                request.setAttribute("mensaje_error", "Debe estar logueado para ver esta página");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

            DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
            if (((DtCliente) usuario).getSuscripcion() != null) {
                request.setAttribute("mensaje_error", "Ya posee una suscripción vigente");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }
            String nickname = usuario.getNickname();
            String cuota = request.getParameter("Cuota");

            if (iUsuario.ingresarSuscripcion(nickname, cuota)) {
                DtUsuario usr = iUsuario.getDataUsuario(nickname);
                request.getSession().setAttribute("usuario", usr);
                getServletContext().getRequestDispatcher("/SInicio").forward(request, response);
            }

        } else if (request.getParameter("accion").equals("cancelar")) {
            //este cancelar es cuando pasa de pendiente a cancelada
            DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
            String estado = request.getParameter("Estado");

            Calendar hoy = new GregorianCalendar();
            DtFecha fechaHoy = new DtFecha(hoy.get(Calendar.DATE), (hoy.get(Calendar.MONTH) + 1), hoy.get(Calendar.YEAR));

            if (estado.equals("Pendiente")) {
                if (iUsuario.cancelarSuscripcion(usuario.getNickname(), estado, "", "", "", fechaHoy)) {
                    DtUsuario usr = iUsuario.getDataUsuario(usuario.getNickname());
                    DtSuscripcion s = ((DtCliente) usr).getSuscripcion();
                    request.getSession().setAttribute("usuario", usr);
                    // request.getSession().setAttribute("suscripcion", s);
                    // request.getSession().setAttribute("suscripciones", ((DtCliente) usr).getSuscripciones());
                    // getServletContext().getRequestDispatcher("/SSuscripcion?accion=redir1").forward(request, response);
                }
            } else {
                String cuota = request.getParameter("Cuota");
                String fecha = request.getParameter("Fecha");
                String fecha_venc = request.getParameter("FechaVenc");

                if (iUsuario.cancelarSuscripcion(usuario.getNickname(), estado, cuota, fecha, fecha_venc, fechaHoy)) {
                    DtUsuario usr = iUsuario.getDataUsuario(usuario.getNickname());
                    DtSuscripcion s = ((DtCliente) usr).getSuscripcion();
                    request.getSession().setAttribute("usuario", usr);
                    //request.getSession().setAttribute("suscripcion", s);
                    //request.getSession().setAttribute("suscripciones", ((DtCliente) usr).getSuscripciones());
                    //getServletContext().getRequestDispatcher("/SSuscripcion?accion=redir1").forward(request, response);
                }

            }

        } else if (request.getParameter("accion").equals("renovar")) {

            DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");

            String estado = request.getParameter("Estado");
            String cuota = request.getParameter("Cuota");
            String fecha = request.getParameter("Fecha");
            String fecha_venc = request.getParameter("FechaVenc");

            Calendar dia = new GregorianCalendar();
            DtFecha hoy = new DtFecha(dia.get(Calendar.DATE), (dia.get(Calendar.MONTH) + 1), dia.get(Calendar.YEAR));

            if (((DtCliente) usuario).getSuscripcion() == null) {
                if (iUsuario.renovarSuscripcion(usuario.getNickname(), estado, cuota, fecha, fecha_venc, hoy)) {
                    DtUsuario usr = iUsuario.getDataUsuario(usuario.getNickname());
                    DtSuscripcion s = ((DtCliente) usr).getSuscripcion();
                    request.getSession().setAttribute("usuario", usr);
                }
            } else {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("error");
            }

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
