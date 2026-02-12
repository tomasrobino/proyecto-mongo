package org.example;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class PostRepository {
    private final MongoCollection<Post> postsCollection = MongoUtil.getDatabase().getCollection("posts", Post.class);

    public void save(Post post) {
        postsCollection.insertOne(post);
    }

    public Iterable<Post> findAll() {
        return postsCollection.find();
    }

    public Post findById(String id) {
        return postsCollection.find(new Document("_id", id)).first();

    }

    public void deleteById(String id) {

    }

    public void update(Post post) {

    }

    public void deleteAll() {

    }
}
