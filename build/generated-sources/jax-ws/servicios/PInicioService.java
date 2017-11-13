
package servicios;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PInicioService", targetNamespace = "http://Servicios/", wsdlLocation = "file:/C:/Users/Luis/Documents/NetBeansProjects/ProgApliTarea3/src/conf/xml-resources/web-services/Inicio/wsdl/localhost_1234/inicio.wsdl")
public class PInicioService
    extends Service
{

    private final static URL PINICIOSERVICE_WSDL_LOCATION;
    private final static WebServiceException PINICIOSERVICE_EXCEPTION;
    private final static QName PINICIOSERVICE_QNAME = new QName("http://Servicios/", "PInicioService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/Luis/Documents/NetBeansProjects/ProgApliTarea3/src/conf/xml-resources/web-services/Inicio/wsdl/localhost_1234/inicio.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PINICIOSERVICE_WSDL_LOCATION = url;
        PINICIOSERVICE_EXCEPTION = e;
    }

    public PInicioService() {
        super(__getWsdlLocation(), PINICIOSERVICE_QNAME);
    }

    public PInicioService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PINICIOSERVICE_QNAME, features);
    }

    public PInicioService(URL wsdlLocation) {
        super(wsdlLocation, PINICIOSERVICE_QNAME);
    }

    public PInicioService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PINICIOSERVICE_QNAME, features);
    }

    public PInicioService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PInicioService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PInicio
     */
    @WebEndpoint(name = "PInicioPort")
    public PInicio getPInicioPort() {
        return super.getPort(new QName("http://Servicios/", "PInicioPort"), PInicio.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PInicio
     */
    @WebEndpoint(name = "PInicioPort")
    public PInicio getPInicioPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Servicios/", "PInicioPort"), PInicio.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PINICIOSERVICE_EXCEPTION!= null) {
            throw PINICIOSERVICE_EXCEPTION;
        }
        return PINICIOSERVICE_WSDL_LOCATION;
    }

}
