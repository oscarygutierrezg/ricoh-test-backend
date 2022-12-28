package com.ricoh.test.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

	private static final String[] AUTH_WHITELIST = {
			"/v3/api-docs/**",
			"/v3/api-docs",
			"/swagger-ui/**",
			"/v1/security/authenticate",
			"/v1/security/register"
	};

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtRequestFilter jwtRequestFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().disable().csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers(AUTH_WHITELIST).permitAll()
				.requestMatchers(HttpMethod.OPTIONS,"/v1/vacante*").permitAll()
				.requestMatchers(HttpMethod.OPTIONS,"/v1/categoria*").permitAll()
				.requestMatchers(HttpMethod.OPTIONS,"/v1/perfil/*").permitAll()
				.requestMatchers(HttpMethod.OPTIONS,"/v1/usuario/*").permitAll()
				.requestMatchers(HttpMethod.OPTIONS,"/v1/inscripcion/*").permitAll()
				.requestMatchers(HttpMethod.GET,"/v1/vacante*").hasAuthority("READ_PRIVILEGE")
				.requestMatchers(HttpMethod.POST,"/v1/vacante*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.PUT,"/v1/vacante/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.DELETE,"/v1/vacante/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.GET,"/v1/categoria*").hasAuthority("READ_PRIVILEGE")
				.requestMatchers(HttpMethod.POST,"/v1/categoria*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.PUT,"/v1/categoria/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.DELETE,"/v1/categoria/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.GET,"/v1/usuario*").hasAuthority("READ_PRIVILEGE")
				.requestMatchers(HttpMethod.POST,"/v1/usuario*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.PUT,"/v1/usuario/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.DELETE,"/v1/usuario/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.GET,"/v1/perfil*").hasAuthority("READ_PRIVILEGE")
				.requestMatchers(HttpMethod.POST,"/v1/perfil*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.PUT,"/v1/perfil/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.DELETE,"/v1/perfil/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.GET,"/v1/inscripcion*").hasAuthority("READ_PRIVILEGE")
				.requestMatchers(HttpMethod.POST,"/v1/inscripcion*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.PUT,"/v1/inscripcion/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.DELETE,"/v1/inscripcion/*").hasAuthority("WRITE_PRIVILEGE")
				.requestMatchers(HttpMethod.GET,"/external/v1/nobel*").hasAuthority("READ_PRIVILEGE")
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return  httpSecurity.build();
	}
}