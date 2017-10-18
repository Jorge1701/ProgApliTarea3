<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <jsp:include page="../scripts/inicio.html"/>

        <link rel="stylesheet" type="text/css" href="estilos/pagina_error.css">

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
                    <div class="panelTexto">
                        <p class="text-center mensaje"><%= request.getAttribute("mensaje_error")%></p> 
                    </div>
                    <div>
                        <a href="/Tarea2/SInicio" class="btn btn-default btn-lg btn-block">Ir al Inicio</a>
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