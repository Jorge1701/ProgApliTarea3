<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>
        <title>Ranking de Usuarios</title>
    </head>
    <body style="background-image: url('media/wallpaper2.jpg')">

        <div class="container-fluid">
            <!-- Header -->
            <jsp:include page="header.jsp"/>
            <hr>
            <div class="row">

                <!-- Contenido -->
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">


                    <div class="panel panel-default">

                        <h2 style="text-align: center; font-weight: bold">Ranking <span class="glyphicon glyphicon-star" style="color: #ffff00;"></span><span class="glyphicon glyphicon-star" style="color: #ffff00;"></span><span class="glyphicon glyphicon-star" style="color: #ffff00;"></span></h2>   
                        <br>

                        <div class="table-responsive"> 
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <th>Usuario</th>
                                        <th>Cantidad de seguidores</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <%if (request.getAttribute("ranking") != null) {
                                            //DtRanking ranking = (DtRanking)request.getAttribute("ranking");
                                            //if(ranking.size() != 0){
                                        }%>
                                    <tr>
                                        <td style="font"><a href="/Tarea2/SConsultarPerfil?nickUs=lachiqui">Mirtha Legrand(lachiqui)</a></td>
                                        <td> 3</td>

                                    </tr>
                                    <tr>
                                        <td style="font"><a href="/Tarea2/SConsultarPerfil?nickUs=vpeople">Village People</a></td>
                                        <td> 1</td>

                                    </tr>


                                </tbody>
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
        <script src="scripts/ranking.js" type="text/javascript"></script>
    </body>
</html>
