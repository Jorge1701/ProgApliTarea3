$("#btnCrearAlbum").click(function () {
    var tabla = document.getElementById("tabla");
    var lista = document.getElementById("listaGeneroFinal");
    var temas = "";
    var generos = "";
    var i;
    if (tabla.rows.length > 1 && lista.length > 0 && $("#txtnombreAlbum").val().length > 0 && $("#txtAnio").val().length > 0) {


        for (i = 1; i < tabla.rows.length; i++) {
            temas = temas + tabla.rows[i].cells[0].innerHTML + "~" + tabla.rows[i].cells[1].innerHTML + "~" + tabla.rows[i].cells[2].innerHTML + "~" + tabla.rows[i].cells[3].innerHTML + "@";
        }
        for (i = 0; i < lista.length; i++) {
            generos = generos + lista.options[i].text + "(#%*)";
        }

        if (subirImagen() === false) {
            return;
        }
       
        $.ajax({
            type: "POST",
            url: "/Tarea2/SContenido",
            data: {
                "nombreAlbum": $("#txtnombreAlbum").val().toString(),
                "nickArtista": $("#nick").val().toString(),
                "anio": $("#txtAnio").val().toString(),
                "generos": generos,
                "temas": temas,
                "imagen": imagen,
                "accion": "crearAlbum"
            }, 
            success: function (data) {
                window.location = "/Tarea2/SInicio?mensaje=El Album Fue Creado Exitosamente";
            },
            error: function () {
                alert("Error en el servelt, al momento de ingresar el album");
            }

        });
    } else {

        alert("Falta Ingresar Algun Campo");
    }

});

$("#btnAgregar").click(function () {

    var lista = document.getElementById("listaGeneros");
    var listafinal = document.getElementById("listaGeneroFinal");

    var genero = document.createElement("option");
    if (lista.selectedIndex === -1) {
        alert("Seleccione Un Genero Que Desea Agregar");
    } else {
        genero.textContent = lista.options[lista.selectedIndex].value;
        listafinal.add(genero);
        lista.remove(lista.selectedIndex);
    }
});
$("#btnQuitar").click(function () {
    var lista = document.getElementById("listaGeneros");
    var listafinal = document.getElementById("listaGeneroFinal");
    var genero = document.createElement("option");
    if (listafinal.selectedIndex === -1) {
        alert("Seleccione Un Genero Que Desea Quitar");
    } else {
        genero.textContent = listafinal.options[listafinal.selectedIndex].value;
        listafinal.remove(listafinal.selectedIndex);
        lista.add(genero);
    }
});



$("#btnAgregarTema").click(function () {
    var checLocal = document.getElementById("ChecLocal");
    var checUrl = document.getElementById("ChecUrl");
    if (checLocal.checked) {
        if (subirTema() === false) {
            return;
        }
    }
    if (checUrl.checked) {
        tema = document.getElementById("txtTemaRemoto").value;

    }
    if (tema === "") {
        alert("Falta Seleccionar Tema");
    } else {
        var nombre = document.getElementById("txtNombre").value;
        var hora = document.getElementById("txtHora").value;
        var min = document.getElementById("txtMin").value;
        var seg = document.getElementById("txtSegundos").value;
        var posicion = document.getElementById("txtPosicion").value;

        if (nombre === "" || posicion === "") {
            alert("Falta Completar Algun Campo Del Tema");
        } else {
            if (hora === "" || min === "" || seg === "") {

                alert("Rellene Con Ceros La Duracion");
            } else {
                if (hora < 0 || min < 0 || seg < 0) {
                    alert("La duracion no puede cotnener numeros negativos");
                    return;
                }
                var tabla = document.getElementById("tabla");
                var fila = document.createElement("tr");
                var columna1 = document.createElement("td");
                var columna2 = document.createElement("td");
                var columna3 = document.createElement("td");
                var columna4 = document.createElement("td");

                var textoCelda1 = document.createTextNode(tema);
                var textoCelda2 = document.createTextNode(nombre);
                var textoCelda3 = document.createTextNode(posicion);
                var textoCelda4 = document.createTextNode(hora + ":" + min + ":" + seg);

                columna1.appendChild(textoCelda1);
                columna2.appendChild(textoCelda2);
                columna3.appendChild(textoCelda3);
                columna4.appendChild(textoCelda4);
                fila.appendChild(columna1);
                fila.appendChild(columna2);
                fila.appendChild(columna3);
                fila.appendChild(columna4);
                fila.onclick = function () {
                    $(this).remove();
                };
                fila.align = "center";
                tabla.appendChild(fila);
                tabla.style.background = "white";
                document.getElementById("txtNombre").value = "";
                document.getElementById("txtHora").value = "";
                document.getElementById("txtMin").value = "";
                document.getElementById("txtSegundos").value = "";
                document.getElementById("txtPosicion").value = "";
                document.getElementById("txtTemaRemoto").value = "";
            }
        }
    }
});

//Subir tema
var tema = "";
function subirTema() {
    var formElement = $("[name='formTema']")[0];
    var fd = new FormData(formElement);
    var fileInput = $("[name='tema']")[0];
    fd.append('file', fileInput.files[0]);

    var ruta = $("#tema").val();
    if (ruta !== "") {
        tema = ruta.split("\\")[2];
        var ext = tema.split(".").splice(-1, 1);
        if (ext.toString().localeCompare("mp3") === 0) {
            $.ajax({
                url: '/Tarea2/Uploadfile',
                data: fd,
                processData: false,
                contentType: false,
                type: 'POST',
                success: function (data) {
                    return true;
                }
            });
        } else {
            alert("Solo puede subir temas");
            tema = "";
            return false;

        }
    }
}

//Subir imagen
var imagen = "";
function subirImagen() {
    var formElement = $("[name='formImagen']")[0];
    var fd = new FormData(formElement);
    var fileInput = $("[name='imagen']")[0];
    fd.append('file', fileInput.files[0]);

    var ruta = $("#imagen").val();
    if (ruta !== "") {
        imagen = ruta.split("\\")[2];
        var ext = imagen.split(".").splice(-1, 1);
        if (ext.toString().localeCompare("jpg") === 0 || ext.toString().localeCompare("jpeg") === 0 || ext.toString().localeCompare("png") === 0 || ext.toString().localeCompare("PNG") === 0) {
            $.ajax({
                url: '/Tarea2/Uploadfile',
                data: fd,
                processData: false,
                contentType: false,
                type: 'POST',
                async: false,
                success: function (data) {
                    imagen=data;
                    return true;
                }
            });
        } else {
            alert("Solo puede subir imágenes");
            imagen = "";
            return false;

        }
    }
}

var correctoNombreAlbum = false;
$("#txtnombreAlbum").keyup(function () {
    if ($("#txtnombreAlbum").val().toString() === "") {
        $("#nicknameAlerta").hide();
        $("#nicknameSuccess").hide();
        $("#faltaNickname").show();
        return correctoNombreAlbum;
    }
    $.ajax({
        type: "POST",
        url: "/Tarea2/SContenido",
        data: {
            "nombreAlbum": $("#txtnombreAlbum").val().toString(),
            "nickArtista": $("#nick").val().toString(),
            "accion": "nombreAlbum"
        },

        success: function (data) {
            if (data === "si") {
                $("#nombreAlerta").show();
                $("#nombreSuccess").hide();
                $("#faltaNombre").hide();
                return correctoNombreAlbum = false;
            } else {
                $("#nombreAlerta").hide();
                $("#nombreSuccess").show();
                $("#faltaNombre").hide();
                return correctoNombreAlbum = true;
            }
        },
        error: function () {
            alert("Error en el servlet, al momento de chequear el Nombre");
        }
    });

});

function mostrar(it, box, other, otherBox) {
    var vis = (box.checked) ? "block" : "none";
    document.getElementById(it).style.display = vis;
    document.getElementById(otherBox).checked = false;
    document.getElementById(other).style.display = "none";
}

