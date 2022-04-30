package nl.novi.eindopdrachtv3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


//     @Autowired
//    private JwtRequestFilter jwtRequestFilter;


//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("jan").password("wachtwoord").roles("USER")
                .and()
                .withUser("kees").password("wachtwoordddd").roles("ADMIN");
    } //password encoder / je kan ook op de plek van wachtwoord nu > {bcrypt}$+incrypted-password

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http); // hier gaan we er al vanuit dat gebruiker auth is, dus ingelogd.
        // en hier kan je dus rollen toewijzen
        // daar gebruik je antMatchers() voor, zie voorbeeld.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
