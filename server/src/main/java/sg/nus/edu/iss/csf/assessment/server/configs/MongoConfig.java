package sg.nus.edu.iss.csf.assessment.server.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static sg.nus.edu.iss.csf.assessment.server.Constants.*;

@Configuration
public class MongoConfig {
  
  @Value("${mongo.url}")
  private String mongoUrl;

  @Bean
  public MongoTemplate createMovies(){
    MongoClient mongoClient = MongoClients.create(mongoUrl);
    return new MongoTemplate(mongoClient, DATABASE_MOVIES);
  }
}
