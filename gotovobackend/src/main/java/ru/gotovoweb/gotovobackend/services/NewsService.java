package ru.gotovoweb.gotovobackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gotovoweb.gotovobackend.entity.News;
import ru.gotovoweb.gotovobackend.entity.User;
import ru.gotovoweb.gotovobackend.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service // Эта аннотация говорит Spring, что это сервисный слой
public class NewsService {

    @Autowired
    private NewsRepository newsRepository; // Репозиторий для работы с БД

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            return news.get();
        } else {
            throw new RuntimeException("News not found with id: " + id);
        }
    }

    public News createNews(News news, User author) {
        news.setAuthor(author); // Устанавливаем автора, переданного из контроллера
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News updatedNews) {
        Optional<News> existingNewsOpt = newsRepository.findById(id);
        if (existingNewsOpt.isPresent()) {
            News existingNews = existingNewsOpt.get();
            // Обновляем поля
            existingNews.setTitle(updatedNews.getTitle());
            existingNews.setContent(updatedNews.getContent());
            // Обновляем дату, если нужно
            // existingNews.setCreatedAt(LocalDateTime.now()); // или оставить старую
            return newsRepository.save(existingNews);
        } else {
            throw new RuntimeException("News not found with id: " + id);
        }
    }

    public void deleteNews(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
        } else {
            throw new RuntimeException("News not found with id: " + id);
        }
    }
}
