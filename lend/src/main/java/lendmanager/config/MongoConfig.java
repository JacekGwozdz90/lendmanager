package lendmanager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;


@Configuration
@EnableMongoRepositories(basePackages={"lendmanager.mongo"})
@ComponentScan(basePackages={"lendmanager.mongo"})
class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "lend";
	}

	@Override
	public Mongo mongo() throws Exception {
		  return new Mongo();
	}
	
}
