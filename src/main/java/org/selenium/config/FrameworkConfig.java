package org.selenium.config;

import java.net.URL;

import org.aeonbits.owner.Config.DefaultValue;
import org.aeonbits.owner.Config.Key;

import org.aeonbits.owner.Config;


/**
 * Configuration interface to provide method name for framework configuration keys.
 * Mar 27, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @see Config
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ 
	"system:properties", 
	"system:env",
	"file:${user.dir}/src/test/resources/config.properties",
	})
public interface FrameworkConfig extends Config {
	
	@Key("url")
	String webUrl();	
	
	@Key("localhost")
	String getHost();
	
	@Key("port")
	Integer getPortNo();
	
	@Key("database")
	String getDatabase();
	
	@Key("collection")
	String getCollection();
	
}
