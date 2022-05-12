package nl.novi.eindopdrachtv3.config;

import nl.novi.eindopdrachtv3.services.ExamService;
import nl.novi.eindopdrachtv3.utils.JwtRequestFilter;
import nl.novi.eindopdrachtv3.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtRequestFilter jwtRequestFilter;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http     // deze allemaal nog even goed zetten, dubbel check!!
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/users/{username}/enabled").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/userprofiles").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.POST, "/users/{username}/authorities").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/users/**").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.POST,"/wordlists/**").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.PUT,"/wordlists/**").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.DELETE,"/wordlists/**").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.GET,"/images").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.GET,"/exams").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.DELETE,"/exams/**").hasAnyRole("ADMIN", "DOCENT")
//                .antMatchers(HttpMethod.GET,"/images").hasAnyRole("ADMIN", "DOCENT")
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // om cookies te voorkomen
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}

////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .inMemoryAuthentication()
////                .withUser("jan").password("wachtwoord").roles("USER")
////                .and()
////                .withUser("kees").password("wachtwoordddd").roles("ADMIN");
////    } //password encoder / je kan ook op de plek van wachtwoord nu > {bcrypt}$+incrypted-password
//
//    Deze hierrboven wil ik niet, dit is voor het testen even, weghalen voor inleveren!!
