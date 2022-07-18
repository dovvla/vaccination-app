package com.timrobot.vaccapp.utility;


import com.timrobot.vaccapp.services.UserAuthService;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserAuthService userDetailsService;

    @Autowired
    protected final Log LOGGER = LogFactory.getLog(getClass());

    public TokenAuthenticationFilter(TokenUtils tokenHelper, UserAuthService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String identity;
        String authToken = tokenUtils.getToken(request);
        if (authToken != null) {
            identity = tokenUtils.getIdentityFromToken(authToken);
            if (identity != null) {
                UserDetails userDetails;
                userDetails = userDetailsService.loadUserByUsername(identity);

                if (tokenUtils.validateToken(authToken, userDetails)) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
                    for (String role : parseJwt(authToken)) {
                        authoritiesList.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(identity, null, authoritiesList);
                    usernamePasswordAuthenticationToken.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }


    private List<String> parseJwt(String token) {
        Claims claims = tokenUtils.getAllClaimsFromToken(token);
        List<String> roles = new ArrayList<>();
        try {
            String role = (String) ((HashMap<?, ?>) ((ArrayList<?>) claims.get("role")).get(0)).get("authority");
            roles.add(role.toUpperCase());
        }
        catch (Exception ignored) {}
        try {
            String role = (String) ((HashMap<?, ?>) ((ArrayList<?>) claims.get("role")).get(1)).get("authority");
            roles.add(role.toUpperCase());
        }
        catch (Exception ignored) {}
        try {
            String role = (String) ((HashMap<?, ?>) ((ArrayList<?>) claims.get("role")).get(2)).get("authority");
            roles.add(role.toUpperCase());
        }
        catch (Exception ignored) {}
        return roles;
    }
}
