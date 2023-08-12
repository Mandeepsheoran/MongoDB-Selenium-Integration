package org.mongodbselenium.test;

import org.mongo.setup.MongoDBConnection;
import org.selenium.pompages.DemoQAPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MongoDBSeleniumTest extends BaseTest{
	DemoQAPage demoqa = new DemoQAPage();
	MongoDBConnection mongodb = new MongoDBConnection();
	
	@Test
	public void loginTestFromMongoDB() throws InterruptedException {
		demoqa.enterUserName(mongodb.getMongoDBCollections().createNewDocument().getUserName());
		demoqa.enterpassword(mongodb.getMongoDBCollections().getPassword());
		demoqa.clickLogin();
		Thread.sleep(5000);
		Assert.assertEquals(true, demoqa.isUserLoggedIN());
	}
	
	@Test
	public void getMongoDBDocumentList() throws InterruptedException {
		String docs = mongodb.getMongoDBCollections().retrieveDocument();
		System.out.println("List of available document in given collection is :+"+ docs);
	}
	
	@Test
	public void deleteDocument() throws InterruptedException {
		mongodb.getMongoDBCollections().createNewDocument().deleteDocument();
	}
	
	@Test
	public void retrieveDocCountFromCollection() throws InterruptedException {
		long doccount = mongodb.getMongoDBCollections().retrieveDocumentCount();
		System.out.println("Count of total documents in collection is :+"+ doccount);
	}

}
