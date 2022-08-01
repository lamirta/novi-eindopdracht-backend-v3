package nl.novi.eindopdrachtv3.config;

import nl.novi.eindopdrachtv3.services.JwtService;
import nl.novi.eindopdrachtv3.utils.JwtRequestFilter;
import nl.novi.eindopdrachtv3.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
                .antMatchers(HttpMethod.PUT,"/users/{username}").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.PUT,"/users/{username}/enabled").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("TEACHER", "STUDENT")

                //API profiles
                .antMatchers(HttpMethod.POST, "/userprofiles").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.PUT,"/userprofiles/{id}/image").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.PUT,"/userprofiles/{id}/**").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.DELETE, "/userprofiles/**").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/userprofiles/{id}").hasAnyAuthority("TEACHER", "STUDENT")

                //API exams
                .antMatchers(HttpMethod.POST,"/exams").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.PUT,"/exams/{id}/**").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.DELETE,"/exams/**").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/exams/{id}").hasAnyAuthority("TEACHER", "STUDENT")

                //API wordlists
                .antMatchers(HttpMethod.POST,"/wordlists").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.DELETE,"/wordlists/**").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.PUT,"/wordlists/**").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET,"/wordlists/**").hasAnyAuthority("TEACHER", "STUDENT")

                //API images
                .antMatchers(HttpMethod.POST,"/images").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.DELETE,"/images/**").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.GET,"/images/**").hasAnyAuthority("TEACHER", "STUDENT")

                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors();
    }

}
