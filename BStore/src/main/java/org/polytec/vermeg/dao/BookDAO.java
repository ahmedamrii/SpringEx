package org.polytec.vermeg.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.polytec.vermeg.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BookDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Book> getAllBooks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Book> bookList = session.createQuery("from Book").list();
		
		for (Book b:bookList) {
			System.out.println("##### Count "+b.getTitle());
		}
		
		return bookList;
	}

	public Book getBook(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = (Book) session.get(Book.class, new Integer(id));
		return book;
	}

	public Book addBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(book);
		return book;
	}

	public void updateBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(book);
	}

	public void deleteBook(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Book p = (Book) session.load(Book.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}	

}
