package br.com.canhette.pfood.infrastructure.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//TODO mudar como é feito o webSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // sempre q precisar de uma instacia AuthenticationSuccessHandler, é esse metodo q ele chama
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandlerImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/images/**","/css/**", "/js/**", "/public", "/sbpay").permitAll()
                .antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())
                .antMatchers("/restaurante/**").hasRole(Role.RESTAURATE.toString())
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login-error")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll();
    }
}
