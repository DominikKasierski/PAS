package com.mycompany.firstapplication.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

@ApplicationScoped
@Named
public class IdentityUtils {
    @Inject
    private HttpServletRequest request;

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(
            "bundles/messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    public String getMyLogin() {
        return (null == request.getUserPrincipal()) ? resourceBundle.getString("guest") : request.getUserPrincipal().getName();
    }

    public boolean isLogIn() {
        return request.getUserPrincipal() != null;
    }

    public boolean isInAdminRole() {
        return request.isUserInRole("Admin");
    }

    public boolean isInSuperUserRole() {
        return request.isUserInRole("SuperUser");
    }

    public boolean isInClientRole() {
        return request.isUserInRole("Client");
    }
}
