package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static MongoClient client;
    private static MongoDatabase db;

    public static MongoDatabase getDatabase() {
        if (db == null) {
            client = MongoClients.create("mongodb://localhost:27017");
            db = client.getDatabase("centro_fp");
        }
        return db;
    }

    public static void close() {
        if (client != null) client.close();
    }
}
