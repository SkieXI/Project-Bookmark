
package com.gcu.model;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Contains information related to how a user registers.
 *  Includes: firstName, lastName, and userName.
 * 
 * @author Joe Leon | Jerran Fredricks
 * @since 11-4-18
 * @category Model
 */
public class User extends LoginUser {
	@NotNull(message="First name cannot be null.")
	@Size(min=2, max=30, message="First name must be between 2 and 30 characters.")
private String firstName;
	@NotNull(message="Last name cannot be null.")
	@Size(min=2, max=30, message="Last name must be between 2 and 30 characters.")
private String lastName;
	@NotNull(message="User name cannont be null.")
	@Size(min=2, max=30, message="User name must be between 2 and 30 characters.")
private String userName;
	
	/** Constructor
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param userName
	 */
	public User(String email, String password,String firstName,String lastName,String userName) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}
	/** Other Constructor
	 */
	public User() {
		super();
		this.firstName = "";
		this.lastName = "";
		this.userName = "";
	}
	//=== Getters and Setters ===
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String toString() {
		return this.getEmail()+ "=="+this.getPassword()+"=="+this.getPermission()+"=="+this.firstName+"=="+this.lastName+"=="+this.userName;
	}

}
