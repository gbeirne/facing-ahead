package com.facingahead.app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class Application {

	private static final String CONNECTION_STRING = "mongodb://gbeirne10:JcWQsd30ESMO6k91@ac-oyzxan7-shard-00-00.q4sdkmc.mongodb.net:27017,ac-oyzxan7-shard-00-01.q4sdkmc.mongodb.net:27017,ac-oyzxan7-shard-00-02.q4sdkmc.mongodb.net:27017/?ssl=true&replicaSet=atlas-6dda6o-shard-0&authSource=admin&retryWrites=true&w=majority";
	private static final String NEW_CONNECTION_STRING = "mongodb+srv://gbeirne10:JcWQsd30ESMO6k91@fa-db.q4sdkmc.mongodb.net/?retryWrites=true&w=majority";
	private static final String HOST = "ac-oyzxan7-shard-00-02.q4sdkmc.mongodb.net";
	private static final String PORT = "27017";
	private static final String DB = "facingAhead";
	private static final String USER = "gbeirne10";
	private static final String PASS = "JcWQsd30ESMO6k91";
	private static final String URI_KEY = "MONGO_URI";

	public static void main(String[] args) {
		Map<String, String> env = System.getenv();
		String uri = env.get(URI_KEY);
//		System.setProperty("spring.data.mongodb.host", HOST);
//		System.setProperty("spring.data.mongodb.port", PORT);
		System.setProperty("spring.data.mongodb.database", DB);
//		System.setProperty("spring.data.mongodb.username", USER);
//		System.setProperty("spring.data.mongodb.password", PASS);
		System.setProperty("spring.data.mongodb.uri", uri);


		SpringApplication.run(Application.class, args);
	}

	@Configuration
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		SecUserDetailsService userDetailsService;

		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		// Enable jdbc authentication
		@Autowired
		public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
			builder.userDetailsService(userDetailsService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/index.html", "/home.html", "/login.html", "/static/**", "/", "/lib/**", "/css/**",
							"/images/**", "/partials/**", "/create/user")
					.permitAll().anyRequest().authenticated().and().csrf().disable();
		}

//		 @Autowired
//		 public void configureGlobal(AuthenticationManagerBuilder auth) throws
//		 Exception {
//			 System.out.println("Config Global called");
//		 auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//		 auth.inMemoryAuthentication().withUser("nalah").password("nalah").roles("USER");
//		 }
	}
}
