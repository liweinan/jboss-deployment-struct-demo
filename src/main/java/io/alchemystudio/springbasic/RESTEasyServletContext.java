package io.alchemystudio.springbasic;

import org.jboss.resteasy.core.ResteasyContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

// https://itestfirst.wordpress.com/2015/08/13/unable-to-find-contextual-data-of-type-javax-servlet-servletcontext/
//@Component
//@Provider
public class RESTEasyServletContext implements ContainerRequestFilter {
    @Autowired
    ServletContext sc;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        ResteasyContext.getContextDataMap().put(ServletContext.class, sc);
    }

}
