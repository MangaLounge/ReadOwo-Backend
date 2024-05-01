package com.readowo.api.mapping;

import com.readowo.api.ReadOwo.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration{
    @Bean // Tipo de singleton(?
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }
    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }
}
