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
@WebService(serviceName = "PImagenService", portName = "PImagenPort", endpointInterface = "servicios.PImagen", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Imagen/localhost_1234/imagen.wsdl")
public class Imagen {

    public byte[] getFile(java.lang.String recurso, java.lang.String fileName) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
