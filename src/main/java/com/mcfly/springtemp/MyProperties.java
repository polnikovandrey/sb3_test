package com.mcfly.springtemp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("com.mcfly")
@Getter
@Setter
public class MyProperties {

    private int prop1;
    private String prop2;

}
