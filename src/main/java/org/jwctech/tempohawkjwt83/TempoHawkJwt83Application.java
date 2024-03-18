package org.jwctech.tempohawkjwt83;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppJwtProperties.class)
public class TempoHawkJwt83Application {

	public static void main(String[] args) {
		SpringApplication.run(TempoHawkJwt83Application.class, args);
	}

}
