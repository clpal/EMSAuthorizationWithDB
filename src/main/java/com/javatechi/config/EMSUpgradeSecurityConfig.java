package com.javatechi.config;

import com.javatechi.service.EmployeeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EMSUpgradeSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){

    /*    UserDetails employee=  User.withUsername("chhote").password(passwordEncoder().encode("pwd1")).roles("EMPLOYEE").build();
        UserDetails hr=  User.withUsername("anju").password(passwordEncoder().encode("pwd2")).roles("HR").build();
        UserDetails admin=  User.withUsername("adi").password(passwordEncoder().encode("pwd3")).roles("HR","MANAGER").build();
       return new InMemoryUserDetailsManager(employee,hr,admin);*/
        return  new EmployeeUserDetailsService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     /* return   http.authorizeRequests().antMatchers("/nonsecure").permitAll().
                and()
                .authorizeRequests().antMatchers("/welcome","/text").authenticated().
                and().httpBasic()
                .and().build(); */
      return   http.csrf().disable().authorizeRequests().antMatchers("/employees/welcome","/employees/create").permitAll().
                and()
                .authorizeRequests().antMatchers("/employees/**").authenticated().
                and().httpBasic()
                .and().build();
       /* return   http.authorizeRequests().antMatchers("/employees/welcome").permitAll().
                and()
                .authorizeRequests().antMatchers("/employees/**").authenticated().
                and().httpBasic()
                .and().build();*/
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
