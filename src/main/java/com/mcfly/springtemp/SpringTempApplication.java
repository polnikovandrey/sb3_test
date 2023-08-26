package com.mcfly.springtemp;

import com.mcfly.springtemp.algorithms.items.StringArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTempApplication {

    private final MyProperties myProperties;

    @Autowired
    public SpringTempApplication(MyProperties myProperties) {
        this.myProperties = myProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringTempApplication.class, args);
    }

    @Bean
    StringArray stringArray() {
        System.out.println("### Custom property with @ConfigurationProperties: " + myProperties.getProp1());
        return new StringArray(new String[]{Integer.toString(myProperties.getProp1())});
    }

    @Bean
    StringArray anotherStringArray(MyProperties props) {
        System.out.println("### Custom property with @ConfigurationProperties: " + props.getProp2());
        return new StringArray(new String[]{props.getProp2()});
    }

    @Bean
    String withQualifier(StringArray anotherStringArray) {
        return anotherStringArray.getStrings()[0];
    }

    @Bean
    String resultingString(String withQualifier) {
        System.out.println("### Resulting string: " + withQualifier);
        return withQualifier;
    }


}
