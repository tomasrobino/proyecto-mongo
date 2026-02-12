package org.example;

import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {
        MongoDatabase db = MongoUtil.getDatabase();
        System.out.println(db.getName());
        PostRepository postRepository = new PostRepository();
        postRepository.save(new Post());
        System.out.println(postRepository.findAll());
    }
}