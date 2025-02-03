package mongoDemo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class MongoDbOperations {
	public static void main(String[] args) {
		System.out.println("*** Mongo Db Operations is running ***");
		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = client.getDatabase("demodb");
		MongoCollection<Document> collection = database.getCollection("Employee");
		Bson projection1 = Projections.excludeId();
		Bson projection2 = Projections.exclude("age", "_id");
		Bson filter = Filters.gt("salary", 100);
		Bson sort = Sorts.ascending("name", "salary");
		FindIterable<Document> docs = collection.find(filter).projection(projection1).sort(sort);
		
		
		for(Document d : docs) {
			System.out.println(d.toJson());
		}
//		Document doc = new Document();
//		doc.append("name", "Kalua Singh");
//		doc.append("age", 33);
//		doc.append("salary", "Phooti Kaudi");
//		doc.append("designation", "wasooli");
//		collection.insertOne(doc);

//		collection.insertOne(new Document().append("name", "Dendi Prasad").append("age", 22)
//				.append("salary", "milega milega").append("designation", "mar tod"));
//		List<Document> employees = new ArrayList<>();
//		employees.add(new Document().append("name", "Shoyab Siddique").append("age", 22).append("salary", 100000000000000L).append("designation", "Developer"));
//		employees.add(new Document().append("name", "Sanjana Panda").append("age", 22).append("salary", 100000000000000L).append("designation", "Developer"));
//		collection.insertMany(employees);
		System.out.println("Successfully connected to mongodb ");
		client.close();
	}
}
