package io.alchemystudio.springbasic;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient43Engine;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest/client")
public class InvokeClientResource {
    @GET
    public String get() {
        return call();
    }

    private static String call() {
        HttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        HttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
        ApacheHttpClient43Engine clientHttpEngine = new ApacheHttpClient43Engine(httpClient);
        ResteasyClient client = ((ResteasyClientBuilderImpl) ResteasyClientBuilder.newBuilder())
                .httpEngine(clientHttpEngine).build();
        return client
                .target("http://localhost:8080/jboss-deployment-demo/rest/foo")
                .request().get(String.class);

    }

    public static void main(String[] args) throws Exception {
        System.out.println(call());
    }
}
