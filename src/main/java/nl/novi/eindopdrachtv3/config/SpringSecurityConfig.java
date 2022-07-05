package nl.novi.eindopdrachtv3.config;

import nl.novi.eindopdrachtv3.services.JwtService;
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

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

// Waarom werken de antmatchers niet :')
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // avoid cookies
                .and().authorizeRequests()

                //API auth
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()

                //API getAll
                .antMatchers(HttpMethod.GET,"/users").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/userprofiles").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/exams").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/images").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/wordlists").hasAnyAuthority("TEACHER", "STUDENT")

                //API users
                .antMatchers(HttpMethod.POST,"/users").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("TEACHER")
//                .antMatchers(HttpMethod.PUT,"/users/{username}").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.PUT,"/users/{username}/enabled").hasRole("TEACHER")
//                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("TEACHER")
//                .antMatchers(HttpMethod.GET,"/users/**").hasAnyRole("TEACHER", "STUDENT") // check if only with own Username..
//
//                //API profiles
                .antMatchers(HttpMethod.POST, "/userprofiles").hasAnyAuthority("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.PUT,"/userprofiles/{id}").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.PUT,"/userprofiles/{id}/**").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.DELETE, "/userprofiles/**").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET,"/userprofiles/{id}").hasAnyAuthority("TEACHER", "STUDENT") // check if only with own ID..
//
//
//                //API exams
                .antMatchers(HttpMethod.POST,"/exams").hasAnyAuthority("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.PUT,"/exams/{id}/{userProfileId}").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.PUT,"/exams/{id}/{wordlistTitle}").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.DELETE,"/exams/**").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET,"/exams/{id}").hasAnyAuthority("TEACHER", "STUDENT") // check if only with own ID..
//
//                //API wordlists
                .antMatchers(HttpMethod.POST,"/wordlists").hasAuthority("TEACHER")
//                .antMatchers(HttpMethod.DELETE,"/wordlists/**").hasRole("TEACHER")
//                .antMatchers(HttpMethod.PUT,"/wordlists/**").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET,"/wordlists/**").hasAnyAuthority("TEACHER", "STUDENT")
//
//                //API images
//                .antMatchers(HttpMethod.POST,"/images").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.DELETE,"/images/**").hasAnyRole("TEACHER", "STUDENT")
//                .antMatchers(HttpMethod.GET,"/images/**").hasAnyRole("TEACHER", "STUDENT") // check if only with own ID..

//                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors();
    }

}
