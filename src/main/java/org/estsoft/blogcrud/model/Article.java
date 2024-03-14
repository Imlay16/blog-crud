package org.estsoft.blogcrud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.estsoft.blogcrud.dto.ArticleResponse;
import org.estsoft.blogcrud.dto.ArticleViewResponse;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Article {
    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime modifiedAt;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public ArticleResponse toResponse() {
        return new ArticleResponse(id, title, content);
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
