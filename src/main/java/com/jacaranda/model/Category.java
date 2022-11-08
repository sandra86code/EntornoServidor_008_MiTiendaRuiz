package com.jacaranda.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private String name;
	private String description;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Article> articles;
	
	
	
	public Category() {
		super();

	}

	
	public Category(String name, String description) throws CategoryException {
		super();
		this.setName(name);
		this.setDescription(description);
		this.articles = new ArrayList<>();
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
	
	
	public void setName(String name) throws CategoryException {
		if(name==null || name.isBlank() || name.isEmpty() || name.length()<2|| name.length()>50) {
			throw new CategoryException("Nombre incorrecto");
		}
		this.name = name;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) throws CategoryException {
		if(description==null || description.isBlank() || description.isEmpty() || description.length()<2|| description.length()>240) {
			throw new CategoryException("Descripcion incorrecta");
		}
		this.description = description;
	}
	
	
	public List<Article> getArticles() {
		return articles;
	}
	
	
	public void setArticles(List<Article> articles) throws CategoryException {
		if(articles==null) {
			throw new CategoryException("Lista de articulos incorrecta");
		}
		this.articles = articles;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(name, other.name);
	}
	
	
	@Override
	public String toString() {
		return "Category [cod=" + cod + ", name=" + name + ", description=" + description + ", articles=" + articles
				+ "]";
	}

	
	
	
	
}
