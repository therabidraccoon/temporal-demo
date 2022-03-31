package it.si2001.demo.temporal.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("it.si2001.demo")
public class TemporalProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemporalProducerApplication.class, args);
	}

}
