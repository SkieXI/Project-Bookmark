package com.gcu.model;


//NOTES: For debugging purposes, password and email min length has been edited for 3 characters long.
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/** This class is used for variables and parameters related to the login process. 
 * This includes userName, email, password, and permission
 * @author Joe Leon | Jerran Fredricks
 * @since 11-4-18
 * @category Model
 */
public class LoginUser 
{
	
	
	
	@NotNull(message="Email cannont be null.")
	@Size(min=8, max=30, message="Email must be between 8 and 30 characters.")
	private String email;
	@NotNull(message="Password cannot be null.")
	@Size(min=6, max=30, message="Password must be between 6 and 30 characters.")
	private String password;
	//permission will be auto populated as a value of 1 to mark the user as a generic user, value 0 will mark them as an admin.
	@Min(value=0, message="Invalid value for permission.")
	@Max(value=1, message="Invalid value for permission.")
	private int permission;
	
	/** Constructor
	 * @param email
	 * @param password
	 */
	public LoginUser(String email,String password)
	{
		this.email = email;
		this.password = password;
		//here permission is marked as 1
		this.permission = 1;
	}
	/** Other Constructor
	 */
	public LoginUser() {
		this.email = "";
		this.password = "";
		//here permission is marked as 1
		this.permission =1;
	}
	//=== Getters and Setters ===
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	
}
