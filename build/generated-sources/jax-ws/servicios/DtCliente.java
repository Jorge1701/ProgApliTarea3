
package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtCliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://Servicios/}dtUsuario">
 *       &lt;sequence>
 *         &lt;element name="suscripciones" type="{http://Servicios/}dtSuscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actual" type="{http://Servicios/}dtSuscripcion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCliente", propOrder = {
    "suscripciones",
    "actual"
})
public class DtCliente
    extends DtUsuario
{

    @XmlElement(nillable = true)
    protected List<DtSuscripcion> suscripciones;
    protected DtSuscripcion actual;


    public DtCliente() {
        this.suscripciones = null;
        this.actual = null;
    }

    public DtCliente(String nickname, String nombre, String apellido, String email, DtFecha fechaNac, String imagen, String contrasenia, DtSuscripcion suscripcion) {
        super(nickname, nombre, apellido, email, fechaNac, imagen, contrasenia);
        this.suscripciones = new ArrayList<>();
        this.actual = suscripcion;
    }

    
    public List<DtSuscripcion> getSuscripciones() {
        if (suscripciones == null) {
            suscripciones = new ArrayList<DtSuscripcion>();
        }
        return this.suscripciones;
    }

    /**
     * Obtiene el valor de la propiedad actual.
     * 
     * @return
     *     possible object is
     *     {@link DtSuscripcion }
     *     
     */
    public DtSuscripcion getActual() {
        return actual;
    }

    /**
     * Define el valor de la propiedad actual.
     * 
     * @param value
     *     allowed object is
     *     {@link DtSuscripcion }
     *     
     */
    public void setActual(DtSuscripcion value) {
        this.actual = value;
    }

}
