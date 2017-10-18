$("#cerrarSesion").click(function () {
    window.location = "index.jsp";
});

$("#btnSesion").hide();


$("#btnIniciarSesion").click(function () {

    $.ajax({
        type: "POST",
        url: "/Tarea2/SSesion",
        data: {
            "accion": "iniciarSesion",
            "nickname": $("#txtNickname").val().toString(),
            "contrasenia": md5($("#txtPass").val().toString())
        },
        success: function (data) {
            if (data.toString() === "correcto") {
                window.location = "/Tarea2/SInicio";
            } else {
                window.location = "/Tarea2/SSesion?accion=error&mensaje=" + data.toString();
            }
        }

    });

});