<%@page import="servicios.DtTemaRemoto"%>
<%@page import="servicios.DtTemaLocal"%>
<%@page import="servicios.DtSuscripcion"%>
<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtUsuario"%>
<%@page import="servicios.DtTema"%>
<%@page import="servicios.DtAlbumContenido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@page import="servicios.DtAlbum"%>
<%@page import="servicios.DtPerfilArtista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <jsp:include page="../scripts/Descargar.html"/>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Album</title>
    </head>
    <body>
        <%

            DtAlbumContenido albumes = (DtAlbumContenido) request.getAttribute("Album");
            DtAlbum inf = (DtAlbum) albumes.getInfo();
            ArrayList<String> Generos = (ArrayList) albumes.getGeneros();
            ArrayList<DtTema> temas = (ArrayList) albumes.getTemas();
            String nickArtista = inf.getNickArtista();
            String imagen = inf.getImagen();
            String nombreAlbum = inf.getNombre();
            int anioCreacion = inf.getAnio();

        %>


        <div class="container-fluid">
            <jsp:include page="header.jsp"/>
            <hr>
            <div class="row">

                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12"></div>
                <!-- Contenido -->
                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                    <ul class="nav nav-tabs ">
                        <li class="active"><a data-toggle="tab" href="#home" style="color: black"><h5 class="pestaniaP">Informacion Basica</h5></a></li>
                        <li><a data-toggle="tab" href="#menu1" style="color: black"><h5 class="pestaniaP">Temas</h5></a></li>
                    </ul>
                    <div class="tab-content panel panel-default" style="color: white">
                        <div id="home" class="tab-pane fade in active">                
                            <div class="panel-body ">
                                <div class="row "  >
                                    <div class=" col-md-10 col-lg-10 " >
                                        <div class="col-sm-6 centrar">
                                            <div   align="center"> <img height="250" width="250" alt="Album Pic" src="/Tarea2/SImagen?album=<%= imagen%>" id="album-imagen" class="img-circle img-responsive"> 
                                                <input id="profile-image-upload" class="hidden" type="file">
                                            </div>

                                            <td><h4 style="color: black">Nombre Album : <%= nombreAlbum%></h4></td>
                                            <td><h4 style="color: black">Año De Creacion : <%= anioCreacion%> </h4></td>
                                            <td><h4 style="color: black">Generos: <%=Generos%></h4></td>
                                        </div>
                                    </div>          
                                </div> 
                            </div>
                        </div>
                        <%-- Temas --%>

                        <div id="menu1" class="table-responsive tab-pane">     
                            <div>
                                <table class="table table-condensed" >
                                    <caption ><center><text style="color:black ">Temas</text></center></caption>
                                    <tr>
                                        <th><center><text style="color:black ">Nombre</text></center></th>                                  
                                    <th><center><text style="color:black ">Duracion</text></center></th>
                                    <th><center><text style="color:black ">Ubicacion</text></center></th>
                                    <th><center><text style="color:black ">Reproducir</text></center></th>
                                    <th><center><text style="color:black ">Cant Descargas</text></center></th>                            
                                    <th><center><text style="color:black ">Descargar</text></center></th>
                                        <% for (int i = 0; i < temas.size(); i++) {%>
                                    <tr>                              
                                        <td><text style="color:black"><center><%= temas.get(i).getNombre()%></center> </text></td>
                                    <td><text style="color:black  ; background-color: white"><center> <%= temas.get(i).getDuracion().getHoras()%>:<%= temas.get(i).getDuracion().getMinutos()%>:<%= temas.get(i).getDuracion().getSegundos()%></center></text></td>
                                    <td><text style="color:black ; background-color: white"><center> <%= temas.get(i) instanceof DtTemaLocal ? ((DtTemaLocal) temas.get(i)).getDirectorio() : ((DtTemaRemoto) temas.get(i)).getUrl()%></center> </text></td>
                                        <%  if (temas.get(i) instanceof DtTemaLocal) {
                                                DtTemaLocal local = (DtTemaLocal) temas.get(i);%>
                                    <td><center><button type="button" class="btn btn-default" aria-label="Left Align">
                                            <span class="glyphicon glyphicon-play-circle" aria-hidden="true"  onclick="reproducirLocal('<%= local.getDirectorio().replace("'", "\\'")%>', '<%= local.getNombre().replace("'", "\\'")%>', '<%= local.getArtista().replace("'", "\\'")%>', '<%= local.getImagenAlbum().replace("'", "\\'")%>', '<%= local.getAlbum().replace("'", "\\'")%>')"></span>
                                        </button></center></td>
                                        <%} else {
                                            DtTemaRemoto temaRemoto = (DtTemaRemoto) temas.get(i);%>
                                    <td><center><button type="button" class="btn btn-default" aria-label="Left Align">
                                            <span class="glyphicon glyphicon-play-circle" aria-hidden="true"  onclick="reproducirRemoto('<%= temaRemoto.getUrl()%>', '<%= temaRemoto.getArtista().replace("'", "\\'")%>', '<%= temaRemoto.getAlbum().replace("'", "\\'")%>', '<%= temaRemoto.getNombre().replace("'", "\\'")%>')"></span></button></center></td>
                                            <% }
                                                if (temas.get(i) instanceof DtTemaLocal) {%>
                                                <td><center><span class="badge"> <%= ((DtTemaLocal) temas.get(i)).getDescargas()%> </span></center></td>
                                    <% }else {%>
                                <td><center><span class="badge">0</span></center></td>
                                    <% } %>

                                    <%
                                        if (request.getSession().getAttribute("usuario") != null) {
                                            DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario");
                                            if (user instanceof DtCliente) {
                                                DtSuscripcion suscripcion = (DtSuscripcion) ((DtCliente) user).getActual();
                                                if (suscripcion != null) {
                                                    if (suscripcion.getEstado().equals("Vigente")) {
                                                        if (temas.get(i) instanceof DtTemaLocal) {
                                    %>

                                    <td><center><input readonly onclick="Descarga('<%=((DtTemaLocal) temas.get(i)).getDirectorio()%>', '<%= ((DtTemaLocal) temas.get(i)).getArtista()%>', '<%=((DtTemaLocal) temas.get(i)).getAlbum()%>', '<%=((DtTemaLocal) temas.get(i)).getNombre()%>')" class="btn btn-info" id="btnDescargar" value="Descargar"></center></td>

                                    <% } else {%>
                                    <td><center><text style="color:black ">No Se Puede Descargar</text></center></td> 

                                    <%}%>
                                    <%} else { %>
                                    <td><center><a href="/Tarea2/SSuscripcion?accion=redir1" class="btn btn-info">Renovar Suscripción</a></center></td>


                                    <%}%>
                                    <%} else { %>
                                    <td><center><a href="/Tarea2/SSuscripcion?accion=redir" class="btn btn-info">Modificar Suscripción</a></center></td>                                   
                                        <% }
                                        } else { %>
                                    <td><center><text style="color:black ">No Se Puede Descargar</text></center></td>   

                                    <% }
                                    } else { %> 
                                    <td><center><text style="color:black ">Debe Iniciar Sesion</text></center></td>     
                                    </tr>
                                    <% }
                                        }%>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Lateral -->
                <div id="lateral" class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style=" background-color: #00cc66 ; border-style:solid; border-width: 1px; padding: 15px; border-color: lavender">
                    <jsp:include page = "lateral.jsp"/>
                </div>

            </div>

            <!-- Footer -->
            <jsp:include page = "footer.jsp"/>
        </div>

    </body>
</html>
