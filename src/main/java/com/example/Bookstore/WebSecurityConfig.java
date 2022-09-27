package com.example.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Bookstore.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.authorizeRequests(auth -> {
			auth.antMatchers("/css/**").permitAll();
			//hasRole is not working with H2
			//auth.antMathers("/owners/**").hasRole("ADMIN")
			auth.antMatchers("/main/**").permitAll();
			auth.antMatchers("/add/**").hasAuthority("ADMIN");
			auth.anyRequest().authenticated();
		})
				.formLogin()
				.defaultSuccessUrl("/main", true).and()
				.logout().permitAll().and()
				.httpBasic(Customizer.withDefaults()).build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}


}
