package org.mongo.setup;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.selenium.config.ConfigFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import java.util.Objects;

public class MongoDBConnection {
	private static MongoCollection<Document> collections;
	private static ObjectId id;
	private static Document newdoc;
	private static MongoClient mongoc;
	private static MongoDatabase database;

	public MongoDBConnection getMongoDBCollections() {
		if (Objects.isNull(mongoc)) {
			mongoc = new MongoClient(ConfigFactory.getConfig().getHost(), ConfigFactory.getConfig().getPortNo());
		}
		database = mongoc.getDatabase(ConfigFactory.getConfig().getDatabase());
		collections = database.getCollection(ConfigFactory.getConfig().getCollection());
		return this;
	}

	public MongoDBConnection createNewDocument() {
		newdoc = new Document("username", "mandeep").append("password", "Hallo@123");
		collections.insertOne(newdoc);
		id = newdoc.getObjectId("_id");
		System.out.println("ID for newly created document is : " + id);
		return this;
	}

	public MongoDBConnection updateDocument() {
		collections.updateOne(eq("_id", new ObjectId(id.toString())),
				combine(set("username", "Rohit"), set("password", "sheoran@123"), currentDate("lastModified")),
				new UpdateOptions().upsert(true).bypassDocumentValidation(true));

		newdoc = collections.find(eq("_id", new ObjectId(id.toString()))).first();
		assert newdoc != null;
		String password = newdoc.get("password").toString();
		String username = newdoc.get("username").toString();
		System.out.println("Updated username is :" + username);
		System.out.println("Updated password is :" + password);
		return this;
	}

	public String getUserName() {
		return newdoc.get("username").toString();
	}

	public String getPassword() {
		return newdoc.get("password").toString();
	}

	public String retrieveDocument() {
		String docs = null;
		try (MongoCursor<Document> cursor = collections.find().iterator()) {
			while (cursor.hasNext()) {
				docs = cursor.next().toJson();
			}
		}
		return docs;
	}

	public MongoDBConnection deleteDocument() {
		collections.deleteOne(eq("_id", new ObjectId(id.toString())));
		return this;
	}

	public long retrieveDocumentCount() {
		long count = collections.countDocuments();
		return count;
	}

	public MongoDBConnection quitMongoDBConnection() {
		mongoc.close();
		return this;
	}

}
