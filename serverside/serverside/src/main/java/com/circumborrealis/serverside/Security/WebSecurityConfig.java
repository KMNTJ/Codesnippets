package com.circumborrealis.serverside.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.circumborrealis.serverside.Service.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/css/**", "/signup", "/saveuser").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/yoursnips", true)
			.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
			.invalidateHttpSession(true) 
			.permitAll();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
/*
 * Täältä lisää oppeja
 * https://www.concretepage.com/spring-boot/spring-boot-mvc-security-custom-login-and-logout-thymeleaf-csrf-mysql-database-jpa-hibernate-example
 * 
  @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/signup", "/saveuser").permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/deletebook/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/listbooks")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/", "/home").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//			.logout()
//				.permitAll();
//	}
*/
	// tämä on devausvaiheen security ohitin!
	// http.csrf().disable(); 
	// CSRF on pakko joko laittaa toimimaan oikein, tai sitten disabloida, jotta saan ajax postin suoritettua.
	// Nyt 403/ilman 500/(-vika muualla)
	// oikeaoppinen tapa liittää mukaan cors token ajax postiin. 
	// https://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/html5/#csrf-include-csrf-token-ajax
	
	// Täällä aseteltaisiin mm. default loginSuccess ja loginError page sekä default logoutSuccess page ja logoutError page.
	
	/* "Tiukempi versio autentikointi ja authorisaatio asetuksista"
	  http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
	 */
	/* "Asetus esimerkkejä"
	  http
	  	.authorizeRequests()
	  		.antMatchers("/resources/**","/signup", "/about").permitAll()
	  		.antMatchers("/admin/**").hasRole("ADMIN")
	  		.anyRequest().authenticated()
	  		.and()
	  		.......
	  	.loginForm();
	 */
	
	
	//kait lähinnä testausvaiheen konfiguraatio.
	//oletan että tämä toimii niin, että luo lähtökohtaisen tilanteen sisään 
	//käyttäjän u salasanalla p roolissa USER
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("u").password("p").roles("USER");
	}
	*/
	
	
	// Mahdollisesti väärä toteutus: 
	// vertaa userDetailsService <-> userDetailsService()











