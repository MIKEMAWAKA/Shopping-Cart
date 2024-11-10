package com.mike.shoppingcart.appConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AtomicLong cartIdGenerator() {
        return new AtomicLong(1);  // Initialize with a starting value if needed
    }
}