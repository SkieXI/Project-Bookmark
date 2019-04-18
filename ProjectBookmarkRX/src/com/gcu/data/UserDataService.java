package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.model.*;

/** DAO and all major methods that are related to managing user data.
 * @author Joe Leon | Jerran Fredricks
 * @since 11-4-18
 * @category DAO
 */
public class UserDataService implements UserDataAccessInterface<User> {
	
	
	/**Implements Spring components.
	 */
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	/**Finds all users within the USER table.
	 * @param
	 * @return users
	 */
	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM bookstracker.BOOK_USER";
		List<User> users = new ArrayList<User>();
		try {
			//execute SQL
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()){
				//FullUser() needs to be non-defualt constructor
				users.add(new User());
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
		return users;
	}
	
	/** Finds a user based on their USER_ID
	 * @param int id
	 * @return null
	 */
	@Override
	public User findById(int id) {
		return null;
	}
	
	/** Finds the specific user based on their login infromation.
	 *  - USERNAME and PASSWORD
	 * @param user
	 * @return users
	 */
	@Override
	public List<User> findBy(LoginUser user) {
		String sql = "SELECT * FROM bookstracker.BOOK_USER WHERE EMAIL = '"+user.getEmail()+"' AND PASSWORD = '" +user.getPassword()+"'";
		List<User> users = new ArrayList<User>();
		try {
			//execute SQL
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()){
				//FullUser() needs to be non-defualt constructor
				users.add(new User());
				System.out.println(users);
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
		System.out.println(users);
		return users;
	}
	
	/** Creates a new user within the USER table.
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean create(User user) {
		String sql= "INSERT INTO bookstracker.BOOK_USER(EMAIL,PASSWORD,PERMISSION,FIRSTNAME,LASTNAME,USERNAME) VALUES(?,?,?,?,?,?)";
		try {
			int rows = jdbcTemplateObject.update(sql,user.getEmail(),user.getPassword(),user.getPermission(),user.getFirstName(),user.getLastName(),user.getUserName());
			 return rows ==1 ? true:false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** Updates the user's information... eventually.
	 *  @param user
	 *  @return boolean
	 */
	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/** Deletes the user from the USER table.
	 *  @param 
	 *  @return boolean
	 */
	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	//****Dependency and helper functions ****
		/**dataSource Spring Data Source to inject into this DAO
		 * IoC helper function
		 * @param 
		 */
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
			this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		}
}
