<%-- 
    Document   : crear_lista_reproduccion
    Created on : 07/10/2017, 11:09:53 AM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <jsp:include page="include.html"/>
        <title>Crear lista Reproduccion</title>
    </head>
    <body style="background-image: url('media/wallpaper2.jpg')">
        <div class="container-fluid">
            <jsp:include page="header.jsp"/>
            <hr>
            <div class="row">
                <!-- Contenido -->
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                    <!-- Relleno a la izquiera -->
                    <div class="col-lg-3 col-md-3 col-sm-2 col-xs-1"></div>

                    <div class="col-lg-6 col-md-6 col-sm-8 col-xs-10" style="background-color: transparent">

                        <div class="input-group input-group-lg" >
                            <h4 class="text-center" style="color:lavender ">Crear Lista de Reproduccion</h4>

                            <row class="col-xs-12 "><input style="border-color: black" required="Campo obligatorio" type="text" class="form-control" placeholder="NombreLista" id="txtNombreLst"></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="faltaNombre" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">Debes de ingresar un nombre</span>
                                </div>
                            </row>
                            
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="nombreAlerta" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >Nombre de lista ya existe.</span>
                                </div>
                            </row>
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="nombreSuccess" hidden>
                                    <span class="glyphicon glyphicon-ok" style="color: green"></span>
                                    <span style="color: green; padding-left: 5px" >Nombre disponible.</span>
                                </div>
                            </row>
                            
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="altaLstOK" hidden>
                                    <span class="glyphicon glyphicon-ok" style="color: green"></span>
                                    <span style="color: green; padding-left: 5px" >Se ingreso Lista Correctamente.</span>
                                </div>
                            </row>
                            
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="button" class="btn btn-default pull-right" id="btnCrearLst" style="margin-top: 8px" value="CrearLista"/></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> <div style="margin: 15px"></div></row>

                        </div>


                    </div>

                    <!-- Relleno a la derecha -->
                    <div class="col-lg-3 col-md-3 col-sm-2 col-xs-1"></div>
                </div>

                <!-- Lateral -->
                <div id="lateral" class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style="background-color: black; border-style:solid; border-width: 1px; padding: 15px; border-color: lavender">
                    <jsp:include page = "lateral.jsp"/>
                </div>

            </div>
            <!-- Footer -->
            <jsp:include page = "footer.jsp"/>
             <script src="scripts/md5.min.js" type="text/javascript"></script>
            <script src="scripts/altaLista.js" type="text/javascript"></script>
    </body>
</html>
