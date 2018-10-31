package com.caps.dev.hibernate;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.caps.dev.beans.Person;
import com.caps.dev.dao.PersonDB;

public class PersonHibernateImpl implements PersonDB {

	public Person login(int pid, String password) {
		Person p=new Person();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		TypedQuery<Person> query=em.createQuery("FROM Person p where p.id = ?1 and p.password = ?2 ",Person.class);
		query.setParameter(1, pid);
		query.setParameter(2, password);
		List<Person> list=query.getResultList();

		for (Person s : list) {
			p.setId(s.getId());
			p.setName(s.getName());
			p.setPassword(s.getPassword());
			p.setAge(s.getAge());
			p.setEmail(s.getEmail());
			p.setAddress(s.getAddress());
			System.out.println(p);
		}
		return p;
	}

	public boolean createProfile(Person p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{ 
			tx.begin();
			em.persist(p);
			tx.commit();
			System.out.println(p);
			System.out.println("Successful");
			return true;
		} catch (Exception e) {
			System.out.println("unsuccessful");
			return false;
		}

	}

	public Person search(int pid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		//		Person p=new Person();

		tx.begin();

		Person p=em.find(Person.class, pid);

		/*		TypedQuery<Person> query=em.createQuery("FROM Person p where p.id = ?1 ",Person.class);
		query.setParameter(1, pid);
		List<Person> list=query.getResultList();

		for (Person s : list) {
			System.out.println("Inside loop");
			p.setId(s.getId());
			p.setName(s.getName());
			p.setPassword(s.getPassword());
			p.setAge(s.getAge());
			p.setEmail(s.getEmail());
			p.setAddress(s.getAddress());
		}*/
		return p;		
	}

	public boolean delete(int pid, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {	
			tx.begin();

			Person p=em.find(Person.class, pid);
			em.remove(p);

			/*			TypedQuery<Person> query=em.createQuery("from Person p order by p.id",Person.class);

			List<Person> p =query.getResultList();

			int updated = query.executeUpdate ();
			System.out.println("Deleted succesfully");

			for (Person person : p) {
				person.getName();
			}
			 */
			tx.commit();
			return true;
		} catch (Exception e) {
			System.out.println("Unsuccessful");
			return false;
		} 

	}

	public boolean update(int id, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Query query = em.createQuery( "UPDATE Person p SET p.password= ?1 WHERE p.id= ?2 and p.password=?3");
			Scanner in=new Scanner(System.in);
			System.out.println("Enter new password : ");
			String pass=in.nextLine();

			Person p = em.find(Person.class, id);
			p.setPassword(pass);

			/*			query.setParameter(1, Integer.parseInt(in.nextLine()));
			System.out.println("after setting first parameter");
			query.setParameter(2, id);
			query.setParameter(3, password);
			int updateCount = query.executeUpdate();
			System.out.println(updateCount);
			 */

			tx.commit();
			em.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
