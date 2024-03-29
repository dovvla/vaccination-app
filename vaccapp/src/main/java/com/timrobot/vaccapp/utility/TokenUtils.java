package com.timrobot.vaccapp.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Component
public class TokenUtils {

    @Value("spring-security")
    private String APP_NAME;

    @Value("somesecret")
    private String SECRET;

    @Value("Authorization")
    private String AUTH_HEADER;

    // type of device for jwt token creation
    private static final String AUDIENCE_WEB = "web";

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(String id, Collection<GrantedAuthority> authorities) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(id)
                 .claim("role", authorities)
                .claim("authority", authorities)
                .setAudience(AUDIENCE_WEB)
                .setIssuedAt(new Date())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    public String getToken(HttpServletRequest req) {
        String authHeader = req.getHeader(AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getRoleFromToken(String token) {
        String role;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            ArrayList<HashMap<String, String>> authorities = (ArrayList<HashMap<String, String>>) claims.get("authority");
            role = authorities.get(0).getOrDefault("authority", null);
        } catch (Exception e) {
            role = null;
        }
        return role;
    }

    public Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String identity = getIdentityFromToken(token);

        return (identity != null);
    }

    public String getIdentityFromToken(String token) {
        String identity;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            identity = claims.getSubject().split(",")[0];
        } catch (Exception e) {
            identity = null;
        }
        return identity;
    }

}
