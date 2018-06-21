package com.UserTask.mvc.UserSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.UserTask.mvc.dao.UserDAO;
import com.UserTask.mvc.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserDAO.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter /* implements Filter */ {
	@Autowired
	private UserServiceImpl userDetailsService;

	// private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		handler.setUseReferer(true);
		return handler;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider authenticationProvider = new
	 * DaoAuthenticationProvider();
	 * authenticationProvider.setUserDetailsService(userDetailsService);
	 * authenticationProvider.setPasswordEncoder(passwordEncoder()); return
	 * authenticationProvider; }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("**/secured/**").authenticated().anyRequest().permitAll().and().formLogin()
				.permitAll();

	}

}

/*
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.csrf().disable();
 * http.authorizeRequests().antMatchers("**'/secured/**").authenticated()
 * .anyRequest().permitAll() .and()
 * .formLogin().loginPage("http://localhost:8080/#/").usernameParameter("email")
 * .passwordParameter("password").successHandler(new
 * AuthenticationSuccessHandler() {
 * 
 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
 * HttpServletResponse response, Authentication authentication) throws
 * IOException, ServletException { redirectStrategy.sendRedirect(request,
 * response, "http://localhost:8081/usertask/secured/gettingtask"); }
 * }).permitAll();
 * loginPage("http://localhost:8080/#/").usernameParameter("email")
 * .passwordParameter("j_password") }
 */

/*
 * @Override public void doFilter(ServletRequest req, ServletResponse res,
 * FilterChain chain) throws IOException, ServletException {
 * 
 * HttpServletRequest request = (HttpServletRequest) req; HttpServletResponse
 * response = (HttpServletResponse) res;
 * 
 * response.setHeader("Access-Control-Allow-Origin",
 * request.getHeader("Origin"));
 * response.setHeader("Access-Control-Allow-Credentials", "true");
 * response.setHeader("Access-Control-Allow-Methods",
 * "POST, GET, OPTIONS, DELETE"); response.setHeader("Access-Control-Max-Age",
 * "3600"); response.setHeader("Access-Control-Allow-Headers",
 * "Content-Type, Accept, X-Requested-With, remember-me");
 * response.setHeader("Access-Control-Request-Method", "*"); chain.doFilter(req,
 * res); }
 * 
 * @Override public void destroy() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void init(FilterConfig arg0) throws ServletException { //
 * TODO Auto-generated method stub
 * 
 * }
 */
