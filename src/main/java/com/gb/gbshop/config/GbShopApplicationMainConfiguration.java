package com.gb.gbshop.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@Configuration
@EnableAsync
@ComponentScan("com.gb.gbshop")
@EnableJpaRepositories("com.gb.gbshop")
@EnableAspectJAutoProxy
public class GbShopApplicationMainConfiguration {

    @Bean
    public ModelMapper modelMapper(List<PropertyMap> propertyMapList) {
        ModelMapper modelMapper = new ModelMapper();
        propertyMapList.stream()
                       .map(modelMapper::addMappings);
        return modelMapper;
    }

}
