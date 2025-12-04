package ru.gotovoweb.gotovobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gotovoweb.gotovobackend.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
