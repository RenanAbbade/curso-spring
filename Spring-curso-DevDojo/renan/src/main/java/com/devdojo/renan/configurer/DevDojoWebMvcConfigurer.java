package com.devdojo.renan.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
//Configuração do recurso de paganização
@Configuration
public class DevDojoWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            PageableHandlerMethodArgumentResolver pageableHandler = new PageableHandlerMethodArgumentResolver();
        pageableHandler.setFallbackPageable(PageRequest.of(0, 5));
        resolvers.add(pageableHandler);
        //Com esta function, estou dizendo que por default, a primeira página será apresentada.
        //E terei 5 objetos de retorno.. Se quizer modificar, é só sobrescrever com size e page na query String
    }
}
