package com.demo.demo;

import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService){
		return args -> {
			appUserService.createUser(new AppUserCreateRequest("ripudaman", "ripu502", "1234"));
		};
	}

	/**
	 * This is the first backend repo for the spring boot backend
	 * point to be covered will be
	 * 	1. CRUD API - done
	 * 	2. JWT Security - done https://youtu.be/VVn9OG9nfH0?t=6734
	 * 	3. All mapping- done REQUIRE revisit
	 * 	4. DATABASE - Transaction, native Queries, Cache - done REQUIRE REVISIT
	 * 	7. logging - done
	 * 	8. Global exception handling
	 *
	 * 	13 Unit testing and coverage
	 * 	5. How to move to the microservice Architecture
	 * 	15. Making documentation if any code snippets
	 * 	6. JWT with cookie, http only
	 * 	14. how to make the heirercy level role like admin can access all the customer level routes
	 * 	19. keyclock type authentication server.
	 * 	12 Deploying with docker
	 *
	 * 	9. Emailing and otp handling
	 * 	10. OAuth2 SSO and all
	 * 	11. multi tenent
	 * 	13 images and file handling
	 * 	14 logging to file
	 */

	/**
	 * 3 user
	 * 	admin
	 * 	seller
	 * 	customer
	 *
	 * shoes products
	 * brand
	 * price
	 * seller
	 *
	 * cart buy browse
	 *
	 *
	 */

	/**
	 * Closing on Day 1:
	 * reading https://www.baeldung.com/spring-boot-h2-database
	 * https://github.com/ripu502-deloitte/bookMyshow
	 */

//	https://www.javadevjournal.com/spring-security/spring-security-roles-and-permissions/

//	https://www.baeldung.com/role-and-privilege-for-spring-security-registration
}
