package ru.gotovoweb.gotovobackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.gotovoweb.gotovobackend.entity.News;
import ru.gotovoweb.gotovobackend.entity.User;
import ru.gotovoweb.gotovobackend.services.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping // Публичный доступ
    public ResponseEntity<List<News>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @PostMapping // Только для USER и ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<News> createNews(@RequestBody @Valid News news, Authentication authentication) {
        User author = (User) authentication.getPrincipal();
        return ResponseEntity.ok(newsService.createNews(news, author));
    }

    @PutMapping("/{id}") // Только для ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody @Valid News news) {
        return ResponseEntity.ok(newsService.updateNews(id, news));
    }

    @DeleteMapping("/{id}") // Только для ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}