package UPT_PL.Team_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "UPT_PL.Team_3.repository")
@EntityScan(basePackages = "UPT_PL.Team_3.model")
public class Team3Application {
	public static void main(String[] args) {
		SpringApplication.run(Team3Application.class, args);
	}
}
