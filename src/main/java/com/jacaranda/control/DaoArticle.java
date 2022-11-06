package com.jacaranda.control;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jacaranda.model.Article;


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
}
