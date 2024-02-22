package com.example.blog;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<BlogPost, String>{
    public BlogPost findByTitle(String title);

    public List<BlogPost> findAll();

    List<BlogPost> findByAuthor(String author);
}
