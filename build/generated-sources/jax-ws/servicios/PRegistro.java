
package servicios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PRegistro", targetNamespace = "http://Servicios/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PRegistro {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PRegistro/ingresarUsuarioRequest", output = "http://Servicios/PRegistro/ingresarUsuarioResponse")
    public boolean ingresarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        DtUsuario arg0);

    /**
     * 
     */
    @WebMethod
    @Action(input = "http://Servicios/PRegistro/publicarRequest", output = "http://Servicios/PRegistro/publicarResponse")
    public void publicar();

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "DtArtista")
    @Action(input = "http://Servicios/PRegistro/DtArtistaRequest", output = "http://Servicios/PRegistro/DtArtistaResponse")
    public void dtArtista(
        @WebParam(name = "arg0", partName = "arg0")
        DtArtista arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "DtCliente")
    @Action(input = "http://Servicios/PRegistro/DtClienteRequest", output = "http://Servicios/PRegistro/DtClienteResponse")
    public void dtCliente(
        @WebParam(name = "arg0", partName = "arg0")
        DtCliente arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PRegistro/correoExisteRequest", output = "http://Servicios/PRegistro/correoExisteResponse")
    public boolean correoExiste(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PRegistro/nicknameExisteRequest", output = "http://Servicios/PRegistro/nicknameExisteResponse")
    public boolean nicknameExiste(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servicios.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PRegistro/getDataUsuarioRequest", output = "http://Servicios/PRegistro/getDataUsuarioResponse")
    public DtUsuario getDataUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
