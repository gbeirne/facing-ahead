package com.facingahead.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@SpringBootApplication
public class Application {

	private static final String DB = "facingAhead";
	private static final String URI_KEY = "MONGO_URI";

	public static void main(String[] args) {
		Map<String, String> env = System.getenv();
		String uri = env.get(URI_KEY);
		System.setProperty("spring.data.mongodb.database", DB);
		System.setProperty("spring.data.mongodb.uri", uri);


		SpringApplication.run(Application.class, args);
	}

	@Configuration
	protected static class SecurityConfiguration {

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

		@Bean
		protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			//Holy shit, this works. Why does this work?!?!? There is something definitely screwy about how I'm getting
			//access is being gated but it works. What the actual f...
			http.formLogin().loginPage("/#/login").permitAll().and().cors().disable().csrf().disable().httpBasic().and()
					.authorizeRequests()
					.antMatchers("/#/login").permitAll();
//			http.httpBasic().disable().authorizeRequests()
//					.antMatchers("/index.html", "/home.html", "/login.html", "/static/**", "/", "/lib/**", "/css/**",
//							"/images/**", "/partials/**", "/create/user")
//					.permitAll().anyRequest().authenticated().and().csrf().disable();
			return http.build();
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
