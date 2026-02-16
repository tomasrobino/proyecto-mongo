package org.example;

import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MongoDatabase db = MongoUtil.getDatabase();
        System.out.println(db.getName());

        PostRepository postRepository = new PostRepository();

        Post newPost = new Post();
        Comentario comentario = new Comentario();
        comentario.setContenido("Hola");
        Comentario comentario2 = new Comentario();
        comentario2.setContenido("Adios");
        ArrayList<Comentario> comentarios = new ArrayList<>();
        comentarios.add(comentario);
        comentarios.add(comentario2);
        newPost.setComentarios(comentarios);
        postRepository.save(newPost);

        Iterable<Post> posts = postRepository.findAll();
        posts.forEach(post -> {
            post.getComentarios().forEach(comentario1 -> System.out.println(comentario1.getContenido()));
            System.out.println(post.getId());
        });

        postRepository.deleteById(newPost.getId().toString());
        postRepository.save(newPost);

        Comentario comentario3 = new Comentario();
        comentario3.setContenido("tres");
        ArrayList<Comentario> comentarios2 = new ArrayList<>();
        comentarios2.add(comentario3);
        newPost.setComentarios(comentarios2);

        postRepository.update(newPost);

        System.out.println(postRepository.findById(newPost.getId().toString()));
        System.out.println(postRepository.findAll());

        postRepository.deleteAll();

        Post post1 = new Post();
        ArrayList<String> tags = new ArrayList<>();
        tags.add("java");
        tags.add("spring");
        tags.add("mongodb");
        post1.setEtiquetas(tags);
        postRepository.save(post1);

        Post post2 = new Post();
        tags = new ArrayList<>();
        tags.add("java");
        post2.setEtiquetas(tags);
        postRepository.save(post2);

        Post post3 = new Post();
        tags = new ArrayList<>();
        tags.add("spring");
        post3.setEtiquetas(tags);
        postRepository.save(post3);

        Iterable<Post> posts2 = postRepository.findAllByTag("java");
        posts2.forEach(post -> System.out.println(post.getId()));

        MongoUtil.close();
    }
}
