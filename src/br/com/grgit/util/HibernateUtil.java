package br.com.grgit.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.jboss.logging.Logger;

public class HibernateUtil {

	private static EntityManagerFactory emf = null;

	private HibernateUtil() {
	}
	
    public static EntityManagerFactory getEmf() {  
        if (emf == null){  
            synchronized (HibernateUtil.class) {  
                if (emf == null)  
                    try {  
                        emf = Persistence.createEntityManagerFactory("cobranca");  
                    } catch (RuntimeException ex) {  
                        Logger.getLogger(HibernateUtil.class).fatal("Não foi possível carregar a unidade de persistencia", ex);  
                        throw ex;  
                    }  
            }  
        }  
        return emf;  
    }  
  
    public static EntityManager create() {  
        try {  
            return getEmf().createEntityManager();  
        } catch (RuntimeException ex) {  
            Logger.getLogger(HibernateUtil.class).error("Falha ao criar EntityManager", ex);  
            throw ex;  
        }  
    }
  
    public static Session createSession() {  
        return ((HibernateEntityManager)create()).getSession();  
    }  
	
}