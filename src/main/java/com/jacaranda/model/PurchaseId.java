package com.jacaranda.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PurchaseId implements Serializable {
	
	private int article;
	private String user;
	private LocalDateTime date;
	
	
	public PurchaseId() {
		super();
	}


	public PurchaseId(int articleId, String userNick, LocalDateTime date) {
		super();
		this.article = articleId;
		this.user = userNick;
		this.date = date;
	}


	public int getArticleId() {
		return article;
	}


	public void setArticleId(int articleId) {
		this.article = articleId;
	}


	public String getUserNick() {
		return user;
	}


	public void setUserNick(String userNick) {
		this.user = userNick;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
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
		PurchaseId other = (PurchaseId) obj;
		return article == other.article && Objects.equals(date, other.date)
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "PurchaseId [articleId=" + article + ", userNick=" + user + ", date=" + date + "]";
	}

	
	
}