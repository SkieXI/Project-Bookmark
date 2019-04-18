package com.gcu.data;

import java.util.List;

import com.gcu.model.LoginUser;

/** Interface for all user related functions within the DAO layer.
 * 
 * @author Joe Leon | Jerran Fredricks
 * @since 11-4-18
 * @param <T>
 * @category DAO
 */
public interface UserDataAccessInterface <T>{
	public List<T> findAll();
	public T findById(int id);
	public List<T> findBy(LoginUser user);
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
}
