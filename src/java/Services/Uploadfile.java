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
@WebService(serviceName = "PUploadfileService", portName = "PUploadfilePort", endpointInterface = "servicios.PUploadfile", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Uploadfile/localhost_1234/uploadfile.wsdl")
public class Uploadfile {

    public boolean upload(java.lang.String arg0, java.lang.String arg1, byte[] arg2) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
