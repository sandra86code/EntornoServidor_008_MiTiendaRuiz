package com.jacaranda.model;


import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
@IdClass(PurchaseId.class)
public class Purchase {
	@Id
	@ManyToOne
	@JoinColumn(name="article_id", insertable = false, updatable = false)
	private Article article;
	@Id
	@ManyToOne
	@JoinColumn(name="user_nick", insertable = false, updatable = false)
	private User user;
	@Id
	@Column(name="purchase_date")
	private LocalDateTime date;
	@Column(name="article_price")
	private double price;
	private int quantity;
	
	
	public Purchase() {
		super();
		
	}


	public Purchase(Article article, User user, LocalDateTime date, double price, int quantity) throws PurchaseException {
		super();
		this.setArticle(article);
		this.setUser(user);
		this.setDate(date);
		this.setPrice(price);
		this.setQuantity(quantity);
	}




	public double getPrice() {
		return price;
	}


	public void setPrice(double price) throws PurchaseException {
		if(price<=0) {
			throw new PurchaseException("Precio incorrecto");
		}
		this.price = price;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) throws PurchaseException {
		if(article==null) {
			throw new PurchaseException("Articulo incorrecto");
		}
		this.article = article;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) throws PurchaseException {
		if(user==null) {
			throw new PurchaseException("Usuario incorrecto");
		}
		this.user = user;
	}


	
	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) throws PurchaseException {
		if(date==null || date.isAfter(LocalDateTime.now())) {
			throw new PurchaseException("Fecha incorrecta");
		}
		this.date = date;
	}

	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) throws PurchaseException {
		if(quantity<1) {
			throw new PurchaseException("Cantidad incorrecta");
		}
		this.quantity = quantity;
	}

	
	public double getTotalPrice() {
		return this.price * this.quantity;
	}


	@Override
	public int hashCode() {
		return Objects.hash(article, date, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		return Objects.equals(article, other.article) && Objects.equals(date, other.date)
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "Purchase [article=" + article + ", user=" + user + ", date=" + date + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
