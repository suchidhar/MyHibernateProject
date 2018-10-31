package com.caps.dev.dao;

import com.caps.dev.beans.Person;

public interface PersonDB {
	
	Person login(int pid, String password);
	boolean createProfile(Person p);
	Person search(int pid);
	boolean delete(int pid, String password);
	boolean update(int id, String password);
	
}
