package com.jacaranda.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private String name;
	private String description;
	private double price;
	private int quantity;
	private Blob image;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToMany(mappedBy="article", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Purchase> purchases;
	
	
	
	
	
	public Article() {
		super();
	}
	
	public Article(String name, String description, double price, int quantity, 
			Category category) throws ArticleException {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setQuantity(quantity);
		this.setCategory(category);
		this.image = null;
		this.purchases = new ArrayList<>();
	}
	
	public Article(String name, String description, double price, int quantity, 
			Blob image, Category category) throws ArticleException {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setQuantity(quantity);
		this.setCategory(category);
		this.setImage(image);
		this.purchases = new ArrayList<>();
	}
	
	
	
	public int getCod() {
		return cod;
	}
	
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) throws ArticleException {
		if(name==null || name.isBlank() || name.isEmpty() || name.length()<2|| name.length()>50) {
			throw new ArticleException("Nombre incorrecto");
		}
		this.name = name;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) throws ArticleException {
		if(description==null || description.isBlank() || description.isEmpty() || description.length()<2|| description.length()>120) {
			throw new ArticleException("Descripcion incorrecta");
		}
		this.description = description;
	}
	
	
	public double getPrice() {
		return price;
	}
	
	
	public void setPrice(double price) throws ArticleException {
		if(price<=0) {
			throw new ArticleException("Precio incorrecto");
		}
		this.price = price;
	}
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws ArticleException {
		if(quantity<0) {
			throw new ArticleException("Cantidad incorrecta");
		}
		this.quantity = quantity;
	}

	public Blob getImage() {
		return image;
	}
	
	
	public void setImage(Blob image) throws ArticleException {
		if(image==null) {
			throw new ArticleException("Imagen nula");
		}
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}
	
	
	public void setCategory(Category category) throws ArticleException {
		if(category==null) {
			throw new ArticleException("Categoria nula");
		}
		this.category = category;
	}
	
	
	public List<Purchase> getPurchases() {
		return purchases;
	}

	
	public void setPurchases(List<Purchase> purchases) throws ArticleException {
		if(purchases==null) {
			throw new ArticleException("Lista de compras incorrecta");
		}
		this.purchases = purchases;
	}



	@Override
	public int hashCode() {
		return Objects.hash(category, cod, description, image, name, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(category, other.category) && cod == other.cod
				&& Objects.equals(description, other.description) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}

	@Override
	public String toString() {
		return "Article [cod=" + cod + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category=" + category + "]";
	}
	
	
}
