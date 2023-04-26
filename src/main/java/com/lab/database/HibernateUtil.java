package com.lab.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
    	try {
    		return sessionFactory = new Configuration().configure().buildSessionFactory();	
    	} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
    }
}
