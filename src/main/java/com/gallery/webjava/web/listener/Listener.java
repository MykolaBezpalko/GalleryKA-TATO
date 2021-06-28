package com.gallery.webjava.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Listener.class);
    public void contextDestroyed(ServletContextEvent event) {
        log.info("Servlet context destruction starts");
        log.info("Servlet context destruction finished");
    }
}
