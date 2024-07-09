package ir.ma.filter.xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;

@SpringBootApplication(exclude = MessageSourceAutoConfiguration.class )
public class FilterXssApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterXssApplication.class, args);
	}

}
