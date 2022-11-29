package com.blog.config;

import com.blog.models.CurrentUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrentUserConfig {

    @Bean
    public CurrentUser getCurrentUser(){
        return new CurrentUser();
    }
}
