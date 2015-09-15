//package tabs.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//@Configuration
//public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().fullyAuthenticated().and()
//				.httpBasic().and().csrf().disable();
//	}
//}
