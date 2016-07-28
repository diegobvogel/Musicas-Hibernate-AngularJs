package br.com.grgit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.grgit.entities.Musica;
import br.com.grgit.util.HibernateUtil;

public class MusicaDAO {
	
	public static Musica select(int codigo) {
		EntityManager em = null;
		Musica obj = null;
		try {
			em = HibernateUtil.create();
			Query query = em.createQuery("from Musica where codigo = :codigo");
			query.setParameter("codigo", codigo);
			obj = (Musica) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Musica> listAll() {
		EntityManager em = null;
		List<Musica> list = null;
		try {
			em = HibernateUtil.create();
			Query query = em.createQuery("from Musica order by codigo");
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return list;
	}
	
	public static String gravar(Musica obj) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = HibernateUtil.create();
			tx = em.getTransaction();
			tx.begin();
			em.persist(obj);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return "false";
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return "true";
	}
	
	public static String editar(Musica obj) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = HibernateUtil.create();
			tx = em.getTransaction();
			tx.begin();
			em.merge(obj);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return "false";
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return "true";
	}
	
	public static boolean delete(int codigo) {
		EntityManager em = null;
		EntityTransaction tx = null;
		boolean retorno = false;
		try {
			em = HibernateUtil.create();
			String hql = "from Musica where codigo = :codigo";
			
			Query query = em.createQuery(hql);
			query.setParameter("codigo", codigo);
			
			Musica obj = (Musica) query.getSingleResult();
			
			tx = em.getTransaction();
			tx.begin();
			em.remove(obj);
			tx.commit();
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			retorno = false;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return retorno;
	}

}
