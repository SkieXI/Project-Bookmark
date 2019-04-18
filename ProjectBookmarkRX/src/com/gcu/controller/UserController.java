package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.*;
import com.gcu.model.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Joe Leon | Jerran Fredricks
 * @since 11-3-18
 * @category Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

	// Call the BookBusinessInterface and designates it 'service'
	BookBusinessInterface service;

	/**
	 * Brings up the home.jsp page where users will start out.
	 * 
	 * @return home.jsp
	 */
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home", "user", new User("", "", "", "", ""));
	}

	/**
	 * Brings up the Register Page.
	 * 
	 * @return register.jsp
	 */
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "user", new User("", "", "", "", ""));
	}

	/**
	 * Takes the information from the Register.jsp page and inserts it into the
	 * database.
	 * 
	 * @param fullUser
	 * @param result
	 * @return login.jsp
	 */
	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User fullUser, BindingResult result) {
		System.out.println(result);
		if (result.hasErrors()) {
			// If the validation fails, it will refresh the page.
			return new ModelAndView("register", "user", fullUser);
		}
		System.out.println("Controller TEst 1");
		if (service.register(fullUser) == true) {
			// Method call that brings up the Business layer, and by extension the DTO.
			// service.register(fullUser);
			// Pulls the email and password fields and displays them on the login page so
			// the user doesn't have to retype.
			LoginUser user = new LoginUser(fullUser.getEmail(), fullUser.getPassword());
			System.out.println("Controller Test succeed");
			return new ModelAndView("login", "user", user);
		} else
			System.out.println("Controller test FAIELD");
		return new ModelAndView("register", "user", fullUser);
	}

	/**
	 * Brings up the login page.
	 * 
	 * @return login.jsp
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login", "user", new LoginUser("", ""));
	}

	/**
	 * Takes the information from the login page and compares it to the database for
	 * a match.
	 * 
	 * @param partialUser
	 * @param result
	 * @return displayProduct.jsp
	 */
	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(@Valid @ModelAttribute("user") LoginUser partialUser, BindingResult result) {
		System.out.println(result);

		if (result.hasErrors()) {
			// If the validation fails, then it will kick the user back to the login page.
			return new ModelAndView("login", "user", partialUser);
		} else {
			if (service.login(partialUser)) {

				/*
				 * DisplayProducts is suppose to populate this from the DB. Currently under
				 * investigation.
				 */
				List<Book> books = service.getAllBooks();
				for (int i = 0; i < books.size(); i++) {

				}
				// TEST, THIS GOES OUTSIDE THE LOOP.
				return new ModelAndView("displayProduct", "book", books);
			} else {
				// If the servicelogin passes, then it will take the
				return new ModelAndView("login", "user", partialUser);
			}
		}
	}

	/**
	 * Refresh page.
	 * 
	 * @param List<Book>
	 * @return books
	 */
	@GetMapping("/displayProduct")
	public List<Book> displayProduct() {
		System.out.println("Inside displayProduct");
		List<Book> books = service.getAllBooks();
		System.out.println("Returning");
		return books;
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
