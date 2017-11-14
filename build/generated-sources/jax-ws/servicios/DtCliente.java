
package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DtCliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://Servicios/}DtUsuario">
 *       &lt;sequence>
 *         &lt;element name="suscripciones" type="{http://Servicios/}DtSuscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actual" type="{http://Servicios/}DtSuscripcion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DtCliente", propOrder = {
    "suscripciones",
    "actual"
})
public class DtCliente
    extends DtUsuario
{

    @XmlElement(nillable = true)
    protected List<DtSuscripcion> suscripciones;
    protected DtSuscripcion actual;

    /**
     * Gets the value of the suscripciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suscripciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuscripciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtSuscripcion }
     * 
     * 
     */
    public List<DtSuscripcion> getSuscripciones() {
        if (suscripciones == null) {
            suscripciones = new ArrayList<DtSuscripcion>();
        }
        return this.suscripciones;
    }

    /**
     * Gets the value of the actual property.
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
     * Sets the value of the actual property.
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
