package com.atdecap.springbootbackstage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication()
@EnableCaching
public class SpringbootbackstageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootbackstageApplication.class, args);
    }

}
