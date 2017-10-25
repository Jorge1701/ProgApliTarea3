
package servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtFecha complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DtFecha">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DtFecha", propOrder = {
    "dia",
    "mes",
    "anio"
})
public class DtFecha {

    protected int dia;
    protected int mes;
    protected int anio;

    /**
     * Gets the value of the dia property.
     * 
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets the value of the dia property.
     * 
     */
    public void setDia(int value) {
        this.dia = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     */
    public int getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     */
    public void setMes(int value) {
        this.mes = value;
    }

    /**
     * Gets the value of the anio property.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

}
