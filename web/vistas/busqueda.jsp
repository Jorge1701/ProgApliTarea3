<%@page import="java.net.URLEncoder"%>
<%@page import="Logica.DtTemaLocal"%>
<%@page import="Logica.DtTemaRemoto"%>
<%@page import="Logica.DtListaDefecto"%>
<%@page import="Logica.DtListaParticular"%>
<%@page import="Logica.DtLista"%>
<%@page import="Logica.DtTema"%>
<%@page import="Logica.DtAlbum"%>
<%@page import="Logica.DtArtista"%>
<%@page import="Logica.DtCliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <jsp:include page="../scripts/busqueda.html"/>
        <link rel="stylesheet" type="text/css" href="estilos/busqueda.css">

        <title>Espotify</title>

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
                        <%
                            ArrayList<Object> resultados = (ArrayList<Object>) request.getAttribute("resultado");
                        %>

                        <div class="container" id="info">
                            <div id="resultados">
                                <text><%= resultados.size()%> resultados</text>
                            </div>
                            <div id="opciones">
                                <a class="btn btn-default" style="border-color: black" href="/Tarea2/SBuscador?busqueda=<%= request.getAttribute("busqueda")%>&orden=alfa"><span class="glyphicon glyphicon-sort"></span>Alfabeticamente (A-Z a-z)</a>
                                <a class="btn btn-default" style="border-color: black" href="/Tarea2/SBuscador?busqueda=<%= request.getAttribute("busqueda")%>&orden=anio"><span class="glyphicon glyphicon-sort"></span>Año (descendente)</a>
                            </div>
                            <div id="leyenda">
                                <text>Leyenda:</text>
                                <text><span class="glyphicon glyphicon-book"></span>Album</text>
                                <text><span class="glyphicon glyphicon-list"></span>Lista</text>
                                <text><span class="glyphicon glyphicon-play-circle"></span>Tema</text>
                            </div>
                        </div>

                        <hr>

                        <%
                            if (resultados.size() == 0) {
                        %>

                        <p id="pResultados">No hay resultados con la busqueda "<%= request.getAttribute("busqueda")%>"</p>

                        <%} else {%>

                        <table class="table-hover">
                            <%
                                for (Object o : resultados) {
                            %>

                            <%
                                if (o instanceof DtAlbum) {
                                    DtAlbum album = (DtAlbum) o;
                            %>
                            <tr onclick="irAlbum('<%= album.getNickArtista()%>', '<%= URLEncoder.encode(album.getNombre(), "UTF-8")%>')">
                                <td>
                                    <span class="glyphicon glyphicon-book"></span>
                                </td>
                                <td>
                                    <%= album.getNombre()%> (<%= album.getAnio()%>)
                                </td>

                                <%} else if (o instanceof DtTema) {%>
                                <%
                                    if (o instanceof DtTemaRemoto) {
                                        DtTemaRemoto temaRemoto = (DtTemaRemoto) o;
                                %>
                            <tr onclick="reproducirRemoto('<%= temaRemoto.getUrl()%>')">
                                <td>
                                    <span class="glyphicon glyphicon-play-circle"></span>
                                </td>
                                <td>
                                    <%= temaRemoto.getNombre()%>
                                </td>

                                <% } else if (o instanceof DtTemaLocal) {
                                    DtTemaLocal temaLocal = (DtTemaLocal) o;
                                %>
                            <tr onclick="reproducirLocal('<%= temaLocal.getDirectorio().replace("'", "\\'")%>', '<%= temaLocal.getNombre().replace("'", "\\'")%>', '<%= temaLocal.getArtista().replace("'", "\\'")%>', '<%= temaLocal.getImagenAlbum().replace("'", "\\'")%>')">
                                <td>
                                    <span class="glyphicon glyphicon-play-circle"></span>
                                </td>
                                <td>
                                    <%= temaLocal.getNombre()%>
                                </td>

                                <%}%>
                                <%} else if (o instanceof DtLista) {
                                    if (o instanceof DtListaParticular) {
                                        DtListaParticular listaParticular = (DtListaParticular) o;
                                %>
                            <tr onclick="irListaParticular('<%= URLEncoder.encode(listaParticular.getNombre(), "UTF-8")%>', '<%=listaParticular.getNickDuenio().replace("'", "\\'")%>')">
                                <td>
                                    <span class="glyphicon glyphicon-list"></span>
                                </td>
                                <td>
                                    <%= listaParticular.getNombre()%> (<%= listaParticular.getFecha().getAnio()%>)
                                </td>

                                <%} else if (o instanceof DtListaDefecto) {
                                    DtListaDefecto listaDefecto = (DtListaDefecto) o;
                                %>
                            <tr onclick="irListaDefecto('<%= URLEncoder.encode(listaDefecto.getNombre(), "UTF-8")%>', '<%= URLEncoder.encode(listaDefecto.getGenero().getNombre(), "UTF-8")%>')">
                                <td>
                                    <span class="glyphicon glyphicon-list"></span>
                                </td>
                                <td >
                                    <%= listaDefecto.getNombre()%> (<%= listaDefecto.getFecha().getAnio()%>)
                                </td>

                                <%}%>
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