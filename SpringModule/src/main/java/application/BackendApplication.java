package application;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("CrawlerModule")
@EntityScan("model")
@EnableJpaRepositories(basePackages= {"repository"})
@ComponentScan(basePackages = {"application"})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
