<%@page import="servicios.DtSuscripcion"%>
<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtArtista"%>
<%@page import="servicios.DtUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <jsp:include page="../scripts/inicio.html"/>

        <link rel="stylesheet" type="text/css" href="estilos/inicio.css">

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

                    <!-- Mensaje -->
                    <%if (request.getParameter("mensaje") != null) {%>
                    <div class="panelTexto">
                        <p class="text-center mensaje"><%= request.getParameter("mensaje").toString()%></p> 
                    </div>
                    <%}%>

                    <!-- Paneles -->
                    <div>
                        <ul class="nav nav-tabs">
                            <!-- class="active" marca la pestania seleccionada -->
                            <%
                                String pestania = "";

                                if (request.getAttribute("pestania") != null) {
                                    pestania = request.getAttribute("pestania").toString();
                                }

                                if (pestania.equals("")) {
                                    pestania = "Generos";
                                }
                            %>
                            <li <%= (pestania.equals("Generos") ? " class=\"active\"" : "")%>><a data-toggle="tab" href="#generos"><h3 class="pestania">Generos</h3></a></li>
                            <li <%= (pestania.equals("Artistas") ? " class=\"active\"" : "")%>><a data-toggle="tab" href="#artistas"><h3 class="pestania">Artistas</h3></a></li>
                            <li <%= (pestania.equals("Clientes") ? " class=\"active\"" : "")%>><a data-toggle="tab" href="#clientes"><h3 class="pestania">Clientes</h3></a></li>

                        </ul>

                        <div class="tab-content">

                            <!-- Generos -->
                            <div id="generos" class ="tab-pane fade <%= (pestania.equals("Generos") ? " in active" : "")%>">
                                <%
                                    // Cargar Generos
                                    ArrayList<String> generos = (ArrayList<String>) request.getAttribute("generos");

                                    if (generos.size() == 0) {
                                        // Mostrar mensaje si no hay generos
                                        out.print("<div class=\"panel panel-default\"><h1>No hay generos</h1></div>");
                                    } else {
                                        // Separador para que haya un margen arriba
                                        out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");

                                        // Recorrer generos
                                        for (int i = 0; i < generos.size(); i++) {
                                            if (i == 0 || i % 4 == 0) { // Cada 4 generos ir a una nueva fila (4 generos por columna)
                                                if (i != 0) {
                                                    out.print("</div>");
                                                }
                                                out.print("<div class=\"row\">");
                                            }
                                            out.print("<div class=\"col-lg-3\">"); // El 3 sale de 12 / generos por columnas
                                            out.print("<div class=\"panel panel-default\" onclick=\"irAGenero('" + generos.get(i) + "')\">"); // Llama a la funcion irAGenero con el nombre del genero
                                            out.print("<h1 class=\"text-center\">" + generos.get(i) + "</h1>"); // Muestra el nombre de genero
                                            out.print("</div>");
                                            out.print("</div>");
                                        }

                                        out.print("</div>");
                                    }
                                %>
                            </div>

                            <!-- Artistas -->
                            <div id="artistas" class ="tab-pane fade <%= (pestania.equals("Artistas") ? " in active" : "")%>">
                                <!-- Buscador -->
                                <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12" style="padding-top: 23px"></div>

                                <div class="col-lg-6 col-md-6 col-sm-4 col-xs-12" style="padding-top: 23px">
                                    <form action="/Tarea2/SInicio" method="GET">
                                        <div class="input-group input-group-lg">
                                            <input type="text" style="border-color: black" name="busqueda" class="form-control" placeholder="Buscar"

                                                   <%
                                                       if (request.getAttribute("busquedaArtista") != null) {
                                                           out.print("value=\"" + request.getAttribute("busquedaArtista") + "\"");
                                                       }
                                                   %>
                                                   >
                                            <input hidden type="text" name="pestania" value="Artistas">
                                            <div class="input-group-btn">
                                                <button class="btn btn-default" type="submit" style="border-color: black"><span class="glyphicon glyphicon-search"></span></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top: 23px"></div>

                                <%
                                    // Se obtienen los artistas
                                    ArrayList<DtUsuario> artistas = (ArrayList<DtUsuario>) request.getAttribute("artistas");

                                    // Si hay un usuario logueado, se quita al usuario logueado de la lista de artistas
                                    if (request.getSession().getAttribute("usuario") != null) {
                                        DtUsuario dtu = (DtUsuario) request.getSession().getAttribute("usuario");
                                        if (!(dtu instanceof DtCliente)) {
                                            for (int i = 0; i < artistas.size(); i++) {
                                                if (artistas.get(i).getNickname().equals(dtu.getNickname())) {
                                                    artistas.remove(i);
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    if (artistas.size() == 0) {
                                        //Si no hay resultados en la busqueda se muestra un mensaje
                                        if (request.getAttribute("busquedaArtista") != null) {
                                            out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");
                                            out.print("<div class=\"panel panel-default\"><h1>No hay resultados para \"" + request.getAttribute("busquedaArtista").toString() + "\" </h1></div>");
                                        } else {
                                            // Pero si no hay artistas se muestra otro mensaje
                                            out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");
                                            out.print("<div class=\"panel panel-default\"><h1>No hay artistas</h1></div>");
                                        }
                                    } else {
                                        // Separador al comienzo para dejar un margen
                                        out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");

                                        // Se obtienen los usuarios a los que sigue el usuario logueado
                                        // Si no hay usuario logueado o es un artista "seguidos" va a ser null
                                        ArrayList<DtUsuario> seguidos = null;
                                        if (request.getAttribute("seguidos") != null) {
                                            seguidos = (ArrayList<DtUsuario>) request.getAttribute("seguidos");
                                        }

                                        // Se recorren los artistas
                                        for (int i = 0; i < artistas.size(); i++) {
                                            DtUsuario a = artistas.get(i);

                                            if (i == 0 || i % 3 == 0) { // Cada 3 Artistas pasar a una nueva fila (3 columnas de artistas)
                                                if (i != 0) {
                                                    out.print("</div>");
                                                }
                                                out.print("<div class=\"row\">");
                                            }
                                            out.print("<div class=\"col-lg-4\">"); // El 4 sale de 12 / cantidad de columnas
                                            out.print("<div class=\"panel panel-default\" onclick=\"consultarPerfil('" + a.getNickname() + "')\">");
                                %>

                                <!-- Aqui va un perfil de Artista -->
                                <div class="container">
                                    <img src="/Tarea2/SImagen?usuario=<%= a.getImagen()%>" class="img-circle pull-left imgUsuario" width="80" height="80">
                                    <div class="pull-left infoUsuario">
                                        <text class="nombreUsuario"><%= a.getNombre() + " " + a.getApellido()%></text><br>
                                        <text><%= a.getNickname()%></text>
                                        <!-- Boton para seguir -->
                                        <%
                                            // Si hay un usuario logueado
                                            if (request.getSession().getAttribute("usuario") != null) {
                                                if (request.getAttribute("suscripcion") != null && ((DtSuscripcion) request.getAttribute("suscripcion")).getEstado().equals("Vigente")) {
                                                    DtUsuario dtu = (DtUsuario) request.getSession().getAttribute("usuario");
                                                    // Si el usuario logueado es un cliente
                                                    if (dtu instanceof DtCliente) {
                                                        boolean siguiendo = false;
                                                        // Si hay una lista de seguidos
                                                        if (seguidos != null) {
                                                            // Recorremos para ver si el usuario logueado sigue o no al usuario que estamos mostrando
                                                            for (int j = 0; j < seguidos.size(); j++) {
                                                                if (seguidos.get(j).getNickname().equals(a.getNickname())) {
                                                                    siguiendo = true;
                                                                    break;
                                                                }
                                                            }
                                                        }

                                                        out.print("<br>");

                                                        // Si no lo sigue
                                                        if (!siguiendo) {%>
                                        <form action="/Tarea2/SSeguir" method="POST">
                                            <input type="text" class="hidden" name="accion" value="seguir">
                                            <input type="text" class="hidden" name="seguido" value="<%=a.getNickname()%>">
                                            <input type="submit" class="btn btn-success btnSeguimiento" value="Seguir este artista">
                                        </form>
                                        <%} else {%>
                                        <form action="/Tarea2/SSeguir" method="POST">
                                            <input type="text" class="hidden" name="accion" value="dejarSeguir">
                                            <input type="text" class="hidden" name="seguido" value="<%=a.getNickname()%>">
                                            <input type="submit" class="btn btn-danger btnSeguimiento" value="Dejar de seguir">
                                        </form>
                                        <%
                                                        }
                                                    }
                                                }
                                            }
                                        %>
                                    </div>
                                </div>

                                <%
                                            out.print("</div>");
                                            out.print("</div>");
                                        }
                                        out.print("</div>");
                                    }
                                %>
                            </div>

                            <!-- Cientes -->
                            <div id="clientes" class ="tab-pane fade <%= (pestania.equals("Clientes") ? " in active" : "")%>">

                                <!-- Buscador -->
                                <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12" style="padding-top: 23px"></div>

                                <div class="col-lg-6 col-md-6 col-sm-4 col-xs-12" style="padding-top: 23px">
                                    <form action="/Tarea2/SInicio" method="GET">
                                        <div class="input-group input-group-lg">
                                            <input type="text" style="border-color: black" name="busqueda" class="form-control" placeholder="Buscar"

                                                   <%
                                                       if (request.getAttribute("busquedaCliente") != null) {
                                                           out.print("value=\"" + request.getAttribute("busquedaCliente") + "\"");
                                                       }
                                                   %>
                                                   >
                                            <input hidden type="text" name="pestania" value="Clientes">
                                            <div class="input-group-btn">
                                                <button class="btn btn-default" type="submit" style="border-color: black"><span class="glyphicon glyphicon-search"></span></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top: 23px"></div>

                                <%
                                    // Se obtienen los clientes
                                    ArrayList<DtUsuario> clientes = (ArrayList<DtUsuario>) request.getAttribute("clientes");

                                    // Misma logica que en la parte del artista
                                    if (request.getSession().getAttribute("usuario") != null) {
                                        DtUsuario dtu = (DtUsuario) request.getSession().getAttribute("usuario");
                                        if (dtu instanceof DtCliente) {
                                            for (int i = 0; i < clientes.size(); i++) {
                                                if (clientes.get(i).getNickname().equals(dtu.getNickname())) {
                                                    clientes.remove(i);
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    if (clientes.size() == 0) {
                                        //Si no hay resultados en la busqueda se muestra un mensaje
                                        if (request.getAttribute("busquedaCliente") != null) {
                                            out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");
                                            out.print("<div class=\"panel panel-default\"><h1>No hay resultados para \"" + request.getAttribute("busquedaCliente").toString() + "\" </h1></div>");
                                        } else {
                                            // Pero si no hay clientes se muestra otro mensaje
                                            out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");
                                            out.print("<div class=\"panel panel-default\"><h1>No hay clientes</h1></div>");
                                        }
                                    } else {
                                        // Separador al comienzo para dejar un margen
                                        out.print("<div class=\"row\"><div style=\"margin-top: 20px\"></div></div>");

                                        ArrayList<DtUsuario> seguidos = null;
                                        if (request.getAttribute("seguidos") != null) {
                                            seguidos = (ArrayList<DtUsuario>) request.getAttribute("seguidos");
                                        }

                                        // Misma logica que en generos y artistas
                                        for (int i = 0; i < clientes.size(); i++) {
                                            DtUsuario c = clientes.get(i);
                                            if (i == 0 || i % 3 == 0) {
                                                if (i != 0) {
                                                    out.print("</div>");
                                                }
                                                out.print("<div class=\"row\">");
                                            }
                                            out.print("<div class=\"col-lg-4\">");
                                            out.print("<div class=\"panel panel-default\" onclick=\"consultarPerfil('" + c.getNickname() + "')\">");
                                %>

                                <div class="container">
                                    <img src="/Tarea2/SImagen?usuario=<%= c.getImagen()%>" class="img-circle pull-left imgUsuario" width="80" height="80">
                                    <div class="pull-left infoUsuario">
                                        <text class="nombreUsuario"><%= c.getNombre() + " " + c.getApellido()%></text><br>
                                        <text><%= c.getNickname()%></text>

                                        <%
                                            // Misma logica que en artistas
                                            if (request.getSession().getAttribute("usuario") != null) {
                                                if (request.getAttribute("suscripcion") != null && ((DtSuscripcion) request.getAttribute("suscripcion")).getEstado().equals("Vigente")) {
                                                    DtUsuario dtu = (DtUsuario) request.getSession().getAttribute("usuario");
                                                    if (dtu instanceof DtCliente) {
                                                        boolean siguiendo = false;
                                                        if (seguidos != null) {
                                                            for (int j = 0; j < seguidos.size(); j++) {
                                                                if (seguidos.get(j).getNickname().equals(c.getNickname())) {
                                                                    siguiendo = true;
                                                                    break;
                                                                }
                                                            }
                                                        }

                                                        out.print("<br>");

                                                        // Si no lo sigue
                                                        if (!siguiendo) {%>
                                        <form action="/Tarea2/SSeguir" method="POST">
                                            <input type="text" class="hidden" name="accion" value="seguir">
                                            <input type="text" class="hidden" name="seguido" value="<%=c.getNickname()%>">
                                            <input type="submit" class="btn btn-success btnSeguimiento" value="Seguir este cliente">
                                        </form>
                                        <%} else {%>
                                        <form action="/Tarea2/SSeguir" method="POST">
                                            <input type="text" class="hidden" name="accion" value="dejarSeguir">
                                            <input type="text" class="hidden" name="seguido" value="<%=c.getNickname()%>">
                                            <input type="submit" class="btn btn-danger btnSeguimiento" value="Dejar de seguir">
                                        </form>
                                        <%
                                                        }
                                                    }
                                                }
                                            }
                                        %>

                                    </div>
                                </div>

                                <%
                                            out.print("</div>");
                                            out.print("</div>");
                                        }

                                        out.print("</div>");
                                    }
                                %>
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
