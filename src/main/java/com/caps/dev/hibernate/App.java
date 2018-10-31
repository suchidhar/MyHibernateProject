package com.caps.dev.hibernate;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.caps.dev.beans.CPU;
import com.caps.dev.beans.Desktop;
import com.caps.dev.beans.Movie;
import com.caps.dev.model.JPAUtils;

public class App 
{
	public static void main( String[] args )
	{

		Desktop d=new Desktop();
		d.setId(01);
		d.setColour("Silver");
		d.setModel("Samsung");
		
		CPU c=new CPU();
		c.setCid(101);
		c.setModel("Samsung");
		c.setProcessor("Intel Core i3");
		
		d.setCpu(c);
		
		EntityManager em = JPAUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		em.persist(d);
		tx.commit();
		em.close();
		
	}

	public static void save() {
		System.out.println("Enter Movie Details");
		System.out.println("-------------------");
		Movie movie = new Movie();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter movie id: ");
		movie.setMovieId(Integer.parseInt(in.nextLine()));

		System.out.println("Enter movie name: ");
		movie.setMovieName(in.nextLine());

		System.out.println("Enter movie ratings: ");
		movie.setRatings(Double.parseDouble(in.nextLine()));

		System.out.println("Enter movie summary: ");
		movie.setSummmary(in.nextLine());
		System.out.println("Movie Saved");
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(movie);
		tx.commit();
		em.close();
		emf.close();
		in.close();
	}

	public static void getMovieById() {
		System.out.println("Enter a movie id: ");
		Scanner sc = new Scanner(System.in);
		int id = Integer.parseInt(sc.nextLine());
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//		Movie m = em.find(Movie.class, id);
		Movie m=em.getReference(Movie.class, id);
		System.out.println(m);
		tx.commit();
		em.close();
		sc.close();
	}

	public static void remove() {
		System.out.println("Enter a movie id to delete : ");
		Scanner in=new Scanner(System.in);
		int id=Integer.parseInt(in.nextLine());
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Movie m=em.find(Movie.class, id);

		em.remove(m);
		System.out.println("Removed");
		tx.commit();
		em.close();
		in.close();
	}

	public static void updateRatings() {
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the id to update ratings");
		int id = Integer.parseInt(in.nextLine());
		System.out.println("Enter ratings");
		Double ratings=Double.parseDouble(in.nextLine());

		EntityManagerFactory emf=JPAUtils.getEMF();
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();

		tx.begin();
		Movie m=em.find(Movie.class, id);
		m.setRatings(ratings);
		System.out.println("Updated successfully");
		tx.commit();
		em.close();
		in.close();

	}
}