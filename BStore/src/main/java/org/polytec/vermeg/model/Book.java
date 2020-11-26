package org.polytec.vermeg.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Book")
public class Book {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
private int id;
@Column(name="title")
private String title;
	
	@Column(name="author")
private String author;

@Column(name="price")
private double price;
@Column(name="releasedate")


private Date releasedate;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Date getReleasedate() {
	return releasedate;
}
public void setReleasedate(Date releasedate) {
	this.releasedate = releasedate;
}
public Book(int id, String title, String author, double price, Date releasedate) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
	this.price = price;
	this.releasedate = releasedate;
}
public Book() {
	super();
}
@Override
public String toString() {
	return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", releasedate="
			+ releasedate + "]";
}

}