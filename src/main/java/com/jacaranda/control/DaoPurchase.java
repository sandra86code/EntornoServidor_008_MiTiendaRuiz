package com.jacaranda.control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.hibernate.Session;

import com.jacaranda.cart.Cart;
import com.jacaranda.cart.CartItem;
import com.jacaranda.model.Article;
import com.jacaranda.model.Purchase;
import com.jacaranda.model.PurchaseException;

public class DaoPurchase {
	
	
	
	public DaoPurchase() {
		super();
	}

	public static boolean addPurchase(CartItem cartItem, String nick) throws DaoException {
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
}
