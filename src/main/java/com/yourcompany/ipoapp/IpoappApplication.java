package com.yourcompany.ipoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
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
		// This is where you can add initial data loading if needed
	}
}
