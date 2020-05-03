package rbu.circuitbreaker.resilience4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Resilience4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jApplication.class, args);
	}

	@Bean
	public Random getRandom() {
		return new Random();
	}
}
