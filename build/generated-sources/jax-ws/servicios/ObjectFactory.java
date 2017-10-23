
package servicios;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicios package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtFecha }
     * 
     */
    public DtFecha createDtFecha() {
        return new DtFecha();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtCliente }
     * 
     */
    public DtCliente createDtCliente() {
        return new DtCliente();
    }

    /**
     * Create an instance of {@link DtSuscripcion }
     * 
     */
    public DtSuscripcion createDtSuscripcion() {
        return new DtSuscripcion();
    }

    /**
     * Create an instance of {@link DtArtista }
     * 
     */
    public DtArtista createDtArtista() {
        return new DtArtista();
    }

}
