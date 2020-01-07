package pl.solutions.software.sokolik.bartosz.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenService {

    private final SecurityProperties securityProperties;

    public JwtTokenService(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + securityProperties.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS256, securityProperties.getSecret())
                .compact();
    }

    public Optional<String> getUsernameFromToken(String token) {
        return Optional.ofNullable(Jwts.parser()
                .setSigningKey(securityProperties.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

}
