$("#btnCrearLst").click(function () {
    if (correctoNombre === true) {
        $.ajax({
            type: "POST",
            url: "/Tarea2/SLista",
            data: {
                "nombreLst": $("#txtNombreLst").val().toString(),
                "accion": "registroLst"
            },                
            success: function (data) {
               $("#nombreSuccess").hide();
               $("#txtNombreLst").val("");
               $("#altaLstOK").show();              
            },
            error: function () {
                alert("Error en el servelt, al momento de ingresar la lista");
            }
        });

    } else {
        alert("Campo(s) erroneo(s) o incompleto(s)");
    }
});

//Verificar que no exista el Nombre de Lista
var correctoNombre = false;
$("#txtNombreLst").keyup(function () {
    if ($("#txtNombreLst").val().toString() === "") {
        $("#nombreAlerta").hide();
        $("#nombreSuccess").hide();
        $("#faltaNombre").show();
        return correctoNombre = false;
    }     
    
    $.ajax({
        type: "POST",
        url: "/Tarea2/SLista",
        data: {"nombreLst": $("#txtNombreLst").val().toString(),
            "accion": "nombreLst"},

        success: function (data) {
            if (data === "si") {
                $("#nombreAlerta").show();
                $("#nombreSuccess").hide();
                $("#faltaNombre").hide();
                $("#altaLstOK").hide(); 
                return correctoNombre = false;
            } else {
                $("#nombreAlerta").hide();
                $("#nombreSuccess").show();
                $("#faltaNombre").hide();
                $("#altaLstOK").hide(); 
                return correctoNombre = true;
            }
        },
        error: function () {
            alert("Error en el servlet, al momento de chequear el Nombe de Lista");
        }
    });

});
