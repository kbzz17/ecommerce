package jw.project.ecommerce.infrastructure.user.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenParser {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private static final String HEADER_PREFIX = "Bearer ";

    public Optional<String> extract(String header) {
        if (header == null || header.isBlank()) {
            return Optional.empty();
        }
        if (header.length() < 7) {
            return Optional.empty();
        }
        return Optional.of(header.substring(HEADER_PREFIX.length()));
    }

    public AuthenticatedAccount parse(String token) {
        try {
            Claims jwt = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return new AuthenticatedAccount(((Number)jwt.get("id")).longValue(), (List<String>) jwt.get("role"));
        } catch (SignatureException | MalformedJwtException e) {
            throw new RuntimeException("Invalid Jwt Token");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Expired Jwt Token");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("UnsupportedJwtException");
        }
    }
}
