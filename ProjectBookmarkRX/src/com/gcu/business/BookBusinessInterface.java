
package com.gcu.business;

import java.util.List;

import com.gcu.model.*;

/**
 * 
 * @author Joe Leon | Jerran Fredricks
 * @since ???
 * @category Business Layer
 */
public interface BookBusinessInterface 
{
	//These first three methtods are used for bean sessions.
	public void init();
	public void destroy();
	
	//???
	public void test();
	
	//Method used to find all entries within a database.
	public List<Book> getAllBooks();
	
	//Method used to find a single entry in the database based on the bookID.
	public Book getbyBookID(int ID);
	
	//Method used to add a book to the database.
	public boolean addBook(Book book);
	//code for insert user information into the database.
	public boolean register(User fullUser);
	
	//Method used to check the database for an existing user.
	public boolean login(LoginUser partialUser); 
	
	//Method used to update information about a book.
	public boolean update(Book book);
	
	//Method used to remove a book's information from the database.
	public boolean deleteBook(Book book);
}
