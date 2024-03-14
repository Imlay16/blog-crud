package org.estsoft.blogcrud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.estsoft.blogcrud.dto.AddArticleRequest;
import org.estsoft.blogcrud.dto.UpdateArticleRequest;
import org.estsoft.blogcrud.external.ExternalApiParser;
import org.estsoft.blogcrud.model.Article;
import org.estsoft.blogcrud.repository.BlogRepository;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class BlogService {
    private final BlogRepository repository;
    private final ExternalApiParser parser;

    public BlogService(BlogRepository blogRepository, ExternalApiParser parser) {
        this.repository = blogRepository;
        this.parser = parser;
    }
    public List<Article> selectAllArticles() {
        return repository.findAll();
    }
    public Article selectById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
    public Article saveOneArticle(AddArticleRequest request) {
        return repository.save(request.toEntity());
    }
    @Transactional
    public Article updateOneArticle(Long id, UpdateArticleRequest request) {
        Article article = selectById(id);
        String title = article.getTitle();
        String content = article.getContent();

        if (title != request.getTitle()) {
            title = request.getTitle();
        }

        if (content != request.getContent()) {
            content = request.getContent();
        }

        article.update(title, content);
        return article;
    }

    public void deleteOneArticle(Long id) {
        repository.deleteById(id);
    }

    public List<Article> saveBulkArticles() {
        List<Article> articles = parsedArticles();
        return repository.saveAll(articles);
    }

    private List<Article> parsedArticles() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        String responseJson = parser.fetchDataFromApi(url);

        ObjectMapper objectMapper = new ObjectMapper();
        List<LinkedHashMap<String, String>> jsonMapList = new ArrayList<>();

        try {
            jsonMapList = objectMapper.readValue(responseJson, List.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json", e.getMessage());
        }

        return jsonMapList.stream()
                .map(hashMap -> new Article(hashMap.get("title"), hashMap.get("body")))
                .toList();
    }
}
