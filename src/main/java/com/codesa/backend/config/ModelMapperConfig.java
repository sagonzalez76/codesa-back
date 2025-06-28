package com.codesa.backend.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
