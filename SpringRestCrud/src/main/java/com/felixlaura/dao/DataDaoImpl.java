package com.felixlaura.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.felixlaura.model.Employee;

public class DataDaoImpl implements DataDao{

	@Autowired
	SessionFactory sessionFcatory;
	
	Session session= null;
	Transaction tx= null;
	
	public boolean addEntity(Employee employee) throws Exception{
		session = sessionFcatory.openSession();
		tx = session.beginTransaction();
		session.save(employee);
		tx.commit();
		session.close();
		
		return false;
	}
	
	public Employee getEntityById(long id) throws Exception{
		session = sessionFcatory.openSession();
		Employee employee = (Employee) session.load(Employee.class, new Long(id));
		
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return employee;
		
	}

	public List<Employee> getEntityList() throws Exception {
		session = sessionFcatory.openSession();
		tx = session.beginTransaction();
		List<Employee> employeeList = session.createCriteria(Employee.class).list();
		tx.commit();
		session.close();
		return employeeList;
	}

	public boolean deleteEntity(long id) throws Exception {
		session = sessionFcatory.openSession();
		Object o = session.load(Employee.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
	
	
}
