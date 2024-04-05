package com.prueba.demo;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.prueba.demo.security.JWTFiltroAutorizacion;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(getClass());
	}


	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.exceptionHandling()
					.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
					.and().cors().and().csrf().disable()
					.addFilterAfter(new JWTFiltroAutorizacion(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/demo/**").permitAll()
					.antMatchers(HttpMethod.GET, "/demo/**").permitAll() 
					.antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll() 
					.antMatchers(HttpMethod.POST, "/swagger-ui.html/**").permitAll() 
					.antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
					.antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
					.antMatchers(HttpMethod.GET,"/v2/api-docs/**").permitAll()
					.antMatchers(HttpMethod.GET,"/springfox-swagger/**").permitAll()
					.antMatchers(HttpMethod.GET,"/webjar/**").permitAll()
					.antMatchers(HttpMethod.GET,"/webjars/**").permitAll()

					.anyRequest()
					
					.authenticated();
		}

	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
