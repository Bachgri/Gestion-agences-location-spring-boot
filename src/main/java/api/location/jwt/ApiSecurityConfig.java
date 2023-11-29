package api.location.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import api.location.serviceImpl.AuthService; 

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthService authService;
	@Autowired 
	JwtAuthenticationFilter authenticationFilter;
	@Autowired
	private ApiAuthenticationEntryPoint authenticationEntryPoint;
	
 

	@Override
	public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/login","/register", "/owner/login", "/client/login", "/signup");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable();
		http.cors();
		http.authorizeRequests().antMatchers("/login","/register", "/owner/login", "/client/login", "/signup").permitAll()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("*","/location/users").hasAnyAuthority("ADMIN", "SUPERADMIN");
		http.authorizeRequests().antMatchers("*","/location/users/**").hasAnyAuthority("ADMIN", "SUPERADMIN");
		http.authorizeRequests().antMatchers("*","/location/users/**/**").hasAnyAuthority("ADMIN", "SUPERADMIN");
		http.authorizeRequests().antMatchers("*","/location/users/**/**/**").hasAnyAuthority("ADMIN", "SUPERADMIN");
		
	}	 

	@Bean
	public RegistrationBean jwtAuthFilterRegister(JwtAuthenticationFilter filter) {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
