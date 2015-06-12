package lendmanager.config;

import lendmanager.Application;
import lendmanager.notifications.EmailScheduler;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
@Import(MongoConfig.class)
@EnableScheduling
class ApplicationConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocation(new ClassPathResource("/persistence.properties"));
		return propertyPlaceholderConfigurer;
	}
    @Bean
    public EmailScheduler task() {
        return new EmailScheduler();
    }
}