package org.polytec.vermeg.controller;



import java.util.List;

import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public String sayHello(){
        return "Hello from the other side ";
    }
	
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooks() {
		
		List<Book> listOfBooks = bookService.getAllBooks();
		
		return listOfBooks;
	}

	@RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Book getBookById(@PathVariable int id) {
		return bookService.getBook(id);
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addBook(@ModelAttribute("book") Book book) {	
		if(book.getId()==0)
		{
			bookService.addBook(book);
		}
		else
		{	
			bookService.updateBook(book);
		}
		
		return "redirect:/getAllBooks";
	}

	@RequestMapping(value = "/updateBook/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateBook(@PathVariable("id") int id,Book book) {
		bookService.updateBook(book);
		 return "redirect:/getAllBooks";
	}

	@RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteBook(id);
		 return "redirect:/getAllBooks";

	}	
	// Calcule prix total :
//		@RequestMapping(value = "/calculerPrix/{id}/{quantite}", method = RequestMethod.GET, headers = "Accept=application/json")
//		public long calculerPrix(@PathVariable int id, @PathVariable int quantite) {	
//			long prixUnitaire = BookService.getBook(id).getPrixUnitaire();
//			long montant=prixUnitaire*quantite;
//			return montant;
//		}
			
		// Calcule somme prix unitaire des livres choisi :
		@RequestMapping(value = "/calSommePrixTotal", method = RequestMethod.POST, headers = "Accept=application/json")
		public double calSommePrixTotal(@RequestBody List<Integer> listBooks) {	
			double montant=0;

			for (int i=0;i<listBooks.size();i++) {
			int id = listBooks.get(i);
				
				montant= montant+bookService.getBook(id).getPrice();		
			}		
			return montant;
		}
}
