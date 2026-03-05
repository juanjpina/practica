package org.practica.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

@Override
public void contextInitialized(ServletContextEvent sce){
    System.out.println("Arrancando aplicacion");
    SchemaInitializer.initialize();
    System.out.println("Apliclar lista");
}

@Override
public void contextDestroyed(ServletContextListener sce){
    System.out.println("Aplicacion detenida");
}

}
