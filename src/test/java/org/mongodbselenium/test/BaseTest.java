package org.mongodbselenium.test;

import java.util.Objects;

import org.mongo.setup.MongoDBConnection;
import org.selenium.config.ConfigFactory;
import org.selenium.driver.DriverSetup;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	
	static MongoDBConnection mongodb;
	
	@BeforeClass
	public static void initDriver() {
		DriverSetup.getDriver().get(ConfigFactory.getConfig().webUrl());
	}
	
	@AfterClass
	public static void quitDriver() {
		if(Objects.nonNull(mongodb)) {
		mongodb.quitMongoDBConnection();
		}
		DriverSetup.tearDownDriver();
	}

}
