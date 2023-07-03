package uz.pixel.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@SpringBootApplication
public class SpringmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmongoApplication.class, args);
	}

	@Bean
	public MongoTemplate mongoTemplate(){

		return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/pdpjava"));

	}

}
