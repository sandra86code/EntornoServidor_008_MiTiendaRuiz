package com.jacaranda.control;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;


import com.jacaranda.model.Article;
import com.jacaranda.model.ArticleException;
import com.jacaranda.control.DaoException;
import com.jacaranda.model.Category;


public class DaoArticle {
	

	public DaoArticle() {
		super();
	}
	
	public static Article getArticle(int cod) throws DaoException {
		Session session = ConnectionDB.getSession();
		Article article =(Article) session.get(Article.class, cod);
		if(article==null) {
			throw new DaoException("No existe el articulo");
		}
		return article;
	}
	
	public static ArrayList<Article> getArticles() {
		Session session = ConnectionDB.getSession();
		String hql = "SELECT cod, name, description, price, category_id FROM article a";
		Query<Article> query = session.createNativeQuery(hql, Article.class);
		ArrayList<Article> articles = (ArrayList<Article>) query.getResultList();
		return articles;          
	}
	
	public static boolean addArticle(Article article, Category category) throws DaoException {
		boolean result = false;
		Session session = ConnectionDB.getSession();
		if(article==null) {
			throw new DaoException("Articulo nulo");
		}
		try {
			article.setCategory(category);
			session.getTransaction().begin();
			session.save(article);
			session.getTransaction().commit();
			result = true;
		}catch(ArticleException e) {
			session.getTransaction().rollback();
			throw new DaoException(e.getMessage());
		}catch(Exception e1) {
			session.getTransaction().rollback();
			throw new DaoException("Artículo repetido.");
		}
		return result;
	}
}
