package com.figtreelake.corbeanprocessor.singlechainexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner createCommandLineRunner(TypeFinder typeFinder) {
		return args -> {
			log.info(typeFinder.findType("Value"));
			log.info(typeFinder.findType(2.0));
			log.info(typeFinder.findType(7));
			log.info(typeFinder.findType(null));
		};
	}

}
