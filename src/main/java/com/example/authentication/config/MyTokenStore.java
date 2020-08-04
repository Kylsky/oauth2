package com.example.authentication.config;

import com.example.authentication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

public class MyTokenStore extends RedisTokenStore {
    private static final Logger logger = LoggerFactory.getLogger(MyTokenStore.class);

    public MyTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        logger.info(authentication.getName());
        super.storeAccessToken(token, authentication);
    }
}