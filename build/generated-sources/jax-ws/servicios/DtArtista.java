package servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtArtista", propOrder = {
    "biografia",
    "web"
})
public class DtArtista
        extends DtUsuario {

    protected String biografia;
    protected String web;

    public DtArtista() {
        this.biografia = "";
        this.web = "";
    }

    public DtArtista(String nickname, String nombre, String apellido, String email, DtFecha fechaNac, String imagen, String biografia, String web, String contrasenia) {
        super(nickname, nombre, apellido, email, fechaNac, imagen, contrasenia);
        this.biografia = biografia;
        this.web = web;
    }

    public String getBiografia() {
        return biografia;
    }

    /**
     * Define el valor de la propiedad biografia.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBiografia(String value) {
        this.biografia = value;
    }

    /**
     * Obtiene el valor de la propiedad web.
     *
     * @return possible object is {@link String }
     *
     */
    public String getWeb() {
        return web;
    }

    /**
     * Define el valor de la propiedad web.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setWeb(String value) {
        this.web = value;
    }

}
