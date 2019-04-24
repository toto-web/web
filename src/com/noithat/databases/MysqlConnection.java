package com.noithat.databases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class MysqlConnection {
	public MysqlConnection() {

	}

	public boolean insert(Object obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListAll(Class<T> clazz) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria ctr = session.createCriteria(clazz);
		List<T> result = new ArrayList<T>();
		try {
			result = (List<T>) ctr.list();
		} catch (Exception e) {
		}
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListPaging(Class<T> clazz, int start, int max) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria ctr = session.createCriteria(clazz);
		ctr.setFirstResult(start);
		ctr.setMaxResults(max);
		List<T> result = new ArrayList<T>();
		try {
			result = (List<T>) ctr.list();
		} catch (Exception e) {
		}
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> boolean delete(Class<T> clazz, Serializable id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		T t = (T) session.get(clazz, id);
		boolean rs;
		if (t != null) {
			session.delete(t);
			rs = true;
		} else
			rs = false;
		session.getTransaction().commit();
		session.close();
		return rs;
	}

	@SuppressWarnings("unchecked")
	public <T> boolean update(Class<T> clazz, T obj, Serializable id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		T t = (T) session.get(clazz, id);
		boolean check;
		if (t != null) {
			session.update(obj);
			check = true;
		} else
			check = false;
		session.getTransaction().commit();
		session.close();
		return check;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		T t = (T) session.get(clazz, id);
		session.getTransaction().commit();
		session.close();
		return t;
	}

	public void close() {
		HibernateUtil.shutdown();
	}
}
