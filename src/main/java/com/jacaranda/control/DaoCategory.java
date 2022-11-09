package com.jacaranda.control;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.jacaranda.model.Category;

public class DaoCategory {

	public DaoCategory() {
		super();
		
	}
	
	
	public static ArrayList<Category> getCategories() {
		Session session = ConnectionDB.getSession();
		String hql = "SELECT cod, name, description FROM category c;";
		Query<Category> query = session.createNativeQuery(hql, Category.class);
		ArrayList<Category> categories = (ArrayList<Category>) query.getResultList();
		return categories;          
	}
	
}
