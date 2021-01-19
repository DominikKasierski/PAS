package com.mycompany.firstapplication.RestFilters;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class RequestFilter implements ClientRequestFilter {

    @Override public void filter(ClientRequestContext clientRequestContext) throws IOException {
        System.out.println("Metoda:" + clientRequestContext.getMethod());
            if (clientRequestContext.getMethod().equals("PUT")) {
                System.out.println("PUT:" + clientRequestContext.getMethod());
                clientRequestContext.abortWith(Response.status(400).build());
            }
    }
}
