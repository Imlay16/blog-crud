package org.estsoft.blogcrud.controller;

import org.estsoft.blogcrud.dto.ArticleResponse;
import org.estsoft.blogcrud.dto.ArticleViewResponse;
import org.estsoft.blogcrud.model.Article;
import org.estsoft.blogcrud.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ViewController {
    private final BlogService service;
    public ViewController(BlogService service) {
        this.service = service;
    }

    @GetMapping("/articles")
    public String showAll(Model model) {
        List<ArticleViewResponse> articles = service.selectAllArticles().stream().map(ArticleViewResponse::new).toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String showOneArticle(@PathVariable Long id, Model model) {
        ArticleViewResponse articleViewResponse = new ArticleViewResponse(service.selectById(id));
        model.addAttribute("article", articleViewResponse);
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {
            model.addAttribute("article", new Article());
        }
        else {
            model.addAttribute("article", service.selectById(id));
        }
        return "newArticle";
    }
}
