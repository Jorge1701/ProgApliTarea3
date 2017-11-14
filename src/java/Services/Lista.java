/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.jws.WebService;

/**
 *
 * @author Ale
 */
@WebService(serviceName = "PListaService", portName = "PListaPort", endpointInterface = "servicios.PLista", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Lista/localhost_1234/lista.wsdl")
public class Lista {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean crearListaReproduccion(servicios.DtListaParticular arg0, java.lang.String arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean existeLista(java.lang.String arg0, java.lang.String arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
