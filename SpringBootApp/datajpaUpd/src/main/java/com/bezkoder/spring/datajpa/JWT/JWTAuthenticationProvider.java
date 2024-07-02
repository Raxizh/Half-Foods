package com.bezkoder.spring.datajpa.JWT;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Authentication provider class for JSON Web Tokens.
 */
@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    /**
     * Drives authentication for most client requests.
     * @param authentication [Authentication] Authentication object that should be of type JWTAuthentication.
     * @see JWTAuthentication
     * @return [Authentication] The processed Authentication object.
     * @throws AuthenticationException Indicates exception was encountered while attempting to authenticate the
     * request.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (!authentication.getPrincipal().toString().isEmpty()) {
                // add check for user account to be unlocked
                authentication.setAuthenticated(true);
            }
            return authentication;
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage()) {};
        }
    }

    /**
     * Asserts the provided Authentication object is of type JWTAuthentication.
     * @param authentication [Class] The authentication object.
     * @return [boolean] Response 'true' indicates match and 'false' mismatch.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthentication.class.equals(authentication);
    }
}
