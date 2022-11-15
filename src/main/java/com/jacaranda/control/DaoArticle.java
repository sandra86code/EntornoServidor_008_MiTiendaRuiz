package com.jacaranda.control;

import java.util.ArrayList;
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
		Session session = null;
		Article article = null;
		try {
			session = ConnectionDB.getSession();
			article = (Article) session.get(Article.class, cod);
			if(article==null) {
				throw new DaoException("No existe el articulo");
			}
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}
		
		return article;
	}
	
	
	public static ArrayList<Article> getArticles() throws DaoException {
		ArrayList<Article> articles;
		try {
			Session session = ConnectionDB.getSession();
			String hql = "SELECT cod, name, description, price, category_id, image FROM article a";
			Query<Article> query = session.createNativeQuery(hql, Article.class);
			articles = (ArrayList<Article>) query.getResultList();
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception f) {
			throw new DaoException(f.getMessage());
		}
		return articles;          
	}
	
	
	public static boolean addArticle(String name, String description, double price, 
			String fileExtension, Category category) throws DaoException {
		boolean result = false;
		Session session = null; 
		try {
			session = ConnectionDB.getSession();
			Article article = new Article(name, description, price, fileExtension, category);
			article.setCategory(category);
			session.getTransaction().begin();
			session.save(article);
			session.getTransaction().commit();
			result = true;
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(ArticleException f) {
			throw new DaoException(f.getMessage());
		}catch(Exception g) {
			if(session!=null && session.isOpen()) {
				session.getTransaction().rollback();
			}
			throw new DaoException("Articulo repetido.");
		}
		return result;
	}
}
