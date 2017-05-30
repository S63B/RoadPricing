package Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"Project", "com.S63B.domain.Entities"})
@EnableJpaRepositories({"Project", "com.S63B.domain.Entities"})
@ComponentScan(basePackages = {"Project", "com.S63B.domain.Entities"})
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}
