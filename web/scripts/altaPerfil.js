//Hacer registro y redireccionar al index
$("#btnRegistro").click(function () {
    if (correctoNickname === true && correctoEmail === true
            && correctoContrasenia === true && correctoConfContrasenia === true
            && correctoNombre === true && correctoApellido === true
            && correctoDia === true && correctoAnio === true && correctoMes === true) {

        if (subirImagen() === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "/Tarea2/SRegistro",
            data: {
                "nickname": $("#txtNickname").val().toString(),
                "email": $("#txtEmail").val().toString(),
                "contrasenia": md5($("#txtContrasenia").val().toString()),
                "nombre": $("#txtNombre").val().toString(),
                "apellido": $("#txtApellido").val().toString(),
                "dia": $("#txtDia").val().toString(),
                "mes": $("#txtMes").val().toString(),
                "anio": $("#txtAnio").val().toString(),
                "biografia": $("#txtBiografia").val().toString(),
                "link": $("#txtLink").val().toString(),
                "artista": artista,
                "imagen": imagen,
                "accion": "registro"},
            success: function (data) {
                window.location = "/Tarea2/SInicio?mensaje=¡Bienvenido! Disfrute de la musica online en vivo";
            },

            error: function () {
                alert("Error en el servelt, al momento de ingresar el perfil");
            }
        });

    } else {
        alert("Campo(s) erroneo(s) o incompleto(s)");
    }
});

//Subir imagen
var imagen = "";
function subirImagen() {
    var formElement = $("[name='formArchivo']")[0];
    var fd = new FormData(formElement);
    var fileInput = $("[name='archivo']")[0];
    fd.append('file', fileInput.files[0]);

    var ruta = $("#archivo").val();
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
                    imagen = data;
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

//Verificar que no exista el nickname
var correctoNickname = false;
$("#txtNickname").keyup(function () {
    if ($("#txtNickname").val().toString() === "") {
        $("#nicknameAlerta").hide();
        $("#nicknameSuccess").hide();
        $("#faltaNickname").show();
        $("#formatoErroneo").hide();
        return correctoNickname = false;
    }

    if ($("#txtNickname").val().toString().indexOf("@") !== -1) {
        $("#nicknameAlerta").hide();
        $("#nicknameSuccess").hide();
        $("#faltaNickname").hide();
        $("#formatoErroneo").show();
        return correctoNickname = false;
    }

    $.ajax({
        type: "POST",
        url: "/Tarea2/SRegistro",
        data: {
            "nickname": $("#txtNickname").val().toString().toLowerCase(),
            "accion": "nickname"},

        success: function (data) {
            if (data === "si") {
                $("#nicknameAlerta").show();
                $("#nicknameSuccess").hide();
                $("#faltaNickname").hide();
                $("#formatoErroneo").hide();
                return correctoNickname = false;
            } else {
                $("#nicknameAlerta").hide();
                $("#nicknameSuccess").show();
                $("#faltaNickname").hide();
                $("#formatoErroneo").hide();
                return correctoNickname = true;
            }
        },
        error: function () {
            alert("Error en el servlet, al momento de chequear el Nickname");
        }
    });

});

//Verificar que no exista el Email
var correctoEmail = false;
$("#txtEmail").keyup(function () {
    var email = $("#txtEmail").val().toString();
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if (email === "") {
        $("#emailAlerta").hide();
        $("#emailSuccess").hide();
        $("#faltaEmail").show();
        $("#formatoIncorrecto").hide();
        return correctoEmail = false;
    } else if (!filter.test(email)) {
        $("#emailAlerta").hide();
        $("#emailSuccess").hide();
        $("#faltaEmail").hide();
        $("#formatoIncorrecto").show();
        email.focus;
        return correctoEmail = false;
    }

    $.ajax({
        type: "POST",
        url: "/Tarea2/SRegistro",
        data: {"email": $("#txtEmail").val().toString(),
            "accion": "email"},

        success: function (data) {
            if (data === "si") {
                $("#emailAlerta").show();
                $("#emailSuccess").hide();
                $("#faltaEmail").hide();
                $("#formatoIncorrecto").hide();
                return correctoEmail = false;
            } else {
                $("#emailAlerta").hide();
                $("#emailSuccess").show();
                $("#faltaEmail").hide();
                $("#formatoIncorrecto").hide();
                return correctoEmail = true;
            }
        },
        error: function () {
            alert("Error en el servlet, al momento de chequear el Email");
        }
    });

});


//Ocultar boton Registrarse(del header)

$("#btnRegistrarse").hide();

//Verificacion de contraseña

var correctoContrasenia = false;

$("#txtContrasenia").focusout(function () {
    var contrasenia = $("#txtContrasenia").val().toString();
    if (contrasenia === "") {
        $("#faltaContrasenia").show();
        return correctoContrasenia = false;
    } else {
        $("#faltaContrasenia").hide();
        return correctoContrasenia = true;
    }
});


var correctoConfContrasenia = false;
$("#txtConfContrasenia").keyup(function () {
    $("#alertaContrasenia").hide();
    var contrasenia = $("#txtContrasenia").val().toString();
    var verifContrasenia = $("#txtConfContrasenia").val().toString();

    if (contrasenia.indexOf(verifContrasenia) !== -1) {
        if (contrasenia.length === verifContrasenia.length) {
            $("#alertaContrasenia").hide();
            return correctoConfContrasenia = true;
        } else {
            return correctoConfContrasenia = false;
        }
    } else {
        $("#alertaContrasenia").show();
        return correctoConfContrasenia = false;
    }
});
$("#txtContrasenia").click(function () {
    $("#alertaContrasenia").hide();
    $("#faltaContrasenia").hide();
});
$("#txtConfContrasenia").click(function () {
    $("#alertaContrasenia").hide();
});


//Comprobacion de Nombre y Apellido

var correctoNombre = false;
$("#txtNombre").focusout(function () {
    var nombre = $("#txtNombre").val().toString();

    if (nombre === "") {
        $("#alertaNombre").show();
        return correctoNombre = false;
    } else {
        $("#alertaNombre").hide();
        return correctoNombre = true;
    }
});
var correctoApellido = false;
$("#txtApellido").focusout(function () {
    var apellido = $("#txtApellido").val().toString();

    if (apellido === "") {
        $("#alertaNombre").show();
        return correctoApellido = false;
    } else {
        $("#alertaNombre").hide();
        return correctoApellido = true;
    }
});
//Comprobacion de fecha

var correctoDia = false;
$("#txtDia").focusout(function () {
    var dia = $("#txtDia").val();
    if (!isNaN(dia) && dia !== "") {
        dia = parseInt(dia);
        if (dia <= 0 || dia >= 32) {
            $("#alertaFecha").show();
            return correctoDia = false;
        } else {
            $("#alertaFecha").hide();
            return correctoDia = true;
        }
    } else {
        $("#alertaFecha").show();
        return correctoDia = false;
    }
});

var correctoAnio = false;
$("#txtAnio").focusout(function () {
    var anio = $("#txtAnio").val();
    if (!isNaN(anio) && anio !== "") {
        anio = parseInt(anio);
        if (anio <= 1900 || anio >= 2018) {
            $("#alertaFecha").show();
            return correctoAnio = false;
        } else {
            $("#alertaFecha").hide();
            return correctoAnio = true;
        }
    } else {
        $("#alertaFecha").show();
        return correctoAnio = false;
    }
});
var correctoMes = false;
$("#txtMes").focusout(function () {
    var mes = $("#txtMes").val().toString();
    if (mes === "mes") {
        $("#alertaFecha").show();
        return correctoMes = false;
    } else {
        $("#alertaFecha").hide();
        return correctoMes = true;
    }
});
// Mostrar o ocultar datos del Artista

var artista = "no";
$("#btnCliente").click(function () {
    $("#datosArtista").hide();
    return artista = "no";
});


$("#btnArtista").click(function () {
    $("#datosArtista").show();
    return artista = "si";
});
