<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>

        <title>Crea tu perfil de Espotify</title>
    </head>
    <body style="background-image: url('media/wallpaper2.jpg')">

        <div class="container-fluid">
            <!-- Header -->
            <jsp:include page="header.jsp"/>
            <hr>

            <div class="row">

                <!-- Contenido -->
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">

                    <!-- Relleno a la izquiera -->
                    <div class="col-lg-3 col-md-3 col-sm-2 col-xs-1"></div>


                    <div class="col-lg-6 col-md-6 col-sm-8 col-xs-10" style="background-color: transparent">

                        <div class="input-group input-group-lg" >
                            <h4 class="text-center" style="color:lavender ">Crea tu perfil</h4>

                            <row class="col-xs-12 "><input style="border-color: black" required="Campo obligatorio" type="text" class="form-control" placeholder="Nickname" id="txtNickname"></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="faltaNickname" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">Debes de ingresar un nickname.</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="formatoErroneo" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">El nickname no puede contener "@".</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="nicknameAlerta" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >Nickname ya existe.</span>
                                </div>
                            </row>
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="nicknameSuccess" hidden>
                                    <span class="glyphicon glyphicon-ok" style="color: green"></span>
                                    <span style="color: green; padding-left: 5px" >Nickname disponible.</span>
                                </div>
                            </row>
                            <row class="col-xs-12"><input style="border-color: black" required="Campo obligatorio"  type="email" class="form-control" placeholder="Email" id="txtEmail"></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="faltaEmail" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">Debes de ingresar un email.</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="formatoIncorrecto" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">El formato ingresado es incorrecto.</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="emailAlerta" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >Email ya en uso.</span>
                                </div>
                            </row>
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="emailSuccess" hidden>
                                    <span class="glyphicon glyphicon-ok" style="color: green"></span>
                                    <span style="color: green; padding-left: 5px" >Email disponible.</span>
                                </div>
                            </row>

                            <row class="col-xs-12"> <div style="margin: 20px"></div> </row>

                            <row class="col-xs-6"><input style="border-color: black" required="Campo obligatorio"  type="text" class="form-control" placeholder="Nombre" id="txtNombre"></row>
                            <row class="col-xs-6"> <input style="border-color: black" required="Campo obligatorio" type="text" class="form-control" placeholder="Apellido" id="txtApellido"></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="alertaNombre" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >No puedes dejar ninguno de estos campos en blanco.</span>
                                </div>
                            </row>

                            <span style="color: lavender; padding-left: 15px; font-weight: bold">Fecha de nacimiento:</span>
                            <div class="form-group"> 
                                <div class="col-xs-4">          
                                    <input style="border-color: black" required="Campo obligatorio" type="text" class="form-control" placeholder="Dia" id="txtDia">
                                </div>
                                <div class="col-xs-4 selectContainer">
                                    <select style="border-color: black" required="Campo obligatorio" class="form-control"  id="txtMes">
                                        <option value="mes">Mes</option>
                                        <option value="enero">Enero</option>
                                        <option value="febrero">Febrero</option>
                                        <option value="marzo">Marzo</option>
                                        <option value="abril">Abril</option>
                                        <option value="mayo">Mayo</option>
                                        <option value="junio">Junio</option>
                                        <option value="julio">Julio</option>
                                        <option value="agosto">Agosto</option>
                                        <option value="setiembre">Setiembre</option>
                                        <option value="octubre">Octubre</option>
                                        <option value="noviembre">Noviembre</option>
                                        <option value="diciembre">Diciembre</option>
                                    </select>
                                </div>
                                <div class="col-xs-4">
                                    <input style="border-color: black" required="Campo obligatorio"  type="text" class="form-control" placeholder="Año" id="txtAnio">
                                </div>
                            </div>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="alertaFecha" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >Indique una fecha valida.</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input style="border-color: black" required="Campo obligatorio" type="password" class="form-control" placeholder="Crea una contraseña" id="txtContrasenia"></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="faltaContrasenia" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">Debes de ingresar una contraseña.</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input style="border-color: black" required="Campo obligatorio"  type="password" class="form-control" placeholder="Confirma tu contraseña" id="txtConfContrasenia"></row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="alertaContrasenia" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >Las Contraseñas no coinciden.</span>
                                </div>
                            </row>


                            <row class="col-lg-5 col-md-5 col-sm-5 col-xs-5"> <div style="margin: 10px"></div> 
                                <div class="btn-group" data-toggle="buttons">

                                    <label class="btn btn-primary active" id="btnCliente" >
                                        <input type="radio" name="options" autocomplete="off"> Cliente
                                    </label>

                                    <label class="btn btn-primary" data-toggle="collapse" data-target="#datosArtistas" id="btnArtista">
                                        <input type="radio" name="options" autocomplete="off"> Artista
                                    </label>
                                </div>

                            </row>


                            <row class="col-lg-7 col-md-7 col-sm-7 col-xs-7"><div style="margin: 10px"></div>
                                <form enctype="multipart/form-data" method="post" id="attachfileform" name="formArchivo" >
                                    <input accept="image/*" type="file" name="archivo" id="archivo" class="btn btn-info" style="font-size: 11px"/>
                                    <input hidden type="text" name="accion" value="registro"/>
                                </form>
                            </row>

                            
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> <div style="margin: 10px"></div></row>

                            <div id="datosArtista" hidden>  
                                <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input style="border-color: black" type="text" class="form-control" placeholder="Biografia" id="txtBiografia"></row>
                                <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input style="border-color: black" class="form-control" placeholder="Link" id="txtLink"></row>
                            </div>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="button" class="btn btn-default pull-right" id="btnRegistro" style="margin-top: 8px" value="Registrarse"/></row>

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

        </div>
    </div>
    <script src="scripts/md5.min.js" type="text/javascript"></script>
    <script src="scripts/altaPerfil.js" type="text/javascript"></script>
</body>
</html>
