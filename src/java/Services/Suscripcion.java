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
@WebService(serviceName = "PSuscripcionService", portName = "PSuscripcionPort", endpointInterface = "servicios.PSuscripcion", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Suscripcion/localhost_1234/suscripcion.wsdl")
public class Suscripcion {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean renovarSuscripcion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, servicios.DtFecha arg5) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean ingresarSuscripcion(java.lang.String arg0, java.lang.String arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean cancelarSuscripcion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, servicios.DtFecha arg5) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void dtCliente(servicios.DtCliente arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void dtArtista(servicios.DtArtista arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtUsuario getDataUsuario(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int getMonto(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
