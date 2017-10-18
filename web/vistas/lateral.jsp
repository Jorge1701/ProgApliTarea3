<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <jsp:include page="../scripts/lateral.html"/>
        
    </head>

    <body> 
        <div class="row">
            <div class="text-center" style="margin-bottom: 15px">
                <div   align="center">    <img id="imgAlbumTema" src="/Tarea2/SImagen?album=albumDefault.png" width="150" height="150" class="img-circle img-responsive"></div>
            </div>
        </div>

        <div class="row">
            <div class="text-center" style="margin-bottom: 15px">
                <span id="txtNombreTema" style="color:lavender; text-shadow: 2px 2px 4px #000000">
                    ---
                </span>
            </div>
        </div>
        <div class="row">
            <div class="text-center" style="margin-bottom: 15px">
                <span id="txtNombreArtista" style="font-weight: bold;color:lavender; text-shadow: 2px 2px 4px #000000">
                    ---
                </span>
            </div>
        </div>

        <div class="row">
            <div class="text-center" style="margin-bottom: 15px">
                <audio id="aurepr" style="width: 250px" preload="auto" controls onended="get_next(1)"><source src="" type="audio/mpeg"></audio>
            </div>
        </div>


        <div class="row">
            <div class="input-group-btn text-center"> 
                <button id="btnIzq" class="btn btn-default" type="submit" style=" margin-right: 3px"> <span class="glyphicon glyphicon-backward"></span></button> 
                <button id="btnDer" class="btn btn-default" type="submit" style="margin-left: 3px"> <span class="glyphicon glyphicon-forward"></span></button>         
            </div>
        </div>
      
    </body>
</html>
