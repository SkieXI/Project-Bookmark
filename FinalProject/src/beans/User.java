package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
@SessionScoped
public class User 
{
	@NotNull(message = "Please enter a First Name. This is a required field.")
	@Size(min=4, max=15)
	String firstName = "";
	
	@NotNull(message = "Please enter a Last Name. This is a required field.")
	@Size(min=4, max=15)
	String lastName = "";
	
	String userName = "";
	String password = "";
	int admin = 0;
	
	public User()
	{
		firstName = "Mark";
		lastName = "Reha";
		userName = "aaa";
		password = "JoshSand";
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
