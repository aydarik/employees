package ru.aydarik.server;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import ru.aydarik.shared.EmployeeBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	public static Long saveEmployee(EmployeeBean employee) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return employee.getId();
	}

	public static EmployeeBean getEmployee(Long id) throws SQLException {
		Session session = null;
		EmployeeBean employee = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			employee = (EmployeeBean)session.get(EmployeeBean.class, id);
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return employee;
	}

	public static void updateEmployee(EmployeeBean employee) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<EmployeeBean> getEmployees(int start, int size) throws SQLException {
		Session session = null;
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			list = session.createCriteria(EmployeeBean.class).addOrder(Order.desc("id")).setFirstResult(start)
					.setMaxResults(size).list();
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public static Number getSize() throws SQLException {
		Session session = null;
		Number count = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			count = (Number)session.createCriteria(EmployeeBean.class).setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return count;
	}


	public static void deleteEmployee(Long id) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(session.get(EmployeeBean.class, id));
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
}
