package org.polytec.vermeg.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.polytec.vermeg.dao.BookDAO;
import org.polytec.vermeg.service.BookService;
import org.polytec.vermeg.model.Book;
@ExtendWith ({MockitoExtension.class})
class BookServiceTest {
	Date d = new Date (2020, 11,11);	
	@Mock
	private BookDAO bookdao;
	
	@InjectMocks
	private BookService bookservice;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllBooks() {
		List <Book> books = new ArrayList<Book>();
		books.add(new Book(1,"a","a",1,d)) ;
		books.add(new Book(2,"a","a",1,d)) ;
		books.add(new Book(3,"a","a",1,d)) ;		
		when(bookdao.getAllBooks()).thenReturn(books)	;	
		List<Book> books1 = bookservice.getAllBooks() ;
		assertEquals(books1, books, "error");
	}

	@Test
	void testGetBook() {
		Book book1=new Book(9,"jack","jhon",55,d);
		when(bookdao.getBook(9)).thenReturn(book1);
		assertEquals(this.bookservice.getBook(9),book1);
	}

	@Test
	void testAddBook() {
Book book = new Book(1,"a","a",1,d);
		
		bookservice.addBook(book);
		verify(bookdao, times(1)).addBook(book); 
		
	}

	@Test
	void testUpdateBook() {
		Book book = new Book(10,"a","a",1,d);		
		bookdao.updateBook(book);		
		Book book1 = new Book(10,"b","b",1,d);
		bookdao.updateBook(book1);
		verify(bookdao, times(1)).updateBook(book1);
	}

	@Test
	void testDeleteBook() {
		Book book = new Book(1,"a","a",1,d) ;
		bookservice.deleteBook(1);
		verify(bookdao, times(1)).deleteBook(1);
	}

}
