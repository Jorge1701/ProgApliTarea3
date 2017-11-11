package Configuracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.rmi.server.LogStream.log;
import java.util.HashMap;

// Tarea 2
public class Configuracion {

    private static HashMap<String, String> propiedades;

    public static void cargar() {
        propiedades = new HashMap<>();

        //String quitar = "build/web/WEB-INF/classes/Configuracion/Configuracion.class";
        //String path = Configuracion.class.getResource("/Configuracion/Configuracion.class").getPath().replaceAll(quitar, "configuracion.properties");

        //System.out.println("PATH = " + path);
        try {
            //Use Any Environmental Variable , here i have used CATALINA_HOME
            String propertyHome = System.getenv("CATALINA_HOME");           
            if(null == propertyHome){

                //This is a system property that is  passed
                // using the -D option in the Tomcat startup script
                propertyHome  =  System.getProperty("PROPERTY_HOME");
            }
            String path= propertyHome+"/properties/configuracion.properties";
            
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isEmpty()) {
                    String[] l = linea.split("=");
                    propiedades.put(l[0], l[1]);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String get(String name) {
        return propiedades.get(name);
    }
}
