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
			Article article = null;
			if(image!=null) {
				article = new Article(name, description, price, quantity, image, category);
			}else {
				article = new Article(name, description, price, quantity, category);
			}
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
	
	public static void deleteArticle(int cod) throws DaoException {
		Session session = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			Article article = getArticle(cod);
			session.getTransaction().begin();
			session.delete(article);
			session.getTransaction().commit();
			session.close();
		}catch(DaoException e) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(e.getMessage());
		}catch(Exception g) {
			if(session!=null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
			throw new DaoException("Articulo repetido.");
		}
	}
	
	public static Article modifyArticle(Article article, Article modifiedArticle) throws DaoException {
		if(article!=null && modifiedArticle!=null) {
			//Nombre
			if(!article.getName().equals(modifiedArticle.getName())) {
				try {
					article.setName(modifiedArticle.getName());
				} catch (ArticleException e) {
					throw new DaoException(e.getMessage());
				}
			}
			//Descripción
			if(!article.getDescription().equals(modifiedArticle.getDescription())) {
				try {
					article.setDescription(modifiedArticle.getDescription());
				} catch (ArticleException e) {
					throw new DaoException(e.getMessage());
				}
			}
			//Cantidad
			if(article.getQuantity()!=modifiedArticle.getQuantity()) {
				try {
					article.setQuantity(modifiedArticle.getQuantity());
				} catch (ArticleException e) {
					throw new DaoException(e.getMessage());
				}
			}
			//Precio
			if(article.getPrice()!=modifiedArticle.getPrice()) {
				try {
					article.setPrice(modifiedArticle.getPrice());
				} catch (ArticleException e) {
					throw new DaoException(e.getMessage());
				}
			}
			//Categoría
			if(!article.getCategory().equals(modifiedArticle.getCategory())) {
				try {
					article.setCategory(modifiedArticle.getCategory());
				} catch (ArticleException e) {
					throw new DaoException(e.getMessage());
				}
			}
			//Imagen
			if((article.getImage()==null && modifiedArticle.getImage()!=null)
					|| (article.getImage()!=null && modifiedArticle.getImage()!=null)) {
				try {
					article.setImage(modifiedArticle.getImage());
				} catch (ArticleException e) {
					throw new DaoException(e.getMessage());
				}
			}
		}
		return article;
	}
	
	public static void editArticle(int cod, Article modifiedArticle) throws DaoException {
		Session session = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			Article article = getArticle(cod);
			Article newArticle = modifyArticle(article, modifiedArticle);
			session.getTransaction().begin();
			session.update(newArticle);
			session.getTransaction().commit();
			session.close();
		}catch(DaoException e) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(e.getMessage());
		}catch(Exception g) {
			if(session!=null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
			throw new DaoException("Articulo repetido.");
		}
	}
}
