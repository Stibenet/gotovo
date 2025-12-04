package ru.gotovoweb.gotovobackend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // --- ИСПОЛЬЗУЕМ ГЕНЕРАТОР КЛЮЧЕЙ ДЛЯ HS512 ---
    private final SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
    // Или, если вы хотите использовать конкретный ключ, убедитесь, что его строковое представление,
    // закодированное в байты (например, через Base64), дает >= 64 байта.
    // SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("...64+ байтный_ключ_в_base64..."));
    // Но проще и безопаснее использовать генератор.
    // ---

    private int jwtExpiration = 86400000; // 24 часа в миллисекундах

    public String generateToken(String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpiration);
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(secretKey, Jwts.SIG.HS512) // Используем Jwts.SIG.HS512
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        // Создаем парсер и получаем Claims
        return Jwts.parser()
                .verifyWith(secretKey) // Указываем ключ для проверки подписи
                .build() // Создаем JwtParser
                .parseSignedClaims(token) // Парсим подписанные Claims
                .getPayload() // Получаем полезную нагрузку (Claims)
                .getSubject(); // Получаем subject (username)
    }

    public boolean validateToken(String authToken) {
        try {
            // Создаем парсер, указываем ключ и парсим токен
            Jwts.parser()
                    .verifyWith(secretKey) // Указываем ключ
                    .build() // Создаем JwtParser
                    .parseSignedClaims(authToken); // Проверяем подпись и структуру
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            //log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            //log.error("JWT token is expired: {}", e.getMessage());
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            //log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            //log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}