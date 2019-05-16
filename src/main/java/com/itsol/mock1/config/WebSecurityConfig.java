package com.itsol.mock1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan
@EnableAutoConfiguration

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //để kích hoạt spring sercurity ta cần phải viết một lớp kế thừa WebSecurityConfigurerAdapter
    @Autowired
    private UserDetailsService userDetailsService;
    //goi userdetailService de cau hinh
    @Bean
    public PasswordEncoder passwordEncoder(){ //dam nhan ma hoa mat khau
        return new BCryptPasswordEncoder();//ma hoa mat khau bang thuat toan BCrypt

    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected  void  configure(HttpSecurity http) throws  Exception{
        http.authorizeRequests()
                .antMatchers("register").permitAll()
                .antMatchers("/").hasRole("MENBER")
                .antMatchers("/hr").hasRole("ADMIN")
                .antMatchers("/manager").hasRole("MANAGER")
                .antMatchers("/teachlead").hasRole("TEACHLEAD")

                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

    }

}
