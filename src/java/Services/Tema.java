/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.jws.WebService;

/**
 *
 * @author Diego
 */
@WebService(serviceName = "PTemaService", portName = "PTemaPort", endpointInterface = "servicios.PTema", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Tema/localhost_1234/tema.wsdl")
public class Tema {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public byte[] getAudio(java.lang.String audio) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
