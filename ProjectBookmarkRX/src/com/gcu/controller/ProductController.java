package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.BookBusinessInterface;
import com.gcu.model.Book;

/**
 * Controller for any methods or pages that use the book model class or books in
 * general.
 * 
 * @author Jerran Fredricks | Joe Leon
 * @since 11-4-18
 * @category Controller
 */
@Controller
@RequestMapping("/user")
public class ProductController {

	// Calls the BookBusinessInterface and designates it "service"
	BookBusinessInterface service;

	/**
	 * Takes the user to addProduct.jsp
	 * 
	 * @param GET
	 * @return editProduct.jsp
	 */
	@RequestMapping(path = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct() {
		return new ModelAndView("addProduct", "book", new Book());
	}

	/**
	 * Takes the user to the editProduct.jsp
	 * 
	 * @param GET
	 * @return editProduct.jsp
	 */
	@RequestMapping(path = "/editProduct/{id}", method = RequestMethod.GET)
	public ModelAndView editProductDisplay(@Valid @ModelAttribute("book") Book book, @PathVariable String id,
			BindingResult result) {
		System.out.println("========================");
		int search = Integer.parseInt(id);
		book = service.getbyBookID(search);
		return new ModelAndView("editProduct", "book", book);
	}

	/**
	 * Refreshed the displayProduct Page to actually display the displayProduct
	 * 
	 * @param book
	 * @param result
	 * @return
	 */
	@RequestMapping(path = "/displayRefresh", method = RequestMethod.GET)
	public ModelAndView displayRefresh(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		System.out.println(result);

		// If there are problems with the validation, then the same page will be
		// displayed with error messages.
		if (result.hasErrors()) {
			return new ModelAndView("displayProduct", "book", book);
		}
		// If the validation checks out, then the information entered in the textboxes
		// will be uploaded to the database.
		else {
			// call method from the service interface
			// service.addBook(book);
			// List of books is generated from the getAllBooks method from the
			// BookBusinessInterface.
			List<Book> books = service.getAllBooks();
			// Once the list is made, then the page displayProduct is brought up with the
			// contents of
			// the list laid out for the user.
			return new ModelAndView("displayProduct", "book", books);
		}
	}

	/**
	 * Adds a book's information from the addProduct page into the database and
	 * brings up the display product page.
	 * 
	 * @param book
	 * @param result
	 * @return
	 */
	@RequestMapping(path = "/productsAdded", method = RequestMethod.POST)
	public ModelAndView productsAdded(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		System.out.println(result);

		// If there are problems with the validation, then the same page will be
		// displayed with error messages.
		if (result.hasErrors()) {
			return new ModelAndView("addProduct", "book", book);
		}
		// If the validation checks out, then the information entered in the textboxes
		// will be uploaded to the database.
		else {
			// call method from the service interface
			service.addBook(book);
			System.out.println("TEST1");
			// List of books is generated from the getAllBooks method from the
			// BookBusinessInterface.
			List<Book> books = service.getAllBooks();

			// Once the list is made, then the page displayProduct is brought up with the
			// contents of
			// the list laid out for the user.
			return new ModelAndView("displayProduct", "book", books);
		}
	}

	/**
	 * Adds a book's information from the addProduct page into the database and
	 * brings up the display product page.
	 * 
	 * @param book
	 * @param result
	 * @return
	 */
	@RequestMapping(path = "/updateProduct", method = RequestMethod.POST)
	public ModelAndView editProduct(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		service.update(book);
		return new ModelAndView("displayProduct", "book", book);
	}

	/**
	 * Adds a book's information from the addProduct page into the database and
	 * brings up the display product page.
	 * 
	 * @param book
	 * @param result
	 * @return displayProduct.jsp
	 */
	@RequestMapping(path = "/deleteProduct", method = RequestMethod.POST)
	public ModelAndView deleteProduct(@Valid @ModelAttribute("book") Book book, BindingResult result)
	{
		System.out.println(book.getBookID());
		service.deleteBook(book);
		return new ModelAndView("displayProduct", "book", book);
	}

	/**
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(BookBusinessInterface service) {
		this.service = service;
	}
}
