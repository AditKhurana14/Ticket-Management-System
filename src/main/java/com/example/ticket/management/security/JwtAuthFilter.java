package com.example.ticket.management.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTUtil jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(JWTUtil jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try{
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String username;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("No Authorization header or it doesn't start with Bearer");
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            username = jwtService.extractUserName(jwt);
            System.out.println("Extracted username: " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                boolean valid = jwtService.validateToken(username,userDetails,jwt);
                System.out.println("Is token valid: " + valid);

                if (valid) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            System.out.println("Auth: " + SecurityContextHolder.getContext().getAuthentication());
            filterChain.doFilter(request, response);
        }catch (JwtException | UsernameNotFoundException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("Error"+" "+ ex.getMessage());

        }


    }
}
 