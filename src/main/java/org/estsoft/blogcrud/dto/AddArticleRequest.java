package org.estsoft.blogcrud.dto;

import lombok.Getter;
import org.estsoft.blogcrud.model.Article;

@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(title, content);
    }
}
