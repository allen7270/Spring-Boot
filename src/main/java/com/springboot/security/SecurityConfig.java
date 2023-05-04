package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        unAuth html
        http.exceptionHandling().accessDeniedPage("/unAuth.html");

        http
                .formLogin()
                .loginPage("/login.html") // login page
                .loginProcessingUrl("/login")  // form action
                .defaultSuccessUrl("/login/index").permitAll() // login successful url
                .and().authorizeRequests() // verification required
                .antMatchers("/user").permitAll() // no verification required
//                .antMatchers("/login/index").hasAuthority("admin") // 權限(admin) - UserDetailService 單一權限
//                .antMatchers("/login/index").hasAnyAuthority("admin", "manager") // 多個權限
                .antMatchers("/login/index").hasRole("sale") // 需加前綴 ROLE_
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
