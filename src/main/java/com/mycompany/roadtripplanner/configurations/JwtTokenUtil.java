package com.mycompany.roadtripplanner.configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil implements Serializable {

    //On defini le temp de validité du token ici 5h
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    //On lui donne la clé secrete
    @Value("secretpassword")
    private String secret;

    /**
     * Reclame un jeton d'accès
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims =getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Permet de parser le jeton et de verifier sa signature
     * @param token
     * @return
     */
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
    }

    /**
     * methode qui recupere la date d'expiration du token
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims ::getExpiration);
    }

    /**
     * Méthode qui vérifie si le token est expiré
     * @param token
     * @return
     */
    public Boolean isTokenIsExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    /**
     * Permet de récuperer l'email utilisateur d'un token
     * @param token
     * @return
     */
    public String  getEmailFromToken (String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * On génère le token
     * @param claims
     * @param subject
     * @return
     */
    private String doGenerateToken(Map<String , Object>claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +JWT_TOKEN_VALIDITY+1000))
                .signWith(SignatureAlgorithm.RS512,secret)
                .compact();
    }

    /**
     * Permet de récupérer le token qui est généré
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Méthode qui  permet de valider le token
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenIsExpired(token));
    }
}
