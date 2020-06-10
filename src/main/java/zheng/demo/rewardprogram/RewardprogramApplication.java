package zheng.demo.rewardprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "zheng.demo.rewardprogram")
public class RewardprogramApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RewardprogramApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RewardprogramApplication.class);
	}
}
