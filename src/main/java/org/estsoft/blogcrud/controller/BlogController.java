package org.estsoft.blogcrud.controller;

import org.estsoft.blogcrud.dto.AddArticleRequest;
import org.estsoft.blogcrud.dto.ArticleResponse;
import org.estsoft.blogcrud.dto.UpdateArticleRequest;
import org.estsoft.blogcrud.model.Article;
import org.estsoft.blogcrud.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {

    private final BlogService service;

    public BlogController(BlogService blogService) {
        service = blogService;
    }

    @GetMapping("/api/articles")
    @ResponseBody
    public ResponseEntity<List<ArticleResponse>> selectAllArticles() {

        List<ArticleResponse> articleResponses = service.selectAllArticles().stream()
                .map(x -> x.toResponse()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(articleResponses);
    }

    @GetMapping("/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<ArticleResponse> selectById(@PathVariable Long id) {
        try {
            service.selectById(id).toResponse();
            return ResponseEntity.ok(service.selectById(id).toResponse());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/api/articles")
    @ResponseBody
    public ResponseEntity<ArticleResponse> save(@RequestBody AddArticleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveOneArticle(request).toResponse());
    }

    @PutMapping("/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<ArticleResponse> update(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateOneArticle(id, request).toResponse());
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteOneArticle(id);
        return ResponseEntity.ok().build();
    }
}
