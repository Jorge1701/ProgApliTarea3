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
@WebService(serviceName = "PBuscadorService", portName = "PBuscadorPort", endpointInterface = "servicios.PBuscador", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Buscador/localhost_1234/buscador.wsdl")
public class Buscador {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtListaBuscados buscar(java.lang.String arg0, java.lang.String arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtTema asd(servicios.DtTemaRemoto arg0, servicios.DtTemaLocal arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
