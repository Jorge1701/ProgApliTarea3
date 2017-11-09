<%@page import="servicios.DtCliente"%>
<%@page import="servicios.DtArtista"%>
<%@page import="servicios.DtUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servicios.DtGenero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.html"/>


        <%
            if (request.getSession().getAttribute("usuario") == null) {
                request.setAttribute("mensaje_error", "Esta página está reservada para nuestros Artistas ");
                request.getRequestDispatcher("pagina_error.jsp").forward(request, response);
            }%>
        <%
            DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario");
            if (user instanceof DtCliente) {
                request.setAttribute("mensaje_error", "Esta página está reservada para nuestros Artistas ");
                request.getRequestDispatcher("pagina_error.jsp").forward(request, response);

            }%>

        <title>Espotify</title>

        <style>


        </style>
    </head>
    <body>
        <div class="container-fluid">
            <!-- Header -->
            <jsp:include page="header.jsp"/>



            <input type="hidden" id="nick" value="<%= user.getNickname()%>">
            <hr>

            <div class="row">
                <!-- Contenido -->
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                    <!-- Relleno a la izquiera -->
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-1"></div>

                    <div class="col-lg-6 col-md-6 col-sm-8 col-xs-10" style="background-color: transparent">

                        <div class="input-group input-group-lg" >
                            <h4 class="text-center col-xs-10" style="color:white ">Crea Tu Album</h4>
                            <!-- Nombre  -->
                            <row class="col-xs-10 "><input style="border-color: black" required="Campo obligatorio" type="text" class="form-control" placeholder="Nombre" id="txtnombreAlbum"></row>
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="faltaNombre" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px">Debes de ingresar un nombre.</span>
                                </div>
                            </row>

                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="nombreAlerta" hidden>
                                    <span class="glyphicon glyphicon-alert" style="color: red"></span>
                                    <span style="color: red; padding-left: 5px" >Nombre ya existe.</span>
                                </div>
                            </row>
                            <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                <div  id="nombreSuccess" hidden>
                                    <span class="glyphicon glyphicon-ok" style="color: green"></span>
                                    <span style="color: green; padding-left: 5px" >Nombre disponible.</span>
                                </div>
                            </row>
                            <!-- Fecha -->


                            <row class="col-xs-4">


                                <input style="border-color: black " required="Campo obligatorio"  type="number"  min="1900" max="2017" step="1" value="2017" class="form-control" placeholder="Año De Creacion" id="txtAnio">
                            </row>
                            <!-- Generos  -->                                                      
                            <row class="col-xs-12">
                                <row class="col-xs-12" ><h4 class="text-center col-xs-10" style="color:white ">Generos A Los Que Pertenece</h4></row>
                                <row>
                                    <div>
                                        <% ArrayList<String> generos = (ArrayList<String>) request.getAttribute("Generos");
                                        %>
                                        <select  id="listaGeneros"  class="form-control" size="2" style="width: 100px; height: 100px">
                                            <% for (int i = 0; i < generos.size(); i++) {
                                            %>
                                            <option> <%=generos.get(i)%> </option>   
                                            <%}%>
                                        </select>
                                    </div>
                                </row>
                                <row class="col-xs-4">
                                    <div>
                                        <row><div> <input type="button" class="btn btn-default pull-right" id="btnAgregar" style="margin-top: 8px" value="Agregar"/></div></row>

                                        <row><div><input type="button" class="btn btn-default pull-right" id="btnQuitar" style="margin-top: 8px; width:75px " value="Quitar"/></div></row>
                                    </div>
                                </row>
                                <row class="col-xs-4">
                                    <div>
                                        <select  id="listaGeneroFinal"  class="form-control" size="2" style="width: 100px; height: 100px">                                          
                                        </select>
                                    </div>
                                </row>
                            </row>   

                            <!-- Temas  -->   
                            <row class="col-xs-12" ><h4 class="text-center col-xs-10" style="color:white ">Temas</h4></row>

                            <row class="col-xs-12 " style="margin-top: 10px ;padding-left: 0px">
                                <div class="form-group" > 
                                    <div class="col-xs-2"><span style="color: lavender; font-weight: bold">Nombre: </span></div>
                                    <div class="col-xs-9"><input style="border-color: black; max-width:283px" required="Campo obligatorio"  type="text" class="form-control" id="txtNombre"></div>
                                </div>
                            </row>

                            <row class="col-xs-12 " style="margin-top: 10px; padding-left: 0px">             
                                <div> 
                                    <div class="col-xs-2"> <span style="color: lavender; font-weight: bold">Duracion: </span></div>
                                    <div class="col-xs-3"> <input style="border-color: black; max-width:60px" type="number" class="form-control" id="txtHora" placeholder="hh"></div>
                                    <div class="col-xs-3"> <input style="border-color: black; max-width:60px" type="number" class="form-control" id="txtMin" placeholder="mm"></div>
                                    <div class="col-xs-3"> <input style="border-color: black; max-width:60px" type="number" class="form-control" id="txtSegundos" placeholder="ss"></div>                  
                                </div> 
                            </row>

                            <row class="col-xs-12 " style="margin-top: 10px; padding-left: 0px">             
                                <div class="form-group"> 
                                    <div class="col-xs-2"><span style="color: lavender; font-weight: bold">Posicion: </span></div>
                                    <div class="col-xs-3"><input style="border-color: black; max-width:60px" required="Campo obligatorio"  type="number" class="form-control" id="txtPosicion"></div>                        
                                </div> 
                            </row>

                            <row class="col-xs-12">
                                <div class="col-xs-6"style="padding-left: 0px"><input type="checkbox" id="ChecUrl" value="Url" onclick="mostrar('Url', this, 'Local', 'ChecLocal')"><text style="color: lavender"> Por Url</text></div>
                            </row>

                            <row class="col-xs-12" style="display: none" id="Url">
                                <table >
                                    <tr>
                                    <row class="col-xs-6"><input  required="Campo obligatorio" type="text" class="form-control" placeholder="Url" id="txtTemaRemoto" ></row>
                                    </tr>
                                </table>                         
                            </row>
                        </div>

                        <row class="col-xs-12">
                            <div class="col-xs-6" style="padding-left: 0px"><input type="checkbox" id="ChecLocal"  onclick="mostrar('Local', this, 'Url', 'ChecUrl')"><text style="color: lavender"> Temas Locales</text></div>
                        </row>

                        <row class="col-xs-12 " style="display: none" id="Local">
                            <form enctype="multipart/form-data" method="post" id="formTema" name="formTema" >
                                <input accept=".mp3" type="file" name="tema" id="tema" class="btn btn-info" style="font-size: 11px"/>
                                <input hidden type="text" name="accion" value="tema"/>
                            </form>
                        </row>

                        <row class="col-xs-12 "style="margin-top: 10px; margin-bottom: 10px"><button class="btn"id="btnAgregarTema" value="Agregar">Agregar Tema</button></row>

                        <row class="col-xs-10 " >
                            <span style="color: lavender; font-weight: bold">Lista De Temas :</span>

                            <div  >     
                                <table id="tabla" class="table table-user-information">
                                    <tbody> 
                                        <tr style="background:white">
                                            <th><center><text style="color: black " align="text-center">Url/Directorio</text></center></th>
                                    <th><center><text style="color: black " align="text-center">Nombre</text></center></th>
                                    <th><center><text style="color: black">Posicion</text></center></th>
                                    <th><center><text style="color: black">Duracion</text></center></th>   
                                    </tr>
                                    </tbody>
                                </table>
                            </div> 
                        </row>

                        <row class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div style="margin: 10px"></div>
                            <div class="col-xs-2"> <span style="color: lavender; font-weight: bold">Imagen: </span></div>
                            <form enctype="multipart/form-data" method="post" id="formImagen" name="formImagen" >
                                <input accept="image/*" type="file" name="imagen" id="imagen" class="btn btn-info" style="font-size: 11px"/>
                                <input hidden type="text" name="accion" value="album"/>
                            </form>
                        </row>



                        <!-- Crear Album  -->   
                        <row class="col-xs-4 ">
                            <input type="button" class="btn btn-default pull-right" id="btnCrearAlbum"  style="margin-top: 15px" value="Crear Album"/>
                        </row>
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
        <script src="scripts/altaAlbum.js" type="text/javascript"></script>
    </body>

</html>
