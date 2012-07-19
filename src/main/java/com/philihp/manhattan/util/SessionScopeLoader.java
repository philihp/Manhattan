package com.philihp.manhattan.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionScopeLoader
 *
 */
public class SessionScopeLoader implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionScopeLoader() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
    	String offlineId = (String)se.getSession().getServletContext().getAttribute("offline_id");
    	System.out.println("Session Created, OfflineID = "+offlineId);

		if (offlineId != null) {
			EntityManagerFactory emf = (EntityManagerFactory)se.getSession().getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();
			//se.getSession().setAttribute("user", ...);
			em.close();
		}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    }
	
}
