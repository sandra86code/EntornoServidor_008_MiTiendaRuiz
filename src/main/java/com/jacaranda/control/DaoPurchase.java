package com.jacaranda.control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.jacaranda.cart.Cart;
import com.jacaranda.cart.CartItem;
import com.jacaranda.model.Article;
import com.jacaranda.model.Purchase;
import com.jacaranda.model.Purchase;
import com.jacaranda.model.PurchaseException;
import com.jacaranda.model.User;

public class DaoPurchase {
	
	
	
	public DaoPurchase() {
		super();
	}

	public static boolean addArticle(CartItem cartItem, String nick) throws DaoException {
		boolean result = false;
		Session session = null;
		if(cartItem==null) {
			throw new DaoException("Item nulo");
		}
		if(nick==null) {
			throw new DaoException("Nombre de usuario nulo");
		}
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			Article article = cartItem.getArticle();
			Purchase purchase = new Purchase(article, DaoUser.getUser(nick), 
					LocalDateTime.now(), article.getPrice(), cartItem.getQuantity());
			article.setQuantity(article.getQuantity()-cartItem.getQuantity());
			session.getTransaction().begin();
			session.save(purchase);
			session.update(article);
			session.getTransaction().commit();
			session.close();
			result = true;
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(PurchaseException f) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(f.getMessage());
		}catch(Exception g) {
			if(session!=null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
			throw new DaoException("Compra repetida");
		}
		return result;
	}
	
	public static List<Purchase> getPurchases(String usernick) throws DaoException {
		ArrayList<Purchase> purchases;
		Session session = null;
		if(usernick==null) {
			throw new DaoException("Usuario nulo");
		}
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			String hql = "SELECT article_id, purchase_date, article_price, quantity FROM purchase p WHERE user_nick='" + usernick + "' ORDER BY purchase_date DESC;";
			Query<Purchase> query = session.createNativeQuery(hql, Purchase.class);
			purchases = (ArrayList<Purchase>) query.getResultList();
			session.close();
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception f) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(f.getMessage());
		}
		return purchases;          
	}
}
