package web;

import web.jetty.JettyServer;

public class ApplicationBooter {

    public static void main(String[] args) throws Exception {
        new JettyServer().run();
    }

}
