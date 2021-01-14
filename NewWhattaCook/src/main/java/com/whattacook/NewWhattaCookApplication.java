package com.whattacook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class NewWhattaCookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewWhattaCookApplication.class, args);
	}

}
