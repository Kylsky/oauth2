package com.example.authentication.config;

import com.example.authentication.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class OAuth2Configuration implements AuthorizationServerConfigurer {
    @Resource
    private UserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Resource
    private AuthorizationCodeService authorizationCodeService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .pathMapping("/oauth/check_token","/oauth/hello")
                .authenticationManager(authenticationManager)
                //填写自定义的userService
                .userDetailsService(userService)
                //设置请求类型
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .authorizationCodeServices(authorizationCodeService)
        ;
    }
}
