package com.yourcompany.ipoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class IpoappApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IpoappApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager abc(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
	@Override
	public void run(String... args) throws Exception {
		// initial data
	}
}
