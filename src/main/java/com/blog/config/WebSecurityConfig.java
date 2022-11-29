package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .and()
                .formLogin(formConfigurer -> formConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/admin/posts")
                        .loginProcessingUrl("/login")
                        .permitAll())
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/");

        return http.build();

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
