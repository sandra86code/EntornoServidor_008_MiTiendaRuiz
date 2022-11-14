package com.jacaranda.control;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;


import com.jacaranda.model.Article;
import com.jacaranda.model.ArticleException;
import com.jacaranda.control.DaoException;
import com.jacaranda.model.Category;


public class DaoArticle {
	

	public DaoArticle() {
		super();
	}
	
	public static Article getArticle(int cod) throws DaoException, HibernateException {
		Session session = ConnectionDB.getSession();
		Article article =(Article) session.get(Article.class, cod);
		if(article==null) {
			throw new DaoException("No existe el articulo");
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
		}catch(HibernateException e) {
			throw new DaoException("Error en la conexión con la base de datos.");
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
		return articles;          
	}
	
	public static boolean addArticle(Article article, Category category) throws DaoException {
		boolean result = false;
		Session session = null; 
		try {
			session = ConnectionDB.getSession();
			if(article==null) {
				throw new DaoException("Articulo nulo");
			}
			article.setCategory(category);
			session.getTransaction().begin();
			session.save(article);
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException e) {
			throw new DaoException(e.getMessage());
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
