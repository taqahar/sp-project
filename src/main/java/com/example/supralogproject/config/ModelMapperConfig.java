package com.example.supralogproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration for ModelMapper.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Provides a ModelMapper bean.
     *
     * @return ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

