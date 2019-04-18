//Created a long time ago.
package com.gcu.data;

import java.util.List;

import com.gcu.model.Book;

/** Interface for the DAO that involves books.
 * @author Joe Leon | Jarren Fredricks
 * @since 11-4-18
 * @category DAO
 */
public interface BookDataAccessInterface <T>{
	public List<T> findAll();
	public T findById(Book book);
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
	public Book findByBookID(int t);
}
