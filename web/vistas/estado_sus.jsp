<%@page import="java.util.ArrayList"%>
<%@page import="servicios.DtSuscripcion"%>
<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <jsp:include page="../scripts/busqueda.html"/>



        <title>Administrar Suscripciones</title>
    </head>
    <body style="background-image: url('media/wallpaper2.jpg')">

        <div class="container-fluid">
            <!-- Header -->
            <jsp:include page="header.jsp"/>
            <hr>
            <br>
            <div class="row">

                <!-- Contenido -->
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">

                    <div class="panel panel-default">

                        <div class="container" id="info">

                            <div id="leyenda">
                                Leyenda:
                                <span style="margin-left: 5px; margin-right: 1px" class="glyphicon glyphicon-ok"></span>Renovar
                                <span style="margin-left: 5px; margin-right: 1px " class="glyphicon glyphicon-remove"></span>Cancelar
                            </div>
                        </div>

                        <hr>

                        <div class="table-responsive"> 
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <th>Estado</th>
                                        <th>Tipo de Cuota</th>
                                        <th>Monto</th>
                                        <th>Fecha</th>
                                        <th>Fecha Vencimiento</th>
                                        <th>Accion</th>
                                    </tr>
                                </thead>
                                <tbody>


                                    <% ArrayList<DtSuscripcion> sus = (ArrayList<DtSuscripcion>) request.getAttribute("suscripciones"); %>
                                    <%  for (DtSuscripcion dts : sus) {%>

                                    <tr>
                                        <td><%= dts.getEstado()%></td>
                                        <td><%= dts.getCuota()%></td>
                                        <td><%= dts.getMonto()%></td>
                                        <td><%= dts.getFecha()%></td>
                                        <td><%= dts.getFechaVenc()%></td>


                                        <% if (dts.getEstado().equals("Vencida")) {%>
                                        <td>
                                            <%if (request.getAttribute("suscripcion") == null) {%>
                                            <button onclick="renovarSuscripcion('<%= dts.getEstado()%>', '<%=dts.getCuota()%>', '<%=dts.getFecha()%>', '<%=dts.getFechaVenc()%>')" class="btn btn-default btn-xs">
                                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                            </button>
                                            <%}%>
                                            <button onclick="cancelarSuscripcion('<%= dts.getEstado()%>', '<%=dts.getCuota()%>', '<%=dts.getFecha()%>', '<%=dts.getFechaVenc()%>')" class="btn btn-default btn-xs">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </button>
                                        </td><%}
                                            }%>
                                    </tr>

                                    <% DtSuscripcion activa = (DtSuscripcion) request.getAttribute("suscripcion");%>
                                    <% if (activa != null) {%>
                                    <tr>
                                        <td><%= activa.getEstado()%></td>
                                        <td><%= activa.getCuota()%></td>
                                        <td><%= activa.getMonto()%></td>
                                        <td><%= activa.getFecha()%></td>
                                        <td><% if (activa.getFechaVenc() != null) {%>
                                            <%= activa.getFechaVenc()%> <% } else { %>
                                            No Corresponde <% } %>

                                            <% if (activa.getEstado().equals("Pendiente")) { %>
                                        <td>
                                            <button onclick="cancelarSuscripcion('Pendiente', '', '', '')" class="btn btn-default btn-xs">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </button>
                                        </td>
                                        <%}%>

                                        <%}%>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Lateral -->
                <div id="lateral" class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style="background-color: black; border-style:solid; border-width: 1px; padding: 15px; border-color: lavender">
                    <jsp:include page = "lateral.jsp"/>
                </div>
            </div>


            <!-- Footer -->
            <jsp:include page = "footer.jsp"/>
        </div>

        <script src="scripts/suscripcion.js" type="text/javascript"></script>
    </body>
</html>

