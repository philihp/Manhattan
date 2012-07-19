package com.philihp.manhattan.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.philihp.manhattan.jpa.Config;

/**
 * Application Lifecycle Listener implementation class ApplicationScopeLoader
 * 
 */
public class ApplicationScopeLoader implements ServletContextListener {

	public ApplicationScopeLoader() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		EntityManager em = null;
		try {
			EntityManagerFactory emf = (EntityManagerFactory) sce
					.getServletContext().getAttribute("emf");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			for(Config config : em.createNamedQuery("getAllConfigs", Config.class).getResultList()) {
				String name = config.getName();
				String value = config.getValue();
				sce.getServletContext().setAttribute(name, value);
				System.out.println("Loaded Config: ["+ name + "] => ["+value+"]");
			}
			
			em.getTransaction().commit();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
