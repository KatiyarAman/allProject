package com.db.multi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClientFactory;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.db.multi.mongo.repo",mongoTemplateRef = "mongoTemplate" )
public class MongoDBConfig {

    @Bean
    public com.mongodb.client.MongoClient mongo() {
    	
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return  MongoClients.create(mongoClientSettings);
    }
//    @Bean
//    public MongoClientFactoryBean mongo() {
//      ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
//
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//       //mongo.setHost("localhost");
//       // mongo.setPort(27017);
//        mongo.setConnectionString(connectionString);
//        return mongo;
//    }

    @Bean("mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "test");
      }
  

}
