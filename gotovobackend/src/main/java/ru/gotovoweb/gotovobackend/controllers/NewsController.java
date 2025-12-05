package ru.gotovoweb.gotovobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails; // Добавлен импорт
import org.springframework.web.bind.annotation.*;
import ru.gotovoweb.gotovobackend.entity.News;
import ru.gotovoweb.gotovobackend.entity.User;
import ru.gotovoweb.gotovobackend.repository.UserRepository;
import ru.gotovoweb.gotovobackend.services.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<News> createNews(@RequestBody News news, Authentication authentication) {
        // Правильный способ получения email
        // 1. Получаем Principal (который является org.springframework.security.core.userdetails.User)
        Object principal = authentication.getPrincipal();

        // 2. Приводим его к интерфейсу UserDetails
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername(); // Используем getUsername()

            // 3. Загружаем вашу сущность User из базы данных по email
            User author = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found after authentication"));

            // 4. Устанавливаем найденного автора
            return ResponseEntity.ok(newsService.createNews(news, author));
        } else {
            // На всякий случай, если Principal окажется другого типа
            throw new RuntimeException("Principal is not an instance of UserDetails");
        }
    }


    @PutMapping("/{id}") // Только для ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News news) {
        return ResponseEntity.ok(newsService.updateNews(id, news));
    }

    @DeleteMapping("/{id}") // Только для ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}