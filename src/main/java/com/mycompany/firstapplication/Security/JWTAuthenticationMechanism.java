package com.mycompany.firstapplication.Security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "BEARER ";


    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                HttpMessageContext httpMessageContext) {

        if (!httpServletRequest.getRequestURL().toString().endsWith("/auth")) {
            String authHeader = httpServletRequest.getHeader(AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith(BEARER)) {
                return httpMessageContext.responseUnauthorized();
            }

            String tokenToValidate = authHeader.substring(BEARER.length() - 1);
        }
        return httpMessageContext.doNothing();
    }
}
