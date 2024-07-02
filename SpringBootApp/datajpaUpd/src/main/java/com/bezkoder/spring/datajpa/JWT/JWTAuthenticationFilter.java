package com.bezkoder.spring.datajpa.JWT;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Filter class that applies JSON Web Token authentication.
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    /** The secret signing key to encode or decode authorization tokens. */
    private final String secret;
    /**
     * JWT builder class.
     * @see JWT
     */
    private final JWT jwt;

    /**
     * Spring managed constructor for instantiating the custom authentication filter.
     * @param secret [String] The secret signing key obtained from the application properties.
     * @param jwt [JWT] JWT builder class.
     */
    @Autowired
    public JWTAuthenticationFilter(@Value("${jwt.secret}") String secret, JWT jwt) {
        this.secret = secret;
        this.jwt = jwt;
    }

    /**
     * Filter that most requests must pass in order to be authenticated. This filter ensures a Bearer token is present
     * in the Authorization header of the request.
     * @param request [HttpServletRequest] The client request.
     * @param response [HttpServletResponse] Response to send to client.
     * @param filterChain [FilterChain] The filter chain for requests.
     * @throws ServletException Indicates exception was encountered processing request or response.
     * @throws IOException Indicates an exception was encountered attempting to add the filter to the filter chain.
     */
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = this.getBearerToken(request);
        this.logger.info("Authorization Token: " + token);
        //if (token.isEmpty()) throw new JwtException("No token was provided.");
        if (!token.isEmpty()) {
//            if (this.jwt.expired(token)) throw new ExpiredJwtException(
//                    Jwts.parser()
//                            .setSigningKey(this.secret.getBytes())
//                            .parseClaimsJws(token)
//                            .getHeader(),
//                    Jwts.parser()
//                            .setSigningKey(this.secret.getBytes())
//                            .parseClaimsJws(token)
//                            .getBody(),
//                    "The current JSON Web Token is expired."
//            );
            SecurityContextHolder.getContext().setAuthentication(
                    new JWTAuthentication(token, false, this.secret)
            );
        }
        filterChain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {
        // Attempt to locate & return Authorization Token.
        try {
            return request.getHeader("Authorization").replace("BearerToken", ""). replace(" ", "");
        } catch (Exception e) {
            this.logger.error("Failed to obtain Authorization Token.", e);
            return "";
        }
    }
}
