package ru.gotovoweb.gotovobackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gotovoweb.gotovobackend.entity.User;
import ru.gotovoweb.gotovobackend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections; // или создайте коллекцию с правами

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Преобразуем нашу сущность User в Spring Security UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // обычно username, но мы используем email
                .password(user.getPassword()) // хешированный пароль из БД
                .authorities(getAuthorities(user)) // получаем права доступа (роли)
                .accountExpired(false) // можно настроить по необходимости
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    private java.util.Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Преобразуем нашу роль в GrantedAuthority, понятную Spring Security
        // Spring Security ожидает роли в формате "ROLE_XXX"
        String role = "ROLE_" + user.getRole().name(); // например, "ROLE_USER"
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
