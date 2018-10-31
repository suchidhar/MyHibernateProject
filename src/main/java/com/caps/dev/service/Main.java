package com.caps.dev.service;

import java.util.Scanner;

import com.caps.dev.beans.Person;
import com.caps.dev.dao.PersonDB;
import com.caps.dev.hibernate.PersonHibernateImpl;
import com.caps.dev.jdbc.PersonJDBCImpl;

public class Main {
	
	public static void main(String[] args) {
		PersonDB db=null;
		Person p;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter LOGIN / CREATE / SEARCH / DELETE / UPDATE that you want to perform ");
		System.out.println("----------------------------------------------------------------------");
		String input=in.nextLine();
		System.out.println("Enter JDBC or HIBERNATE ");
		String database=in.nextLine();
		
		if(database.equalsIgnoreCase("JDBC"))
			db=new PersonJDBCImpl();
		else if(database.equalsIgnoreCase("HIBERNATE"))
			db=new PersonHibernateImpl();
		else 
			System.out.println("Wrong choice!!!");
		
		if(input.equalsIgnoreCase("LOGIN")) {
			System.out.println("Enter id and password");
			int pid=Integer.parseInt(in.nextLine());
			String password=in.nextLine();
			db.login(pid, password);
		}
		else if(input.equalsIgnoreCase("CREATE")) {
			p=new Person();
			System.out.println("***Enter person details***");
			System.out.println("---------------------------");
			System.out.println("Enter id : ");
			p.setId(Integer.parseInt(in.nextLine()));
			System.out.println("Enter name : ");
			p.setName(in.nextLine());
			System.out.println("Enter password : ");
			p.setPassword(in.nextLine());
			System.out.println("Enter age : ");
			p.setAge(Integer.parseInt(in.nextLine()));
			System.out.println("Enter email address : ");
			p.setEmail(in.nextLine());
			System.out.println("Enter address");
			p.setAddress(in.nextLine());
			
			db.createProfile(p);
		}
		
		else if(input.equalsIgnoreCase("SEARCH")) {
			System.out.println("Enter the id that you wanna search: ");
			int pid=Integer.parseInt(in.nextLine());
			
			 p=db.search(pid);
			 System.out.println(p);
			 
		}
		else if(input.equalsIgnoreCase("DELETE")) {
			System.out.println("Enter id that you want to delete : ");
			int pid=Integer.parseInt(in.nextLine());
			System.out.println("Enter password for that id : ");
			String password=in.nextLine();
			
			db.delete(pid, password);
		}
		else if(input.equalsIgnoreCase("UPDATE")) {
			System.out.println("Enter id that you want to update : ");
			int pid=Integer.parseInt(in.nextLine());
			System.out.println("Enter password for that id : ");
			String password=in.nextLine();
			
			db.update(pid, password);
		
		}
		
	}

}
