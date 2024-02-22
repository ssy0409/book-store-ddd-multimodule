package kr.ssy.bookstore2.frontapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "kr.ssy.bookstore2")
public class FrontApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApiApplication.class, args);
    }
}
