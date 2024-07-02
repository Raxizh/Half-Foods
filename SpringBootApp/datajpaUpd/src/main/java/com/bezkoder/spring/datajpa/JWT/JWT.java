package com.bezkoder.spring.datajpa.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * Builder class for JSON Web Tokens.
 */
@Component
public class JWT {

    /** The secret signing key to encode or decode authorization tokens. */
    private final String secret;

    /**
     * Spring managed constructor for instantiating a new JWT builder class.
     * @param secret The secret signing key to encode or decode authorization tokens obtained from the application
     * properties file.
     */
    public JWT(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    /**
     * Builds a JSON Web Token signed with the secret signing key. The subject of the token is the user's ID, followed
     * by the user's password. The two values are separated by a semicolon (;).
     * @param userId [UUID] The current user's ID.
     * @return [String] The result JSON Web Token.
     */
    public String build(UUID userId) {
        return Jwts.builder()
                .setExpiration(Date.from(Instant.now().plusSeconds(7200)))
                .setSubject(userId.toString())
                .setIssuer("MessageForum")
                .signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
                .compact();
    }

    /**
     * Obtains the ID of the user associated with the specified JWT.
     * @param token [String] A JSON Web Token.
     * @return [UUID] The user's ID.
     */
    public UUID getUserId(String token) throws ExpiredJwtException {
        if (!this.expired(token))
            return UUID.fromString(
                    Jwts.parser()
                            .setSigningKey(this.secret.getBytes())
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject()
            );
        throw new ExpiredJwtException(
                Jwts.parser()
                        .setSigningKey(this.secret.getBytes())
                        .parseClaimsJws(token)
                        .getHeader(),
                Jwts.parser()
                        .setSigningKey(this.secret.getBytes())
                        .parseClaimsJws(token)
                        .getBody(),
                "The current JSON Web Token is expired."
        );
    }

    /**
     * Reports if a JWT is or isn't expired.
     * @param token [String] A JSON Web Token.
     * @return [boolean] Response 'true' indicates expired and 'false' indicates not expired.
     */
    public boolean expired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(this.secret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

}
