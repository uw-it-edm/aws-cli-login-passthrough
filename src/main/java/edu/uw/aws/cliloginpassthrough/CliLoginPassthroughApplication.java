package edu.uw.aws.cliloginpassthrough;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import edu.uw.aws.cliloginpassthrough.properties.PassthroughProperties;

@SpringBootApplication
@EnableConfigurationProperties(PassthroughProperties.class)
public class CliLoginPassthroughApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliLoginPassthroughApplication.class, args);
	}

}
