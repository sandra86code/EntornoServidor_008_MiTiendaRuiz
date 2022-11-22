package com.jacaranda.cart;

import java.util.Objects;

import com.jacaranda.model.Article;

public class CartItem {
	
	private Article article;
	private int quantity;
	final double DECIMALES = 100.0;
	
	public CartItem() {
		super();
	}


	public CartItem(Article article, int quantity) throws CartExpection {
		super();
		this.setArticle(article);
		this.setQuantity(quantity);
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) throws CartExpection {
		if(article==null) {
			throw new CartExpection("Artículo nulo");
		}
		this.article = article;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) throws CartExpection {
		if(quantity<=0) {
			throw new CartExpection("Cantidad incorrecta");
		}
		this.quantity = quantity;
	}

	
	public double getPriceItem() {
		double resultado = this.article.getPrice()*this.quantity;
		return Math.round(resultado*DECIMALES)/DECIMALES;
	}

	@Override
	public int hashCode() {
		return Objects.hash(article);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(article, other.article);
	}


	@Override
	public String toString() {
		return "CartItem [article=" + article + ", quantity=" + quantity + "]";
	}
	
	
}
