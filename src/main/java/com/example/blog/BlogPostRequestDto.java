package com.example.blog;

import lombok.Builder;
import lombok.Data;

@Builder
public class BlogPostRequestDto {
    private Long id;
    private String title;
    private String author;
    private String content;

}
