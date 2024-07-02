package com.bezkoder.spring.datajpa.JWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication exception handler class for JSON Web Token authentication.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationEntryPoint.class);
    /**
     * Actions to take when an authorization exception is encountered.
     * @param request [HttpServletRequest] The client's request.
     * @param response [HttpServletResponse] The response to send to the client.
     * @param authException [AuthenticationException] The exception encountered while attempting authentication.
     * @throws IOException Indicates an exception was encountered attempting to send a response.
     * @throws ServletException Indicates exception was encountered processing request or response.
     */
    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
    ) throws IOException, ServletException {
        this.logger.error(authException.getMessage(), authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Failed to authorize request. Please log in again.");
    }

}
