package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.BookBusinessInterface;
import com.gcu.model.Book;

/** Rest service used to pull information from the DAO and populate the datatable in DisplayProduct.jspq
 * @author Jerran Fredricks | Joe Leon
 * @since 11-11-18
 * @category Business Layer
 */
@RestController
@RequestMapping("/service1")
public class BookRest {
	@Autowired
	BookBusinessInterface service;

	// Spring Rest service to return all valid flights in the database.
	@GetMapping("/books")
	public List<Book> getBooks() {
		//Test statement to ensure that the rest service is functioning. 
		//System.out.println("Returning");
		
		//Creates a list of Books of all the entries within the Book_Status database.
		List<Book> books = service.getAllBooks();

		//Returns that list of books for the data table.
		return books;
	}
}
