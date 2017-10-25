/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.jws.WebService;

/**
 *
 * @author jorge
 */
@WebService(serviceName = "PSesionService", portName = "PSesionPort", endpointInterface = "servicios.PSesion", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Sesion/localhost_1234/sesion.wsdl")
public class Sesion {

    public void publicar() {
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

    public boolean esCliente(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.lang.String chequearLogin(java.lang.String arg0, java.lang.String arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
