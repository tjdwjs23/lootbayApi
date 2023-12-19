package kr.ne.abc.lootbayapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class LootbayApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LootbayApiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LootbayApiApplication.class, args);
    }

}
