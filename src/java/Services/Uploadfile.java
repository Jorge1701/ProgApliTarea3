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
@WebService(serviceName = "PUploadfileService", portName = "PUploadfilePort", endpointInterface = "servicios.PUploadfile", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Uploadfile/localhost_1234/uploadfile.wsdl")
public class Uploadfile {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean uploadfile(byte[] arg0, java.lang.String arg1, java.lang.String arg2) throws Exception_Exception {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
