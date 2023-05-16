package jw.project.ecommerce.infrastructure.user.redis;

import jw.project.ecommerce.domain.user.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisTokenRepository implements TokenRepository {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void save(String refreshToken) {
        String key = getKey(refreshToken);
        redisTemplate.opsForValue().set(key, refreshToken);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
    }

    @Override
    public Boolean deleteToken(String refreshToken) {
        String key = getKey(refreshToken);
        return redisTemplate.unlink(key);
    }

    @Override
    public Boolean existsKey(String refreshToken) {
        String key = getKey(refreshToken);
        return redisTemplate.hasKey(key);
    }

    private String getKey(String refreshToken) {
        return "RT:" + refreshToken;
    }
}
