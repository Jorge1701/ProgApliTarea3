<%@page import="servicios.DtListaDefecto"%>
<%@page import="servicios.DtListaParticular"%>
<%@page import="servicios.DtTemaLocal"%>
<%@page import="servicios.DtTemaRemoto"%>
<%@page import="servicios.DtLista"%>
<%@page import="servicios.DtTema"%>
<%@page import="servicios.DtAlbum"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>

        <link rel="stylesheet" type="text/css" href="estilos/busqueda.css">

        <title>Espotify</title>

    </head>
    <body style="background-image: url('media/wallpaper.jpg')">
        <div class="container-fluid">
            <!-- Header -->
            <jsp:include page="header.jsp"/>
            <hr>
            <br>
            <div class="row">
                <!-- Contenido -->
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                    <div class="panel panel-default">
                        <%
                            DtLista lista = ((DtLista) request.getAttribute("lista"));
                        %>
                        <div class="container" id="info">
                            <img id="imgLista" src="/Tarea2/SImagen?lista=<%= lista.getImagen()%>" class="pull-left" width="30" height="30">
                            <div id="resultados"><text><%= lista.getNombre()%></text></div>
                            <div id="resultados">
                                <text><%= lista.getTemas().size()%> temas</text>
                            </div>
                            <div id="resultados">
                                <%
                                    if (lista instanceof DtListaParticular) {
                                %>
                                <text><%= "Dueño: " + ((DtListaParticular) lista).getNickDuenio()%></text>
                                <% } %>
                            </div>
                            <br><br>
                            <div id="leyenda">
                                <text>Leyenda:</text>
                                <text><span class="glyphicon glyphicon-cloud"></span>Remoto</text>
                                <text><span class="glyphicon glyphicon-floppy-disk"></span>Local</text>
                            </div>
                            <%
                                if (lista instanceof DtListaParticular) {
                                    if (((DtListaParticular) lista).isPrivada()) {%>
                                    <text style="padding-left: 200px;float: left;padding-top: 8px">Esta lista es privada</text>
                            <%}
                                } %>
                        </div>
                        <hr>
                        <%
                            if (lista.getTemas().size() == 0) {
                        %>
                        <p id="pResultados">No hay temas en la lista</p>
                        <%} else {%>
                        <table class="table-hover">
                            <%
                                for (Object tema : lista.getTemas()) {
                                    if (tema instanceof DtTemaRemoto) {
                                        DtTemaRemoto temaRemoto = (DtTemaRemoto) tema;
                            %>
                            <tr onclick="reproducirRemoto('<%= temaRemoto.getUrl()%>', '<%= temaRemoto.getArtista().replace("'", "\\'")%>', '<%= temaRemoto.getAlbum().replace("'", "\\'")%>', '<%= temaRemoto.getNombre().replace("'", "\\'")%>')">
                                <td>
                                    <span class="glyphicon glyphicon-play-circle"></span>
                                </td>
                                <td>
                                    <span class="glyphicon glyphicon-cloud"></span>
                                </td>

                                <td>

                                    <%= temaRemoto.getNombre()%>
                                </td>
                                <% } else if (tema instanceof DtTemaLocal) {
                                    DtTemaLocal temaLocal = (DtTemaLocal) tema;
                                %>
                            <tr onclick="reproducirLocal('<%= temaLocal.getDirectorio().replace("'", "\\'")%>', '<%= temaLocal.getNombre().replace("'", "\\'")%>', '<%= temaLocal.getArtista().replace("'", "\\'")%>', '<%= temaLocal.getImagenAlbum().replace("'", "\\'")%>', '<%= temaLocal.getAlbum().replace("'", "\\'")%>')">
                                <td>
                                    <span class="glyphicon glyphicon-play-circle"></span>
                                </td>
                                <td>
                                    <span class="glyphicon glyphicon-floppy-disk"></span>
                                </td>

                                <td>
                                    <%= temaLocal.getNombre()%>
                                </td>
                                <%}%>
                            </tr>

                            <%}%>
                        </table>
                        <%}%>
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

    </body>
</html>