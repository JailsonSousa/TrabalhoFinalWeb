package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufc.br.security.UserDetailsServiceSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceSecurity userDetailsServiceSecurity;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/cliente/formulario/cadastrarCliente").permitAll()
		.antMatchers("/cliente/cadastrarCliente").permitAll()
		.antMatchers("/cliente/cadastroClienteSucesso").permitAll()
		.antMatchers("/").permitAll()
		
		.antMatchers("/cliente/fazerPedido").hasAuthority("ROLE_USER")
		.antMatchers("/cliente/fecharPedido").hasAnyAuthority("ROLE_USER")
		.antMatchers("/cliente/adicionarPrato").hasAnyAuthority("ROLE_USER")
		.antMatchers("/cliente/cliente/listaPratosPedido").hasAnyAuthority("ROLE_USER")
		.antMatchers("/cliente/removerPratoDoPedido").hasAnyAuthority("ROLE_USER")
		
		
		.antMatchers("/gerenciar/cadastrarPrato").hasAuthority("ROLE_GERENTE")
		.antMatchers("/gerenciar/listarPratos").hasAnyAuthority("ROLE_GERENTE")
		.antMatchers("/gerenciar/excluirPrato").hasAnyAuthority("ROLE_GERENTE")
		.anyRequest().authenticated()
		
		.and()
		.formLogin().permitAll()
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceSecurity)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/img/**","/js/**","/images/**");
	}

}
