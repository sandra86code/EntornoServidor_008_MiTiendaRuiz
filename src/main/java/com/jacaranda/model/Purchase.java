package com.jacaranda.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	private int cod;
	@Column(name="article_price")
	private double price;
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	@ManyToOne
	@JoinColumn(name="user_nick")
	private User user;
	@Column(name="purchase_date")
	private LocalDate date;
	private int quantity;
	
	
	public Purchase() {
		super();
		
	}


	public Purchase(int cod, double price, Article article, User user, int quantity) throws PurchaseException {
		super();
		this.cod = cod;
		this.setPrice(price);
		this.setArticle(article);
		this.setUser(user);
		this.date = LocalDate.now();
		this.setQuantity(quantity);
	}


	public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
		this.cod = cod;
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


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) throws PurchaseException {
		if(date==null || date.isAfter(LocalDate.now())) {
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
		return Objects.hash(cod);
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
		return cod == other.cod;
	}


	@Override
	public String toString() {
		return "Purchase [cod=" + cod + ", price=" + price + ", article=" + article + ", user=" + user + ", date="
				+ date + "]";
	}
}
