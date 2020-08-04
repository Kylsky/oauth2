package com.example.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Configuration
public class AuthorizationCodeService extends RandomValueAuthorizationCodeServices {
    @Resource
    private RedisTemplate<String, OAuth2Authentication> redisTemplate;

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        redisTemplate.opsForValue().set(code, authentication, 1, TimeUnit.MINUTES);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        OAuth2Authentication auth2Authentication;
        try {
            auth2Authentication = redisTemplate.opsForValue().get(code);
        } catch (Exception e) {
            return null;
        }
        if (auth2Authentication != null) {
            redisTemplate.delete(code);
        }
        return auth2Authentication;
    }
}
