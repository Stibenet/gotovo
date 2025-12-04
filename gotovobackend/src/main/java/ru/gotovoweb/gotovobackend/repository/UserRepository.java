package ru.gotovoweb.gotovobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gotovoweb.gotovobackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
