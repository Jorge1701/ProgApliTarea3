
package servicios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


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

    private final static QName _SoapSeviciosFaultException_QNAME = new QName("http://Servicios/", "SoapSeviciosFaultException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SoapSeviciosFaultException }
     * 
     */
    public SoapSeviciosFaultException createSoapSeviciosFaultException() {
        return new SoapSeviciosFaultException();
    }

    /**
     * Create an instance of {@link DtPerfilCliente }
     * 
     */
    public DtPerfilCliente createDtPerfilCliente() {
        return new DtPerfilCliente();
    }

    /**
     * Create an instance of {@link DtTema }
     * 
     */
    public DtTema createDtTema() {
        return new DtTema();
    }

    /**
     * Create an instance of {@link DtLista }
     * 
     */
    public DtLista createDtLista() {
        return new DtLista();
    }

    /**
     * Create an instance of {@link DtArtista }
     * 
     */
    public DtArtista createDtArtista() {
        return new DtArtista();
    }

    /**
     * Create an instance of {@link DtTime }
     * 
     */
    public DtTime createDtTime() {
        return new DtTime();
    }

    /**
     * Create an instance of {@link DtFecha }
     * 
     */
    public DtFecha createDtFecha() {
        return new DtFecha();
    }

    /**
     * Create an instance of {@link DtListaParticular }
     * 
     */
    public DtListaParticular createDtListaParticular() {
        return new DtListaParticular();
    }

    /**
     * Create an instance of {@link DtGenero }
     * 
     */
    public DtGenero createDtGenero() {
        return new DtGenero();
    }

    /**
     * Create an instance of {@link DtPerfilArtista }
     * 
     */
    public DtPerfilArtista createDtPerfilArtista() {
        return new DtPerfilArtista();
    }

    /**
     * Create an instance of {@link DtTemaRemoto }
     * 
     */
    public DtTemaRemoto createDtTemaRemoto() {
        return new DtTemaRemoto();
    }

    /**
     * Create an instance of {@link DtCliente }
     * 
     */
    public DtCliente createDtCliente() {
        return new DtCliente();
    }

    /**
     * Create an instance of {@link DtTemaLocal }
     * 
     */
    public DtTemaLocal createDtTemaLocal() {
        return new DtTemaLocal();
    }

    /**
     * Create an instance of {@link DtListaDefecto }
     * 
     */
    public DtListaDefecto createDtListaDefecto() {
        return new DtListaDefecto();
    }

    /**
     * Create an instance of {@link SimpleExceptionBean }
     * 
     */
    public SimpleExceptionBean createSimpleExceptionBean() {
        return new SimpleExceptionBean();
    }

    /**
     * Create an instance of {@link DtPerfilUsuario }
     * 
     */
    public DtPerfilUsuario createDtPerfilUsuario() {
        return new DtPerfilUsuario();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtSuscripcion }
     * 
     */
    public DtSuscripcion createDtSuscripcion() {
        return new DtSuscripcion();
    }

    /**
     * Create an instance of {@link DtListaTema }
     * 
     */
    public DtListaTema createDtListaTema() {
        return new DtListaTema();
    }

    /**
     * Create an instance of {@link DtAlbum }
     * 
     */
    public DtAlbum createDtAlbum() {
        return new DtAlbum();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapSeviciosFaultException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "SoapSeviciosFaultException")
    public JAXBElement<SoapSeviciosFaultException> createSoapSeviciosFaultException(SoapSeviciosFaultException value) {
        return new JAXBElement<SoapSeviciosFaultException>(_SoapSeviciosFaultException_QNAME, SoapSeviciosFaultException.class, null, value);
    }

}
