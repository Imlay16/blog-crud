package org.estsoft.blogcrud.dto;

import lombok.Getter;
import org.estsoft.blogcrud.model.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article) {
        id = article.getId();
        title = article.getTitle();
        content = article.getContent();
        createdAt = article.getCreatedAt();
    }
}
