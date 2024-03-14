package org.estsoft.blogcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.estsoft.blogcrud.model.Article;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
}
