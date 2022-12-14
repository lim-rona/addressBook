package com.example.addressBookServer.configurations;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    @Value("${cors.pathMapping}")
    String pathMapping;

    @Value("${cors.origins}")
    String origins;


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new CORSConfiguration(pathMapping,origins);
    }
    
}
