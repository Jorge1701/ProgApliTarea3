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
@WebService(serviceName = "PContadoresService", portName = "PContadoresPort", endpointInterface = "servicios.PContadores", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Contadores/localhost_1234/contadores.wsdl")
public class Contadores {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void reproducirTema(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void descargaTema(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
