package com.devdojo.renan.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//Configuração que irá ser automaticamente carregada pelos filtros com a annotation abaixo
@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)//Quando incluida esta annotation, é gerado um password automatico, também habilitando os recursos de Spring Security por método
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /***
     * Filtros Spring
     * BasicAuthenticationFilter
     * DefaultLoginPageGeneratingFilter
     * DefaultLoginPageGeneratingFilter
     *
     */

    //Neste método será configurado o que queremos proteger com o protocolo http
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Quando se receber uma requisição http, digo que todas elas deverão passar pela autorização
        //Todas as requests que baterem no controller deverão passar por esta autenticação básica
        //CSRF Token: Utilizado para impedir que outro usuário execute ações em nome do user anteriormente logado
        //Medida de segurança utilizada de modo a invalidar tentativas de passagem de identidade.

        //O cross-site request forgery(csrf), em português falsificação de solicitação entre sites, também conhecido como ataque de um clique ou montagem de sessão, é um tipo de exploit malicioso de um website, no qual comandos não autorizados são transmitidos a partir de um usuário em quem a aplicação web confia.
        http.csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // O withHttpOnlyFalse é para prover o cookie a aplicação frontend
                //.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
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
