package com.jacaranda.control;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jacaranda.model.Article;
import com.jacaranda.model.ArticleException;
import com.jacaranda.model.Category;


public class DaoArticle {
	

	public DaoArticle() {
		super();
	}
	
	public static Article getArticle(int cod) throws DaoException {
		Article article = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			Session session = connection.getSession();
			article = session.get(Article.class, cod);
			session.close();
			if(article==null) {
				throw new DaoException("No existe el articulo");
			}
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}
		return article;
	}
	
	
	public static List<Article> getArticles() throws DaoException {
		ArrayList<Article> articles;
		Session session = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			String hql = "SELECT cod, name, description, price, quantity, category_id, image FROM article a";
			Query<Article> query = session.createNativeQuery(hql, Article.class);
			articles = (ArrayList<Article>) query.getResultList();
			session.close();
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception f) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(f.getMessage());
		}
		return articles;          
	}
	
	
	public static boolean addArticle(String name, String description, double price, 
			int quantity, Blob image, Category category) throws DaoException {
		boolean result = false;
		Session session = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			Article article = new Article(name, description, price, quantity, image, category);
			article.setCategory(category);
			session.getTransaction().begin();
			session.save(article);
			session.getTransaction().commit();
			session.close();
			result = true;
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(ArticleException f) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(f.getMessage());
		}catch(Exception g) {
			if(session!=null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
			throw new DaoException("Articulo repetido.");
		}
		return result;
	}
}
