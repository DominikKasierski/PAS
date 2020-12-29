package com.mycompany.firstapplication.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
@Named
public class IdentityUtils {
    @Inject
    private HttpServletRequest request;

    public String getMyLogin() {
        return (null == request.getUserPrincipal()) ? "" : request.getUserPrincipal().getName();
    }
}
