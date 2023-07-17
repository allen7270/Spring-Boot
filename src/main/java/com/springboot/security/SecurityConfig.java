package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration // spring IoC container -> DI -> loose coupling
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Bean // 讓其他物件可以注入
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

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
        http.logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login.html");
        http
                .formLogin()
                .loginPage("/login.html") // login page
                .loginProcessingUrl("/login")  // form action
                .defaultSuccessUrl("/home.html", true).permitAll() // login successful url

                .and().authorizeRequests() // verification required
                .antMatchers("/project/user").permitAll() // no verification required
                .antMatchers("/register.html").permitAll()
                .antMatchers("/js/register.js").permitAll()
                .antMatchers("/js/showMsg.js").permitAll()
                .antMatchers("/css/login.css").permitAll()
//                .antMatchers("/login/index").hasAuthority("admin") // 權限(admin) - UserDetailService 單一權限
//                .antMatchers("/login/index").hasAnyAuthority("admin", "manager") // 多個權限
                .antMatchers("/login/index").hasRole("sale") // Prefix ROLE_
                .anyRequest().authenticated()

                .and().rememberMe().tokenRepository(this.persistentTokenRepository()) // cookie remember
                .tokenValiditySeconds(1) // time(s)
                .userDetailsService(this.userDetailsService)

                .and().csrf().disable();
    }
}
