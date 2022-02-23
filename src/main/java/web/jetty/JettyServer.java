package web.jetty;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import web.configuration.AppConfiguration;
import web.filter.CustomFilter;

public class JettyServer {
    private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";

    public void run() throws Exception {
        Server server = new Server(DEFAULT_PORT);
        server.setHandler(servletContextHandler(webApplicationContext()));
        server.start();
        server.join();
    }

    private ServletContextHandler servletContextHandler(WebApplicationContext context) {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath(CONTEXT_PATH);
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        handler.addEventListener(new ContextLoaderListener(context));

        // 增加一个Filter，正式上线时需要去掉
        handler.addFilter(CustomFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        return handler;
    }

    private WebApplicationContext webApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfiguration.class);
        return context;
    }
}