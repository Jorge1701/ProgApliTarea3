
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
@WebService(name = "PInicio", targetNamespace = "http://Servicios/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PInicio {


    /**
     * 
     * @return
     *     returns servicios.DtListaUsuarios
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/listarClientesRequest", output = "http://Servicios/PInicio/listarClientesResponse")
    public DtListaUsuarios listarClientes();

    /**
     * 
     * @param arg0
     * @return
     *     returns servicios.DtListaUsuarios
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/listarSeguidosDeRequest", output = "http://Servicios/PInicio/listarSeguidosDeResponse")
    public DtListaUsuarios listarSeguidosDe(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns servicios.DtListaUsuarios
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/listarArtistasRequest", output = "http://Servicios/PInicio/listarArtistasResponse")
    public DtListaUsuarios listarArtistas();

    /**
     * 
     */
    @WebMethod
    @Action(input = "http://Servicios/PInicio/publicarRequest", output = "http://Servicios/PInicio/publicarResponse")
    public void publicar();

    /**
     * 
     * @param arg0
     * @return
     *     returns servicios.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/getDataUsuarioRequest", output = "http://Servicios/PInicio/getDataUsuarioResponse")
    public DtUsuario getDataUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servicios.DtCliente
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/getDataClienteRequest", output = "http://Servicios/PInicio/getDataClienteResponse")
    public DtCliente getDataCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns servicios.DtListaString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/obtenerGenerosRequest", output = "http://Servicios/PInicio/obtenerGenerosResponse")
    public DtListaString obtenerGeneros();

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://Servicios/PInicio/chequearSuscripcionRequest", output = "http://Servicios/PInicio/chequearSuscripcionResponse")
    public boolean chequearSuscripcion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "DtSuscripcion")
    @Action(input = "http://Servicios/PInicio/DtSuscripcionRequest", output = "http://Servicios/PInicio/DtSuscripcionResponse")
    public void dtSuscripcion(
        @WebParam(name = "arg0", partName = "arg0")
        DtSuscripcion arg0);

    /**
     * 
     */
    @WebMethod
    @Action(input = "http://Servicios/PInicio/cargarDatosPruebaRequest", output = "http://Servicios/PInicio/cargarDatosPruebaResponse")
    public void cargarDatosPrueba();

}
