
package servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtSuscripcion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DtSuscripcion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_venc" type="{http://Servicios/}DtFecha" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://Servicios/}DtFecha" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DtSuscripcion", propOrder = {
    "estado",
    "cuota",
    "fechaVenc",
    "fecha",
    "monto"
})
public class DtSuscripcion {

    protected String estado;
    protected String cuota;
    @XmlElement(name = "fecha_venc")
    protected DtFecha fechaVenc;
    protected DtFecha fecha;
    protected int monto;

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the cuota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuota() {
        return cuota;
    }

    /**
     * Sets the value of the cuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuota(String value) {
        this.cuota = value;
    }

    /**
     * Gets the value of the fechaVenc property.
     * 
     * @return
     *     possible object is
     *     {@link DtFecha }
     *     
     */
    public DtFecha getFechaVenc() {
        return fechaVenc;
    }

    /**
     * Sets the value of the fechaVenc property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFecha }
     *     
     */
    public void setFechaVenc(DtFecha value) {
        this.fechaVenc = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link DtFecha }
     *     
     */
    public DtFecha getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFecha }
     *     
     */
    public void setFecha(DtFecha value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     */
    public int getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     */
    public void setMonto(int value) {
        this.monto = value;
    }

}
