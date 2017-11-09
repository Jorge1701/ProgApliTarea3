<%@page import="java.util.ArrayList"%>
<%@page import="servicios.DtRanking"%>
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

                        <h2 style="text-align: center; font-weight: bold">Ranking </h2>   
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
                                            ArrayList<DtRanking> rankings = (ArrayList<DtRanking>) request.getAttribute("ranking");
                                            if (rankings.size() != 0) {
                                                for (DtRanking r : rankings) {%>
                                    <tr>
                                        <td><a href="/Tarea2/SConsultarPerfil?nickUs=<%=r.getNickname()%>"><%=r.getNombre()%></a></td>
                                        <td><%=r.getCantSeguidores()%></td>

                                    </tr>
                                    <%}
                                            }
                                        }%>

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
