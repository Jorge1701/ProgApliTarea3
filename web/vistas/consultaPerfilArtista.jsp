<%@page import="servicios.DtAlbum"%>
<%@page import="servicios.DtPerfilArtista"%>
<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtUsuario"%>
<%@page import="java.util.Collection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Perfil Artista</title>
    </head>
    <body style="background-image: url('media/wallpaper.jpg')">
        <%
            DtPerfilArtista dtPArtista = (DtPerfilArtista) request.getAttribute("dtPerfilArtista");
        %>

        <div class="container-fluid">
            <jsp:include page="header.jsp"/>
            <hr>
            <div class="row">

                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12"></div>
                <!-- Contenido -->
                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">

                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#home" style="color: black"><h5 class="pestaniaP">Informacion Basica</h5></a></li>
                        <li><a data-toggle="tab" href="#menu1" style="color: black"><h5 class="pestaniaP">Albumes Publicados</h5></a></li>
                        <li><a data-toggle="tab" href="#menu2" style="color: black"><h5 class="pestaniaP" >Seguidores</h5></a></li>                       


                    </ul>



                    <div class="tab-content panel panel-default">
                        <div id="home" class="tab-pane fade in active">
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <div class="col-sm-6">
                                            <div  align="center"> <img alt="User Pic" src="/Tarea2/SImagen?usuario=<%= dtPArtista.getInfo().getImagen()%>" id="profile-image1" class="img-circle img-responsive"> 

                                                <input id="profile-image-upload" class="hidden" type="file">
                                                <!--<div style="color:#999;" >click here to change profile image</div>
                                                Upload Image Js And Css-->
                                            </div>

                                            <br>

                                            <!-- /input-group -->
                                        </div>
                                        <div class="col-sm-6">
                                            <h4><%= dtPArtista.getInfo().getNombre()%>  <%= dtPArtista.getInfo().getApellido()%> </h4></span>
                                            <span><p>Artista</p></span>
                                        </div>
                                        <div class="clearfix"></div>
                                        <table class="table table-user-information">
                                            <tbody>
                                                <tr>
                                                    <td>NickName:</td>
                                                    <td><%= dtPArtista.getInfo().getNickname()%></td>
                                                </tr>
                                                <tr>
                                                    <td>Nombre</td>
                                                    <td><%= dtPArtista.getInfo().getNombre()%>  <%= dtPArtista.getInfo().getApellido()%> </td>
                                                </tr>
                                                <tr>
                                                    <td>Fecha Nacimiento:</td>
                                                    <td><%= dtPArtista.getInfo().getFechaNac().getDia()%>/<%= dtPArtista.getInfo().getFechaNac().getMes()%>/<%= dtPArtista.getInfo().getFechaNac().getAnio()%></td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td><%= dtPArtista.getInfo().getEmail()%></td>
                                                </tr>
                                                <tr>
                                                    <td>Sitio Web</td>
                                                    <td><%= dtPArtista.getWeb()%></td>
                                                </tr>
                                                <tr>
                                                    <td>Biografia</td>
                                                    <td><p><%= dtPArtista.getBiografia()%></p></td>
                                                </tr>

                                            </tbody>
                                        </table>


                                    </div>
                                </div>
                            </div>                           

                        </div>    
                        <div id="menu1" class="tab-pane fade">
                            <h3>Albumes Publicados</h3>                         
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 "> 
                                        <table class="table table-user-information">
                                            <thead>                                                      
                                                <tr>
                                                    <th>Nombre:</th>                                                                                                        
                                                </tr>
                                            </thead>
                                            <tbody>     
                                                <% Collection<DtAlbum> lstA = dtPArtista.getAlbumes();
                                                    for (DtAlbum dtA : lstA) {%>
                                                <tr>
                                                    <td><a href="/Tarea2/SContenido?accion=consultarAlbum&nickArtista=<%= dtPArtista.getInfo().getNickname()%>&nomAlbum=<%= dtA.getNombre()%>"><%= dtA.getNombre()%></a></td>

                                                </tr>

                                                <% }%>  
                                            </tbody>
                                        </table>
                                        <% if (session.getAttribute("usuario") != null) {
                                                DtUsuario u = (DtUsuario) session.getAttribute("usuario");
                                                if (dtPArtista.getInfo().getNickname().equals(u.getNickname())) {
                                        %>
                                        <h5><a href="/Tarea2/SContenido?accion=AltaAlbum" class="btn btn-info" id="btnCrearAlbum">Crear Album</a></h5>   
                                        <%}
                                                }%>


                                    </div>
                                </div>
                            </div>
                        </div>
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
                                                <% Collection<DtCliente> seguidores = dtPArtista.getSeguidores();
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
