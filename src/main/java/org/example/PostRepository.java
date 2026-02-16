package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PostRepository {
    private final MongoCollection<Post> postsCollection = MongoUtil.getDatabase().getCollection("posts", Post.class);

    public void save(Post post) {
        postsCollection.insertOne(post);
    }

    public Iterable<Post> findAll() {
        System.out.println("findAll");
        return postsCollection.find();
    }

    public Post findById(String id) {
        System.out.println("findById: "+id);
        return postsCollection.find(new Document("_id", new ObjectId(id))).first();

    }

    public void deleteById(String id) {
        System.out.println("deleteById: "+id);
        DeleteResult dr = postsCollection.deleteOne(new Document("_id", new ObjectId(id)));
        System.out.println("Deleted "+dr.getDeletedCount()+" documents");
    }

    public void deleteAll() {
        postsCollection.drop();
    }

    public void update(Post post) {
        System.out.println("update: "+post.getId());
        postsCollection.replaceOne(new Document("_id", post.getId()), post);
    }
}
