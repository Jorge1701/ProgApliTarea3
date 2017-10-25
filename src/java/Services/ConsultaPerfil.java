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
@WebService(serviceName = "PConsultaPerfilService", portName = "PConsultaPerfilPort", endpointInterface = "servicios.PConsultaPerfil", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/ConsultaPerfil/localhost_1234/consultaPerfil.wsdl")
public class ConsultaPerfil {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtPerfilCliente obtenerPerfilCliente(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtPerfilArtista obtenerPerfilArtista(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtUsuario getDataUsuario(java.lang.String arg0) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
