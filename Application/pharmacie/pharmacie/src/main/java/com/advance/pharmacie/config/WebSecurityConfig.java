package com.advance.pharmacie.config;

import com.advance.pharmacie.service.Auth.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/swagger-ui.html",
                        "/v2/api-docs",
                        "/**/swagger-resources/**",
                        "/configuration/ui",
                        "/webjars/**",
                        "/caisse/**",
                        "/client/**",
                        "/depot/**",
                        "/activite/**",
                        "/produit/**",
                        "/famille/**",
                        "/employe/**",
                        "/activite/**",
                        "/etat/**",
                        "/stockArticle/**",
                        "/reglement/**",
                        "/fournisseur/**",
                        "/commande/**",
                        "/ligneDeCommande/**",
                        "/utilisateur/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }


    public static boolean comparePassword(String clear, String crypt) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        return bcrypt.matches(clear, crypt);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(utilisateurService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}

