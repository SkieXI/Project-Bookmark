/* Created 10-28-18
 * 
 * Business logic for all functions 
 * 
 */

package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.*;
import com.gcu.model.*;
 
/**Main business layer class to handle information from the controller and passes the corresponding information to the DAO.
 * @author Jerran Fredricks | Joe Leon
 * @since 10-28-18
 * @category Business Layer
 */
public class BookBusinessService implements BookBusinessInterface {

	
	//Makes the IDE not complain about generics to avoid confusing the developers.
	//But implements the BookDataAccessInterface and assigns it "dao".
	@SuppressWarnings("rawtypes")
	@Autowired
	BookDataAccessInterface dao;
	
	//Makes the IDE not complain about generics to avoid confusing the developers.
	//But implements the UserDataAccessInterface and assigns it "dao2".
	@SuppressWarnings("rawtypes")
	@Autowired
	UserDataAccessInterface<User> dao2;

	
	/**
	 * Used for bean sessions.
	 */
	public void init() {
		System.out.println("init() OrderBusinessService");
	}

	
	/**
	 * Used for bean sessions and ICA.
	 */
	public void destroy() 
	
	{
		System.out.println("destroy() in OrderBusinessService");
	}

	/**
	 * Used for bean session and ICA.
	 */
	@Override
	public void test() {
		// A test method that is left over from ICA.
		System.out.println("Hello from OrderBusinessService");
	}

	/** Uses the findall method from BookDataService to pull everything from the DTO. And passes the information back into
	 * BookController class.
	 * @param List<Book>
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() 
	{
		//Returns ALL the information that is available within the database.
		return dao.findAll();
	}
	
	
	/**Takes the variable of bookID within the Book model and uses that value to find information from the database.
	 * @param
	 * @return book
	 */
	@SuppressWarnings("unchecked")
	public Book getbyBookID(int ID)
	{
		System.out.println("In the business Layer");
		return dao.findByBookID(ID);
	}
	
	/**Takes the information from the book object and passes it along to the DAO to insert into the database.
	 * @param Book book
	 * @return boolean
	 */
	@Override
	public boolean addBook(Book book) 
	{
		//Sends the information to the create method in the BookDataService.
		//If the operation is successful, then the DAO will return a true value, if that is the case, it will pass that check
		//back to the ProductController.
		if(dao.create(book))
		{
			System.out.println("TEST SUCCESSFULLL BUSINESS");
			return true;
		}
		//If there is an error, then it will pass back a false.
		return false;
	}

	/**First calls a method that checks to see if the email entered in the register page already exist in the database.
	 * If so, then it returns false and the controller will reject sending the user to the login page.
	 * If it comes up with nothing, then it will proceed to insert the information to the database and return true.
	 * 
	 * @param User fullUser
	 * @return boolean
	 */
	public boolean register(User fullUser) 
	{
		/* When getting a return statement, because it alwasy sends a [] even when it fails, the best way to get around
		*  finding if it is empty or not is to do a .size method and if the return statement is 0 characters long, then 
		* that means that it didn't find anything.
		*/
		if (dao2.findBy(fullUser).size() == 0)
		{
			//If it doesn't find anything, then it calls the create function in the UserDataService and returns true.
			dao2.create(fullUser);
			return true;
		}
		else
			//If the return statement is longer than nothing, then it deny the register information and returns false.
		return false;
	}

	/**
	 * @param partialUser
	 * @return boolean
	 * (non-Javadoc)
	 * @see com.gcu.business.BookBusinessInterface#login(com.gcu.model.LoginUser)
	 */
	public boolean login(LoginUser partialUser) {
		User user = new User();
		List<User> loginList = dao2.findBy(partialUser);
		//if the user is found inside the datebase with the email and password login will work
		if (loginList.size() != 0) {
			user = loginList.get(0);
			return true;
		} else {
			return false;
		}

	}

	/** The function passes information to the update function in the BookDataService.
	 * @param book
	 * @return boolean
	 */
	@Override
	public boolean update(Book book) 
	{
		//The information in the book object is passed to the DTO to be updated to the 
		//database. 
		System.out.println("BUSINESS TEST UPDATE");
		if (dao.update(book))
			{
			//If it works, it returns true.
			return true;
			}
		//If it doesn't work, it returns false.
		return false;
	}

	/** Passes the information to the deleteBook function in the BookDataService. More or less its telling the DTO
	 * what needs to be deleted so that the entire database isn't wipped clean.
	 * 
	 * @param book
	 * @return boolean
	 */
	@Override
	public boolean deleteBook(Book book) 
	{
		System.out.println(book.getBookID());
		//Checks to see if the delete was successful.
		if (dao.delete(book))
		{
			System.out.println("IT WORKED.");
			//If the delete was, then it returns true.
			return true; 
		}
		else
			System.out.println("IT FAILED");
			//If not, then it returns false.
		return false;
	}
}
