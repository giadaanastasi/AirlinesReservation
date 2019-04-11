/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author giada
 */
@WebListener
public class DbAccessListener implements ServletContextListener {

    // Prepare the EntityManagerFactory & Enhance:
    public void contextInitialized(ServletContextEvent sce) {
        //com.objectdb.Enhancer.enhance("servlet.*");
        //EntityManagerFactory emf =Persistence.createEntityManagerFactory("my-persistence-unit");
        //sce.getServletContext().setAttribute("emf", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //EntityManagerFactory emf =
        //(EntityManagerFactory)sce.getServletContext().getAttribute("emf");
        //emf.close();
    }
}
