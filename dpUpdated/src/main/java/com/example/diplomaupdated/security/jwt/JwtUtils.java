package com.example.diplomaupdated.security.jwt;

import com.example.diplomaupdated.service.impl.UserDetailsImpl;
import com.sun.xml.txw2.IllegalSignatureException;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "secret";
    private int expiration = 86400000;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            throw new IllegalSignatureException("Invalid JWT signature: {}", e);
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token: {}", e);
        } catch (ExpiredJwtException e) {
            throw new IllegalStateException("JWT token is expired: {}", e);
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported: {}", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty: {}", e);
        }
//        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

}

