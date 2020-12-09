package org.polytec.vermeg.test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytec.vermeg.controller.BookController;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class test {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private BookService bookservice;
	
	@InjectMocks
	private BookController bc ;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bc).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetBooks() {
		List <Book> books = new ArrayList<Book>();
		books.add(new Book(1,"a","a",1.0,null)) ;
		books.add(new Book(2,"a","a",1.0,null)) ;
		books.add(new Book(3,"a","a",1.0,null)) ;
		when(bookservice.getAllBooks()).thenReturn(books)
		;
		
			
			
					try {
						this.mockMvc.perform(get("/api/book/getAll")).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(3))).andDo(print());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
		
		}
	@Test
	void testGetBookById() throws Exception {
		Book b= new Book(1, "jhon", "jack", 5, null);
		int idBook=b.getId();
		when(bookservice.getBook(idBook)).thenReturn(b);
		this.mockMvc.perform(get("/api/book/getBook/"+idBook))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	void testAddBook() throws Exception {
		Book b= new Book(1, "jhon", "jack", 5, null);
		bookservice.addBook(b);;
		   verify(bookservice, times(1)).addBook(b);
	
	 this.mockMvc.perform(post("/api/book/addBook")).andExpect(status().isOk()).andDo(print());	}

	@Test
	void testUpdateBook() throws Exception {
		Book b= new Book(1, "jhon", "jack", 5, null);
		Book b1= new Book(1, "fly me to the moon", "forbs", 6, null);
		int idBook =b.getId();
		bookservice.updateBook(b);
		verify(bookservice, times(1)).updateBook(b);
		 this.mockMvc.perform(put("/api/book/updateBook/"+idBook)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void testDeleteBook() throws Exception {
		Book b= new Book(1, "jhon", "jack", 5, null);
		int idBook =b.getId();
		bookservice.deleteBook(idBook);
	verify(bookservice, times(1)).deleteBook(idBook);
    this.mockMvc.perform(delete("/api/book/deleteBook/"+idBook)).andExpect(status().isOk()).andDo(print());
		}
	@Test
	void testCalSommePrixTotal() throws Exception {
		Book b= new Book(1, "jhon", "jack", 5, null); 
		Book b0= new Book(2, "jhon", "jack", 10, null);
		Book b1= new Book(3, "jhon", "jack", 15, null);
		List <Integer> liste = new ArrayList <Integer>();
		liste.add(b.getId());
		liste.add(b0.getId());
		liste.add(b1.getId());
		
		this.mockMvc.perform(post("/api/book/calSommePrixTotal")).andExpect(status().isOk()).andDo(print());
		
		assertEquals(bc.calSommePrixTotal(liste),30.0);
		
	}

}
