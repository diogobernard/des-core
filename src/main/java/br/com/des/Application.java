package br.com.des;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by diogobernard on 22/04/17.
 */

@Configuration
@SpringBootApplication(scanBasePackages = { "br.com.des.core" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
