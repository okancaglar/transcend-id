package com.example.demo.authentication;


import com.example.demo.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ApplicationConfiguration {

	private OfficerRepository officerRepository;

	@Autowired
	public ApplicationConfiguration(OfficerRepository officerRepository) {
		this.officerRepository = officerRepository;
	}


	@Bean
	UserDetailsService userDetailsService(){
		return username -> officerRepository.findById(Integer.parseInt(username)).orElseThrow(() ->
				new UsernameNotFoundException("officer not exist"));
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		authProvider.setUserDetailsService(userDetailsService());

		return authProvider;
	}


}
