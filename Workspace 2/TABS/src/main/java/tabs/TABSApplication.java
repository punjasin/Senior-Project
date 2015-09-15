package tabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import tabs.security.ApplicationSecurity;

@SpringBootApplication
public class TABSApplication {
	
//	@Bean
//	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
//		return new ApplicationSecurity();
//	}

    public static void main(String[] args) {
        SpringApplication.run(TABSApplication.class, args);
    }
}
