/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.jws.WebService;
import servicios.Exception_Exception;

/**
 *
 * @author Ale
 */
@WebService(serviceName = "PInicioService", portName = "PInicioPort", endpointInterface = "servicios.PInicio", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Inicio/localhost_1234/inicio.wsdl")
public class Inicio {

    public servicios.DtListaUsuarios listarSeguidosDe(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtListaUsuarios listarArtistas() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtListaUsuarios listarClientes() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean chequearSuscripcion(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtListaString obtenerGeneros() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtUsuario getDataUsuario(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtCliente getDataCliente(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void dtSuscripcion(servicios.DtSuscripcion arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void cargarDatosPrueba() throws Exception_Exception {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
