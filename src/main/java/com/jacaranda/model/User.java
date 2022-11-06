package com.jacaranda.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	@Id 
	private String nick;
	private String password;
	private String name;
	private String surname;
	@Column(name="birth_date")
	private LocalDate birthDate;
	private char sex;
	private boolean admin;
	
	@OneToMany(mappedBy="user")
	private List<Purchase> purchases;
	
	
	public User() {
		super();
	}


	


	public User(String nick, String password, String name, String surname, LocalDate birthDate, char sex,
			boolean admin) throws UserException {
		super();
		this.setNick(nick);
		this.setPassword(password);
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birthDate);
		this.setSex(sex);
		this.admin = admin;
		this.purchases = new ArrayList<>();
	}



	public String getNick() {
		return nick;
	}


	public void setNick(String nick) throws UserException {
		if(nick==null || nick.isEmpty() || nick.isBlank() || nick.length()<3 || nick.length()>30) {
			throw new UserException("Nick incorrecto");
		}
		this.nick = nick;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) throws UserException {
		if(password==null || password.isBlank() || password.isEmpty() || password.length()!=32) {
			throw new UserException("Password incorrecto");
		}
		this.password = password;
	}


	public String getName() {
		return name;
	}

	
	public void setName(String name) throws UserException {
		if(name==null || name.isBlank() || name.isEmpty() || name.length()<2|| name.length()>30) {
			throw new UserException("Nombre incorrecto");
		}
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) throws UserException {
		if(surname==null || surname.isBlank() || surname.isEmpty() || surname.length()<2 || surname.length()>60) {
			throw new UserException("Apellido/s incorrecto");
		}
		this.surname = surname;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) throws UserException {
		if(birthDate.isAfter(LocalDate.now())) {
			throw new UserException("Fecha de nacimiento incorrecta");
		}
		this.birthDate = birthDate;
	}


	public char getSex() {
		return sex;
	}


	public void setSex(char sex) throws UserException {
		if(sex!='H' && sex!='M') {
			throw new UserException("El sexo solo puede ser H o M.");
		}
		this.sex = sex;
	}

	
	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
	public List<Purchase> getPurchases() {
		return purchases;
	}





	public void setPurchases(List<Purchase> purchases) throws UserException {
		if(purchases==null) {
			throw new UserException("Lista de compras incorrecta");
		}
		this.purchases = purchases;
	}





	@Override
	public int hashCode() {
		return Objects.hash(nick);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(nick, other.nick);
	}





	@Override
	public String toString() {
		return "User [nick=" + nick + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", birthDate=" + birthDate + ", sex=" + sex + ", admin=" + admin + "]";
	}


	
}
