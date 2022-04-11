package web;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import web.configuration.AppConfiguration;
import web.jetty.JettyServer;

public class ApplicationBooter {

    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfiguration.class);

        JettyServer.startJettyServer(8080, context);
    }

}
