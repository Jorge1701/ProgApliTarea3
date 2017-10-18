<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.SSesion"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <jsp:include page="../scripts/header.html"/>
        <link rel="stylesheet" type="text/css" href="estilos/header.css">
    </head>
    <body>
        <div class="row" style="padding-top: 20px">
            <!-- Logo y nombre -->
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12" style="padding-top: 15px">
                <div class="container" onclick="irInicio()">
                    <img src="media/icono.png" class="pull-left" width="80" height="80">
                    <h1 class="pull-left" style="padding-left: 5px ; color: lavender">Espotify</h1>
                </div>
            </div>

            <!-- Buscador -->
            <div class="col-lg-5 col-md-5 col-sm-4 col-xs-12" style="padding-top: 35px">
                <form action="/Tarea2/SBuscador" method="GET">
                    <div class="input-group input-group-lg">
                        <input type="text" style="border-color: black" name="busqueda" class="form-control" placeholder="Tema, Lista o Album"
                               <%
                                   if (request.getAttribute("busqueda") != null) {
                                       out.print("value=\"" + request.getAttribute("busqueda") + "\"");
                                   }
                               %>
                               >
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit" style="border-color: black"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                </form>
            </div>



            <%
                DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
                boolean logeado = usuario != null;

                if (logeado) {
                    String nombre = usuario.getNombre() + " " + usuario.getApellido();
            %>

            <!-- Perfil de Usuario -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" style="padding-top: 5px"  id="formPU">
                <div class="panelInformacion">
                    <div class="row">

                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                            <img src="/Tarea2/SImagen?usuario=<%= usuario.getImagen()%>" class="img-circle pull-left" width="65" height="65" style="margin-right: 20px" id="puImagen">
                            <div style="margin: 0px; color:lavender; text-shadow: 2px 2px 4px #000000" id="puNombre"><a href="/Tarea2/SConsultarPerfil?nickUs=<%= usuario.getNickname()%>"><%= nombre%></a></div>
                                <% if (usuario instanceof DtCliente) { %>
                            <div> <a href="/Tarea2/SSuscripcion?accion=redir" class="btn btn-link btn-xs">Contratar Suscripción</a></div>
                            <div><a href="/Tarea2/SSuscripcion?accion=redir1" class="btn btn-link btn-xs">Estado Suscripciones</a></div>
                            <% } %>
                            <div><a href="/Tarea2/SInicio?cargarDatosPrueba=si" class="btn btn-link btn-xs">Cargar Datos de Prueba</a></div>

                            <form action="/Tarea2/SSesion" method="POST">
                                <input type="text" class="hidden" name="accion" value="cerrarSesion">
                                <input type="submit" class="btn btn-link btn-xs" value="Cerrar Sesion">
                            </form>
                        </div> 
                    </div>
                </div>
            </div>

            <% }
                if (!logeado) {
            %>

            <!-- Opciones -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" style="padding-top: 10px">
                <div class="container">
                    <a href="/Tarea2/SSesion?accion=redirigir" class="btn btn-default" id="btnSesion" style="margin-left: 50px; border-color: black">Iniciar Sesion</a>
                    <a href="/Tarea2/SRegistro?accion=redirigir" class="btn btn-default" id="btnRegistrarse" style="margin: 20px; border-color: black ">Registrarse</a>
                </div>
            </div>

            <% }%>

        </div>
    </body>
</html>
