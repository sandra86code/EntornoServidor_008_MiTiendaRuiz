package com.jacaranda.control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.hibernate.Session;

import com.jacaranda.cart.Cart;
import com.jacaranda.cart.CartItem;
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
			Purchase purchase = new Purchase(cartItem.getArticle(), DaoUser.getUser(nick), 
					LocalDateTime.now(), cartItem.getArticle().getPrice(), cartItem.getQuantity());
			session.getTransaction().begin();
			session.save(purchase);
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
