
<%@page import="servicios.DtAlbum"%>
<%@page import="servicios.DtListaDefecto"%>
<%@page import="servicios.DtTema"%>
<%@page import="servicios.DtLista"%>
<%@page import="servicios.DtUsuario"%>
<%@page import="servicios.DtListaParticular"%>
<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtPerfilCliente"%>
<%@page import="java.util.Collection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/> 
        <jsp:include page="../scripts/busqueda.html"/>
        <link rel="stylesheet" type="text/css" href="estilos/inicio.css">
        <title>Consulta perfil Cliente</title>
    </head>
    <body style="background-image: url('media/wallpaper2.jpg')">
        <%
            DtPerfilCliente dtPCliente = (DtPerfilCliente) request.getAttribute("DtPerfilCliente");
            DtCliente dtCli = null;
            if (dtPCliente.getInfo() instanceof DtCliente) {
                dtCli = (DtCliente) dtPCliente.getInfo();
            }
        %>
        <div class="container-fluid">
            <jsp:include page="header.jsp"/>
            <hr>
            <div class="row">

                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12"></div>
                <!-- Contenido -->
                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">

                    <%
                        String pestania = "";
                        if (request.getAttribute("pestania") != null) {
                            pestania = request.getAttribute("pestania").toString();
                        }
                    %>

                    <ul class="nav nav-tabs" >
                        <li <%= (pestania.equals("") ? " class=\"active\"" : "")%>><a data-toggle="tab" href="#home" style="color: black"><h5 class="pestaniaP">Informacion Basica</h5></a></li>
                        <li <%= (pestania.equals("Listas") ? " class=\"active\"" : "")%>><a data-toggle="tab" href="#menu1" style="color: black"><h5 class="pestaniaP">Listas</h5></a></li>
                            <% if (session.getAttribute("usuario") != null) {
                            %>
                        <li><a data-toggle="tab" href="#menu2" style="color: black"><h5 class="pestaniaP">Seguidores</h5></a></li>


                        <%if (dtCli.getActual() != null && dtCli.getActual().getEstado() != null && dtCli.getActual().getEstado().equals("Vigente")) { %>
                        <li><a data-toggle="tab" href="#menu6" style="color: black"><h5 class="pestaniaP">Sigue</h5></a></li>
                            <%}%> 
                        <li><a data-toggle="tab" href="#menu3" style="color: black"><h5 class="pestaniaP">Album Favoritos</h5></a></li>
                        <li><a data-toggle="tab" href="#menu4" style="color: black"><h5 class="pestaniaP">Listas Favoritos</h5></a></li>
                        <li><a data-toggle="tab" href="#menu5" style="color: black"><h5 class="pestaniaP">Temas Favoritos</h5></a></li>
                            <%}%>
                    </ul>

                    <div class="tab-content panel panel-default">
                        <div id="home" class="tab-pane fade <%= (pestania.equals("") ? " in active" : "")%>">
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <div class="col-sm-6">
                                            <div  align="center"> <img alt="User Pic" src="/Tarea2/SImagen?usuario=<%= dtPCliente.getInfo().getImagen()%>" id="profile-image1" class="img-circle img-responsive"> 

                                                <input id="profile-image-upload" class="hidden" type="file">
                                                <!--<div style="color:#999;" >click here to change profile image</div>
                                                Upload Image Js And Css-->
                                            </div>

                                            <br>

                                            <!-- /input-group -->
                                        </div>
                                        <div class="col-sm-6" >

                                            <h4 style="color:black;"><%= dtPCliente.getInfo().getNombre()%>  <%= dtPCliente.getInfo().getApellido()%> </h4></span>
                                            <span><p>Cliente</p></span>

                                        </div>
                                        <div class="clearfix"></div>
                                        <table class="table table-user-information">
                                            <tbody>
                                                <tr>
                                                    <td>NickName:</td>
                                                    <td><%= dtPCliente.getInfo().getNickname()%></td>
                                                </tr>
                                                <% if (session.getAttribute(
                                                            "usuario") != null) {%>
                                                <tr>
                                                    <td>Nombre</td>
                                                    <td><%= dtPCliente.getInfo().getNombre()%>  <%= dtPCliente.getInfo().getApellido()%> </td>
                                                </tr>
                                                <tr>
                                                    <td>Fecha Nacimiento:</td>
                                                    <td><%= dtPCliente.getInfo().getFechaNac().getDia()%>/<%= dtPCliente.getInfo().getFechaNac().getMes()%>/<%= dtPCliente.getInfo().getFechaNac().getAnio()%></td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td><%= dtPCliente.getInfo().getEmail()%></td>
                                                </tr>                                                 
                                                <%}%>
                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>                           

                        </div>    
                        <div id="menu1" class="tab-pane fade <%= (pestania.equals("Listas") ? " in active" : "")%>">
                            <% pestania = ""; %>
                            <h3>Listas Creadas</h3>                         
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>Nombre:</th>
                                                    <th>Cantidad de Temas</th> 
                                                    <th>Pública</th>
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtListaParticular> listasP = dtPCliente.getListasCreadas();
                                                    for (DtListaParticular dtLP : listasP) {
                                                        if (session.getAttribute("usuario") == null || !((DtUsuario) session.getAttribute("usuario")).getNickname().equals(dtPCliente.getInfo().getNickname())) {
                                                            if (!dtLP.isPrivada()) {%>
                                                <tr>                                                    
                                                    <td onclick="irListaParticular('<%= dtLP.getNombre().replace("'", "\\'")%>', '<%=dtLP.getNickDuenio().replace("'", "\\'")%>')"><a><%= dtLP.getNombre()%></a></td>
                                                    <td><span class="badge"> <%= dtLP.getTemas().size()%> </span></td>
                                                    <td><div class="glyphicon glyphicon-ok-sign" style="color:green"></div></td>
                                                </tr>
                                                <%}
                                                } else {%>
                                                <tr>                                                    
                                                    <td onclick="irListaParticular('<%= dtLP.getNombre().replace("'", "\\'")%>', '<%=dtLP.getNickDuenio().replace("'", "\\'")%>')"><a><%= dtLP.getNombre()%></a></td>
                                                    <td><span class="badge"> <%= dtLP.getTemas().size()%> </span></td>
                                                    <% if (dtLP.isPrivada()) {%>
                                                    <td id="privada"><form method="POST" action="/Tarea2/SContenido"><div class="glyphicon glyphicon-remove-sign" style="color: red"></div><input type="text" class="hidden" name="accion" value="publicarLista"><input name="nomLista" hidden value="<%= dtLP.getNombre()%>"><button type="submit" id="btnPublicar" class="btn btn-info" style="margin-left: 50px" >Publicar</button></form></td>
                                                            <% } else {%>
                                                    <td><div class="glyphicon glyphicon-ok-sign" style="color:green"></div></td>
                                                        <%}%>
                                                </tr>

                                                <%}
                                                    }%>  
                                            </tbody>
                                        </table>
                                        <% if (session.getAttribute("usuario") != null) {
                                                DtUsuario usSesion = (DtUsuario) session.getAttribute("usuario");
if (usSesion.getNickname().equals(dtCli.getNickname()) && dtCli.getActual()!= null && dtCli.getActual().getEstado() != null && dtCli.getActual().getEstado().equals("Vigente")) {%>
                                        <h5><a href="/Tarea2/SLista" class="btn btn-info" id="btnCrearLista">Crear Lista Reproduccion</a> </h5> 
                                        <%}
                                            }%>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <% if (session.getAttribute(
                                    "usuario") != null) {%>                    
                        <div id="menu2" class="tab-pane fade">
                            <h3>Seguidores</h3>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>NickName:</th>
                                                    <th>Nombre</th>                                                        
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtCliente> seguidores = dtPCliente.getSeguidores();
                                                    for (DtCliente dtC : seguidores) {%>
                                                <tr>
                                                    <td><a href="/Tarea2/SConsultarPerfil?nickUs=<%= dtC.getNickname()%>"><%= dtC.getNickname()%></a></td>
                                                    <td><%= dtC.getNombre()%>  <%= dtC.getApellido()%> </td>
                                                </tr>

                                                <% }%>  
                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu3" class="tab-pane fade">
                            <h3>Abumes Favoritos</h3>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>Artista:</th>
                                                    <th>Nombre</th>
                                                    <th>Año</th>
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtAlbum> albumes = dtPCliente.getAlbumes();
                                                    for (DtAlbum dtA : albumes) {%>
                                                <tr>
                                                    <td><a href="/Tarea2/SConsultarPerfil?nickUs=<%= dtA.getNickArtista()%>"><%= dtA.getNickArtista()%></a></td>
                                                    <td><%= dtA.getNombre()%> </td>
                                                    <td><%= dtA.getAnio()%> </td>
                                                </tr>

                                                <% }%>  
                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu4" class="tab-pane fade">
                            <h3>Listas Favoritos</h3>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>Nombre:</th>
                                                    <th>Cantidad de Temas</th>
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtLista> dtLF = dtPCliente.getListasFavoritas();
                                                    for (DtLista dtL : dtLF) {%>
                                                <tr>
                                                    <%if (dtL instanceof DtListaParticular) {%>
                                                    <td onclick="irListaParticular('<%= dtL.getNombre().replace("'", "\\'")%>', '<%=((DtListaParticular) dtL).getNickDuenio().replace("'", "\\'")%>')"><a><%= dtL.getNombre()%></a></td>
                                                            <% } else {%> 

                                                    <td onclick="irListaDefecto('<%= dtL.getNombre().replace("'", "\\'")%>', '<%=((DtListaDefecto) dtL).getGenero().getNombre().replace("'", "\\'")%>')"><a><%= dtL.getNombre()%></a></td>
                                                            <%}%>
                                                    <td><span class="badge"> <%= dtL.getTemas().size()%> </span></td>
                                                </tr>
                                                <% }%>  
                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu5" class="tab-pane fade">
                            <h3>Temas Favoritos</h3>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>Nombre:</th>
                                                    <th>Duracion</th>
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtTema> dtTemasF = dtPCliente.getTemas();
                                                    for (DtTema dtT : dtTemasF) {%>
                                                <tr>
                                                    <td><%= dtT.getNombre()%></td>
                                                    <td><span class="badge"> <%= dtT.getDuracion().toString()%> </span></td>
                                                </tr>

                                                <% }%>  
                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            if (dtCli.getActual()!= null && dtCli.getActual().getEstado() != null && dtCli.getActual().getEstado().equals("Vigente")) {
                        %>                    
                        <div id="menu6" class="tab-pane fade">
                            <h3>Sigue</h3>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>NicName:</th>
                                                    <th>Nombre:</th>
                                                    <th>Perfil:</th>
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtUsuario> dtUs = dtPCliente.getSeguidos();
                                                    for (DtUsuario dtU : dtUs) {%>
                                                <tr>
                                                    <td><a href="/Tarea2/SConsultarPerfil?nickUs=<%= dtU.getNickname()%>"><%= dtU.getNickname()%></a></td>
                                                    <td><%= dtU.getNombre()%>  <%= dtU.getApellido()%> </td>
                                                    <% if (dtU instanceof DtCliente) { %>
                                                    <td>Cliente</td>
                                                    <%} else {%>
                                                    <td>Artista</td>
                                                    <% }%>
                                                </tr>

                                                <% }%>  
                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <%}
                            }%>                    
                    </div>


                </div>

                <!-- Lateral -->
                <div id="lateral" class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style="background-color: black; border-style:solid; border-width: 1px; padding: 15px; border-color: lavender">
                    <jsp:include page = "lateral.jsp"/>
                </div>
            </div>

        </div>

    </body>
</html>
