package com.example.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<BlogPostRequestDto> getBlogPostsByAuthor(String author) {
        List<BlogPost> blogPosts = postRepository.findByAuthor(author);

        return blogPosts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BlogPostRequestDto getPostDtoByTitle(String title) {
        BlogPost postEntity = postRepository.findByTitle(title);

        if (postEntity != null) {
            return convertToDto(postEntity);
        } else {
            return null;
        }
    }

    private BlogPostRequestDto convertToDto(BlogPost blogPost) {
        return BlogPostRequestDto.builder()
                .id(blogPost.getId())
                .title(blogPost.getTitle())
                .author(blogPost.getAuthor())
                .content(blogPost.getContent())
                .build();
    }


}
