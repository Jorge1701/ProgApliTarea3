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
@WebService(serviceName = "PDownloadFileService", portName = "PDownloadFilePort", endpointInterface = "servicios.PDownloadFile", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Downloadfile/localhost_1234/downloadfile.wsdl")
public class Downloadfile {

    public byte[] getFile(java.lang.String fileName) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
