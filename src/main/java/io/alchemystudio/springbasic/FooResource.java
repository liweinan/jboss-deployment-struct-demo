package io.alchemystudio.springbasic;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest/foo")
public class FooResource {

   @Autowired
   FooBean fooBean;

//   @GET
//   public String getFoo(@Context ServletContext context) {
//      return context.getInitParameter("foo");
//   }

   @GET
   @Path("/hello")
   public String hello() {
      return fooBean.hello();
   }
}
