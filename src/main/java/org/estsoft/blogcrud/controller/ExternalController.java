package org.estsoft.blogcrud.controller;

import org.estsoft.blogcrud.external.ExternalApiParser;
import org.estsoft.blogcrud.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalController {

    private final BlogService service;
    public ExternalController(BlogService service) {
        this.service = service;
    }

    @GetMapping("/api/test")
    public void test() {
        service.saveBulkArticles();
    }
}
