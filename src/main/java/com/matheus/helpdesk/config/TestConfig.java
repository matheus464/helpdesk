package com.matheus.helpdesk.config;

import com.matheus.helpdesk.config.services.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dBservice;

    @Bean
    public void instanciaDB(){
        this.dBservice.instanciaDB();
    }
}
