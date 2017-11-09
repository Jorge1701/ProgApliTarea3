package servlets;

import servicios.DtArtista;
import servicios.DtUsuario;
import servicios.DtCliente;
import servicios.DtFecha;
import servicios.DtSuscripcion;
import servicios.PSuscripcionService;
import servicios.PSuscripcion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Configuracion.Configuracion;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SSuscripcion", urlPatterns = {"/SSuscripcion"})
public class SSuscripcion extends HttpServlet {

    PSuscripcion port;

    public SSuscripcion() {
        try {
            URL url = new URL("http://" + Configuracion.get("ip") + ":" + Configuracion.get("puerto") + "/" + Configuracion.get("PSuscripcion"));
            PSuscripcionService suscripcion = new PSuscripcionService(url);
            port = suscripcion.getPSuscripcionPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("accion").equals("redir")) {
            if (request.getSession().getAttribute("usuario") != null) {
                DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario");

                if (usr instanceof DtArtista) {
                    request.setAttribute("mensaje_error", "Esta pagina esta reservada para nuestros clientes");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("suscripcion", ((DtCliente) usr).getActual());
                    request.getSession().setAttribute("suscripciones", ((DtCliente) usr).getSuscripciones());
                    this.getServletContext().getRequestDispatcher("/vistas/suscripcion.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mensaje_error", "Debe estar logueado para ver esta pÃ¡gina");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

        } else if (request.getParameter("accion").equals("redir1")) {
            if (request.getSession().getAttribute("usuario") != null) {
                DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario");

                if (usr instanceof DtArtista) {
                    request.setAttribute("mensaje_error", "Esta pÃgina esta reservada para nuestros clientes");
                    request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
                } else {
                    request.setAttribute("suscripcion", ((DtCliente) usr).getActual());
                    request.setAttribute("suscripciones", (ArrayList<DtSuscripcion>) ((DtCliente) usr).getSuscripciones());
                    request.getRequestDispatcher("vistas/estado_sus.jsp").forward(request, response);
                    //this.getServletContext().getRequestDispatcher("/vistas/estado_sus.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mensaje_error", "Debe estar logueado para ver esta pÃ¡gina");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

        } else if (request.getParameter("accion").equals("monto")) {
            String cuota = request.getParameter("Cuota");
            int monto = port.getMonto(cuota);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(monto));

        } else if (request.getParameter("accion").equals("registro")) {
            if (request.getSession().getAttribute("usuario") == null) {
                request.setAttribute("mensaje_error", "Debe estar logueado para ver esta pÃ¡gina");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }

            DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
            if (((DtCliente) usuario).getActual() != null) {
                request.setAttribute("mensaje_error", "Ya posee una suscripciÃ³n vigente");
                request.getRequestDispatcher("vistas/pagina_error.jsp").forward(request, response);
            }
            String nickname = usuario.getNickname();
            String cuota = request.getParameter("Cuota");

            if (port.ingresarSuscripcion(nickname, cuota)) {
                DtUsuario usr = port.getDataUsuario(nickname);
                request.getSession().setAttribute("usuario", usr);
                getServletContext().getRequestDispatcher("/SInicio").forward(request, response);
            }

        } else if (request.getParameter("accion").equals("cancelar")) {
            //este cancelar es cuando pasa de pendiente a cancelada
            DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
            String estado = request.getParameter("Estado");

            Calendar hoy = new GregorianCalendar();
            DtFecha fechaHoy = new DtFecha();

            //seteo de la fecha
            fechaHoy.setDia(hoy.get(Calendar.DATE));
            fechaHoy.setMes(hoy.get(Calendar.MONTH) + 1);
            fechaHoy.setAnio(hoy.get(Calendar.YEAR));
            //

            if (estado.equals("Pendiente")) {
                if (port.cancelarSuscripcion(usuario.getNickname(), estado, "", "", "", fechaHoy)) {
                    DtUsuario usr = port.getDataUsuario(usuario.getNickname());
                    DtSuscripcion s = ((DtCliente) usr).getActual();
                    request.getSession().setAttribute("usuario", usr);
                    // request.getSession().setAttribute("suscripcion", s);
                    // request.getSession().setAttribute("suscripciones", ((DtCliente) usr).getSuscripciones());
                    // getServletContext().getRequestDispatcher("/SSuscripcion?accion=redir1").forward(request, response);
                }
            } else {
                String cuota = request.getParameter("Cuota");
                String fecha = request.getParameter("Fecha");
                String fecha_venc = request.getParameter("FechaVenc");

                if (port.cancelarSuscripcion(usuario.getNickname(), estado, cuota, fecha, fecha_venc, fechaHoy)) {
                    DtUsuario usr = port.getDataUsuario(usuario.getNickname());
                    DtSuscripcion s = ((DtCliente) usr).getActual();
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
            DtFecha hoy = new DtFecha();

            //seteo de la fecha
            hoy.setDia(dia.get(Calendar.DATE));
            hoy.setMes(dia.get(Calendar.MONTH) + 1);
            hoy.setAnio(dia.get(Calendar.YEAR));
            //

            if (((DtCliente) usuario).getActual() == null) {
                if (port.renovarSuscripcion(usuario.getNickname(), estado, cuota, fecha, fecha_venc, hoy)) {
                    DtUsuario usr = port.getDataUsuario(usuario.getNickname());
                    DtSuscripcion s = ((DtCliente) usr).getActual();
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
