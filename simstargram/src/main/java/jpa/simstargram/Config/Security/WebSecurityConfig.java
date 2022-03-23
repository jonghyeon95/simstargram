package jpa.simstargram.Config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
