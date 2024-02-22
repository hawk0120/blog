package com.example.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.SystemEventListener;
import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/posts")
public class Controller {

    private final PostService postService;

    public Controller(PostService postService){
        this.postService = postService;
    }
    @GetMapping()
    public List<BlogPost> getBlogPosts() {
     //   BlogPostRequestDto blogPostRequestDto = postService.get

        return null;
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BlogPostRequestDto>> getBlogPostsByAuthor(@RequestParam String author) {
        List<BlogPostRequestDto> blogPosts = postService.getBlogPostsByAuthor(author);

        if (!blogPosts.isEmpty()) {
            return ResponseEntity.ok(blogPosts);
        } else {
            // No posts found for the given author, return 404 response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
