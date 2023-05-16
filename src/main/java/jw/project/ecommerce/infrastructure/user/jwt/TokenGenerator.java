package jw.project.ecommerce.infrastructure.user.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jw.project.common.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

@Component
public class TokenGenerator {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(Long id, Set<Role> role) {
        return generateJwtToken(id, role, 6);
    }

    public String generateRefreshToken(Long id, Set<Role> role) {
        return generateJwtToken(id, role, 24);
    }

    private String generateJwtToken(Long id, Set<Role> role, int hour) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("role", role);

        return Jwts.builder()
                .signWith(createSecretKey())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofHours(hour).toMillis()))
                .compact();
    }

    private SecretKey createSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
