$("#btnBuscador").click(function (){
   var buscar = $("#txtBuscador").val().toString();
   if( buscar == ""){
       $("#alerta2").show();   
   }else{
      $("#alerta2").hiden(); 
      alert("Se Buscara " + buscar);
   }   
});
$("#txtBuscador").click(function () {
    $("#alerta2").hide();
});