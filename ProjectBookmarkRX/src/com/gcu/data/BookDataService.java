package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.model.Book;

/**Class of BookDataService that takes information from the BookDataAccessInterface.
 * @author Joe Leon | Jarren Fredricks
 * @since 11-4-18
 * @category DAO
 */
public class BookDataService implements BookDataAccessInterface<Book> {
	
	//Implements Spring JDBC components
	// - dataSource which is critical
	// jdbcTemplateObject that does a ton of stuff.
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	/**Function that pulls all information from the BOOK_STATUS database and returns it to the BookBusinessService.
	 * @return books
	 */
	@Override
	public List<Book> findAll() {
		//SQL String to execute in the database.
		String sql = "SELECT * FROM bookstracker.BOOK_STATUS";
		//Create a empty list of books.
		List<Book> books = new ArrayList<Book>();
		try {
			//execute SQL statement and populates the new books list with the information from the databse.
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			//For each entry in the database, it create a new entry in teh list.
			while(srs.next())
			{
				//the list will be composed of: Title, author, pages read, pages total, starting date, last date read, and the id of the list.
				// This corresponds to the TITLE, AUTHOR, PAGESREAD, PAGESTOTAL, STARTDATE, LASTDATE, publishedDate, BOOK_ID.
				books.add(new Book(srs.getString("TITLE"), srs.getString("AUTHOR"), srs.getInt("PAGESREAD"), srs.getInt("PAGESTOTAL"), srs.getString("STARTDATE"), srs.getString("LASTDATE"), srs.getString("LASTDATE"),srs.getInt("BOOK_ID")));
			}
			/*
             * catch this exception and don't swollow it
            *create own custom exception that takes orginal excetpion with it
            *and throw it
            *throw an unchecked exception instead
            */
        }catch(Exception e) {
            e.printStackTrace();
            //create custom database exception
            //throw new DatabaseException(e);
        }
		//Returns the list of books to the BooksBusinessService.
		return books;
	}
	
	/**This one doesn't really do anything right now.
	 * @param int id
	 * @return null
	 */
	
	/**Takes the value of bookID and uses that to pull records from the database.
	 * @param
	 * @return
	 */
	@Override
	public Book findByBookID(int id) {
		String sql = "SELECT * FROM BOOKSTRACKER.BOOK_STATUS WHERE BOOK_ID = "+id+"";
		Book book = new Book();
		try {
			//execute SQL
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
			book = new Book(srs.getString("TITLE"), srs.getString("AUTHOR"), srs.getInt("PAGESREAD"), srs.getInt("PAGESTOTAL"), srs.getString("STARTDATE"), srs.getString("LASTDATE"), srs.getString("PUBLISHDATE"),srs.getInt("BOOK_ID"));
			/*
             * catch this exception and don't swollow it
            *create own custom exception that takes orginal excetpion with it
            *and throw it
            *throw an unchecked exception instead
            */
			}
			System.out.println(book);
			return book;
        }catch(Exception e) {
            e.printStackTrace();
            //create custom database exception
            //throw new DatabaseException(e);
        }
		System.out.println(book);
		return book;
	}
	
	/*
	public Book findByBookID(int id) 
	{
		System.out.println("In the DAO");
		System.out.println(id);
		String sql = "SELECT * FROM BOOKSTRACKER.BOOK_STATUS WHERE BOOK_ID = ?";
		Book book = new Book();
		try
		{
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);
			book = new Book(srs.getString("TITLE"), srs.getString("AUTHOR"), srs.getInt("PAGESREAD"), srs.getInt("PAGESTOTAL"), srs.getString("STARTDATE"), srs.getString("LASTDATE"), srs.getString("LASTDATE"),srs.getInt("BOOK_ID"));		
			System.out.println(book);
			/*
         * catch this exception and don't swollow it
        *create own custom exception that takes orginal excetpion with it
        *and throw it
        *throw an unchecked exception instead
        *
    }catch(Exception e) {
        e.printStackTrace();
        //create custom database exception
        //throw new DatabaseException(e);
    }
	//Returns the list of books to the BooksBusinessService.
	return book;
}
*/

	/**Creates a new book by taking the information from the BookBusinessService, and placing it into the database.
	 * @param 
	 * @return boolean
	 */
	@Override
	public boolean create(Book book) 
	{
		//SQL String that tells the database what to do.
		String sql= "INSERT INTO bookstracker.BOOK_STATUS(TITLE, AUTHOR, PAGESREAD, PAGESTOTAL, STARTDATE, LASTDATE, PUBLISHDATE, BOOK_USER_ID) VALUES(?,?,?,?,?,?,?,?)";
		try {
			System.out.println("DAO TEST BEFORE");
			//Inserts a new row into the database using TITLE, AUTHOR, PAGESREAD, PAGESTOTAL, STARTDATE, LASTDATE, PUBLISHDATE, BOOK_USER_ID values.
			int rows = jdbcTemplateObject.update(sql,book.getTitle(),book.getAuthor(),book.getPagesRead(),book.getPagesTotal(),book.getStartDate(),book.getLastDate(),book.getPublishDate(),1);
			System.out.println("DAO TEST AFTER");
			//returns the first row and... ???
			 return rows ==1 ? true:false;
			 /*
	             * catch this exception and don't swollow it
	            *create own custom exception that takes orginal excetpion with it
	            *and throw it
	            *throw an unchecked exception instead
	            */
	        }catch(Exception e) {
	            e.printStackTrace();
	            //create custom database exception
	            //throw new DatabaseException(e);
	        }
		//If the update fails, then it returns false.
		return false;
	}

	/**THIS is the update function that rewrites the variables that already exist in a row in the database.
	 * @param
	 * @return boolean
	 */
	@Override
	public boolean update(Book book) 
	{
		String sql="UPDATE BOOKSTRACKER.BOOK_STATUS SET TITLE =?, AUTHOR = ?, PAGESREAD = ?, PAGESTOTAL = ?, STARTDATE = ?, LASTDATE = ?, PUBLISHDATE = ? WHERE BOOK_ID = ?";
				
			try {
				int rows = jdbcTemplateObject.update(sql,book.getTitle(),book.getAuthor(), book.getPagesRead(),book.getPagesTotal(),book.getStartDate(),book.getLastDate(),book.getPublishDate(),book.getBookID());
				 return rows ==1 ? true:false;
			/*
             * catch this exception and don't swollow it
            *create own custom exception that takes orginal excetpion with it
            *and throw it
            *throw an unchecked exception instead
            */
        }catch(Exception e) {
            e.printStackTrace();
            //create custom database exception
            //throw new DatabaseException(e);
        }
			return false;
		}
	

	/** Deletes a record from the database based on the bookID.
	 * @param
	 * @return boolean
	 */
	@Override
	public boolean delete(Book book) 
	{
		//SQL statment that tell the database to delete a row of information where the BOOK_ID is 
		String sql = "DELETE FROM BOOKSTRACKER.BOOK_STATUS WHERE BOOK_ID = ?";// + book.getBookID();
		try {
			//The jdbcTemplateObject uses the sql statement, and the BookID to tell the database what to do.
			int rows = jdbcTemplateObject.update(sql, book.getBookID());
			 return rows ==1 ? true:false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//****Dependency and helper functions ****
	/**
	 * IoC helper function
	 * @param dataSource Spring Data Source to inject into this DAO
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Book findById(Book book) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
