package nl.novi.eindopdrachtv3.auth;

import nl.novi.eindopdrachtv3.JwtService;
import nl.novi.eindopdrachtv3.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

//    uit les 15 ??
//    public JwtRequestFilter(JwtService jwtService, CustomUserDetailsService cudService) {
//        this.jwtService = jwtService;
//        this.userDetailsService = cudService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
