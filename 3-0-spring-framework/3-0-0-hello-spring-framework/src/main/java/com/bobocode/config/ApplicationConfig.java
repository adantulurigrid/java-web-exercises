package com.bobocode.config;

import com.bobocode.TestDataGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.bobocode.dao",
        "com.bobocode.service"
})
public class ApplicationConfig {

    @Bean
    public TestDataGenerator dataGenerator() {
        return new TestDataGenerator();
    }
}