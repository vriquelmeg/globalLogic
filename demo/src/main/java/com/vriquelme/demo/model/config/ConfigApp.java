package com.vriquelme.demo.model.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vriquelme.demo.model.helper.LoginHelper;
import com.vriquelme.demo.model.helper.UserHelper;
import com.vriquelme.demo.model.repository.UserRepository;
import com.vriquelme.demo.model.services.LoginServices;
import com.vriquelme.demo.model.services.TokenAuthenticationServices;
import com.vriquelme.demo.model.services.UserServices;
import com.vriquelme.demo.model.services.impl.LoginServicesImpl;
import com.vriquelme.demo.model.services.impl.TokenAuthenticationServicesImpl;
import com.vriquelme.demo.model.services.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

    @Bean
    public LoginHelper loginHelper(LoginServices p_loginServices, UserServices p_userServices, ObjectMapper p_objectMapper) {
        return new LoginHelper(p_loginServices, p_userServices,p_objectMapper);
    }

    @Bean
    public TokenAuthenticationServices tokenAuthenticationServices(@Value("${jwt.secret}") String secret) {
        return new TokenAuthenticationServicesImpl(secret);
    }

    @Bean
    public LoginServices loginServices(UserRepository userRepository, TokenAuthenticationServices tokenAuthenticationServices) {
        return new LoginServicesImpl(userRepository, tokenAuthenticationServices);
    }

    @Bean
    public UserServices userServices(UserRepository userRepository, TokenAuthenticationServices tokenAuthenticationServices){
        return new UserServicesImpl(userRepository, tokenAuthenticationServices);
    }

    @Bean
    public UserHelper userHelper(UserServices userServices, ObjectMapper objectMapper) {
        return new UserHelper(userServices, objectMapper);
    }
}
