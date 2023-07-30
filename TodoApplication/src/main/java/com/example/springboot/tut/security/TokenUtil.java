package com.example.springboot.tut.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private long TOKEN_VALIDITY = 604800L;

    private String TOKEN_SECRET = "todouser1234";
    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims = new HashMap<>();
        claims.put("sub",userDetails.getUsername());
        claims.put("created",new Date());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        }catch (Exception ex){
            return null;
        }

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims (String token){
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex){
            claims = null;
        }
        return claims;
    }
}
