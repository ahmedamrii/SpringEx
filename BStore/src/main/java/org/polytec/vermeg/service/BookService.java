package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.dao.BookDAO;

import org.polytec.vermeg.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("bookService")
public class BookService {
	@Autowired
	BookDAO bookDao;
	
	@Transactional
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Transactional
	public Book getBook(int id) {
		return bookDao.getBook(id);
	}

	@Transactional
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	@Transactional
	public void updateBook(Book book) {
		bookDao.updateBook(book);

	}

	@Transactional
	public void deleteBook(int id) {
		bookDao.deleteBook(id);
	}
}
