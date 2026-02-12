package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoUtil {
    private static MongoClient client;
    private static MongoDatabase db;

    public static MongoDatabase getDatabase() {
        if (db == null) {
            CodecRegistry pojoCodecRegistry =
                    fromRegistries(
                            getDefaultCodecRegistry(),
                            fromProviders(PojoCodecProvider.builder().automatic(true).build())
                    );

            MongoClientSettings settings = MongoClientSettings
                    .builder()
                    .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                    .codecRegistry(pojoCodecRegistry)
                    .build();
            client = MongoClients.create(settings);
            db = client.getDatabase("blog").withCodecRegistry(pojoCodecRegistry);
        }
        return db;
    }

    public static void close() {
        if (client != null) client.close();
    }
}
