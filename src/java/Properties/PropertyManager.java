/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Properties;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Ale
 */
public class PropertyManager {
    
   private static final String PROPERTIES_FILE= "/EspotifyWebServer.properties";
	private static PropertyManager fileP;
	
	private Properties props;
	
	private PropertyManager() {
	}
	
	private void init() {
            InputStream is = this.getClass().getResourceAsStream(PROPERTIES_FILE);
            props = new Properties();
            try {
                    props.load(is);
            } catch (IOException e) {
                    e.printStackTrace();
            }
	}
	
	public static PropertyManager getInstance() {
		if (fileP == null) {			
                    fileP = new PropertyManager();
                    fileP.init();
		}
		return fileP;
	}
	
	public String getProperty(String porpName) {
            String propertyValue = props.getProperty(porpName);			
            return propertyValue;
	}
}
