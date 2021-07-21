package com.devdojo.renan.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//Configuração que irá ser automaticamente carregada pelos filtros com a annotation abaixo
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Neste método será configurado o que queremos proteger com o protocolo http
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Quando se receber uma requisição http, digo que todas elas deverão passar pela autorização
        //Todas as requests que baterem no controller deverão passar por esta autenticação básica
        //CSRF Token: Utilizado para impedir que outro usuário execute ações em nome do user anteriormente logado
        //Medida de segurança utilizada de modo a invalidar tentativas de passagem de identidade.
        http.csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // O withHttpOnlyFalse é para prover o cookie a aplicação frontend
                //.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    //O método abaixo terá como objetivo a criação de um usuário em memória
    //É necessário definir uma criptografia para a senha do usuário em memória
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded {}", passwordEncoder.encode("test"));
        auth.inMemoryAuthentication()
                .withUser("renan")
                .password(passwordEncoder.encode("academy"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("devdojo")
                .password(passwordEncoder.encode("academy"))
                .roles("USER");
    }
}
