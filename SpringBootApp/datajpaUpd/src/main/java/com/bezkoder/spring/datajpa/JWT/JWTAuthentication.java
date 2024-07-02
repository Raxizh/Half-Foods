package com.bezkoder.spring.datajpa.JWT;

import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Authentication class for JSON Web Tokens.
 */
public class JWTAuthentication implements Authentication {

    /** The JSON Web Token. */
    private final String jwt;
    /** Authentication flag. */
    private boolean authenticated;
    /** The secret signing key. */
    private final String signingKey;

    /**
     * Instantiates a new JWTAuthentication object.
     * @param jwt [String] The JSON Web Token.
     * @param authenticated [boolean] Authentication flag.
     * @param signingKey [String] The secret signing key.
     */
    public JWTAuthentication(String jwt, boolean authenticated, String signingKey) {
        this.jwt = jwt;
        this.authenticated = authenticated;
        this.signingKey = signingKey;
    }

    /** Unused */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return List.of();}

    /** Unused */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Returns the authentication token and status in JSON.
     * @return [JSONObject] Authentication details.
     */
    @Override
    public Object getDetails() {
        JSONObject details = new JSONObject();
        details.put("Token", this.jwt);
        details.put("Authenticated", this.authenticated);
        return details;
    }

    /**
     * Obtains the user's ID from the authentication token.
     * @return [Object] The user's ID.
     */
    @Override
    public Object getPrincipal() {
        return Jwts.parser()
                .setSigningKey(this.signingKey.getBytes())
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject(); // the subject is the user's id
    }

    /**
     * Reports authentication status.
     * @return [boolean] Response 'true' indicates authenticated and 'false' not authenticated.
     */
    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    /**
     * Sets the authenticated flag.
     * @param isAuthenticated [boolean] Argument 'true' indicates authenticated and 'false' indicates not authenticated.
     * @throws IllegalArgumentException Indicates an illegal argument was provided.
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    /**
     * Alternate method for obtaining user ID from the authentication token that returns type String.
     * @return [String] The user's ID.
     */
    @Override
    public String getName() {return this.getPrincipal().toString();}

}
