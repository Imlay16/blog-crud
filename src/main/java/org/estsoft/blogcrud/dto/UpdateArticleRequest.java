package org.estsoft.blogcrud.dto;

import lombok.Getter;

@Getter
public class UpdateArticleRequest {
    private String title;
    private String content;
}
