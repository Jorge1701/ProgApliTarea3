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
@WebService(serviceName = "PRankingService", portName = "PRankingPort", endpointInterface = "servicios.PRanking", targetNamespace = "http://Servicios/", wsdlLocation = "WEB-INF/wsdl/Ranking/localhost_1234/ranking.wsdl")
public class Ranking {

    public void publicar() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public servicios.DtListaRanking obtenerRanking() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
