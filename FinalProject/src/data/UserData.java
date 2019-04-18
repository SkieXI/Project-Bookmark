package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.User;

@Stateless
@LocalBean
public class UserData
{
	private String url = "jdbc:derby:C:\\Users\\Joe\\MyDB";
	private String username = "root";
	private String password = "derby";
	
	public UserData() {

	}

	public boolean create(User t) {
		Connection conn = null;	
		String sql = "INSERT INTO ROOT.CTWUSER(FIRST_NAME, LAST_NAME, ADMIN, USER_NAME, PASSWORD)"
				+ " VALUES('" + t.getFirstName() + "', '" + t.getLastName() + "', 0,'" + t.getUserName() + "', '"
				+ t.getPassword() + "')";

		boolean create = true;
		try {
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return create;

	}

	public User findUser(User t) {
		System.out.println(t.getUserName() + "->");
		User userInfo = new User();
		Connection conn = null;
		String sql = "SELECT * FROM ROOT.CTWUSER WHERE USER_NAME = '" + t.getUserName() + "' AND PASSWORD = '"
				+ t.getPassword() + "'";
		System.out.println(sql);
		int count = 0;
		try {
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Test 1.");

			while (rs.next()) {
				count++;
				userInfo.setFirstName(rs.getString("FIRST_NAME"));
				userInfo.setLastName(rs.getString("LAST_NAME"));
				userInfo.setUserName(rs.getString("USER_NAME"));
				userInfo.setPassword(rs.getString("PASSWORD"));
				userInfo.setAdmin(rs.getInt("ADMIN"));
				
				System.out.println(userInfo.getFirstName());
				System.out.println(userInfo.getUserName());
				System.out.println(userInfo.getAdmin());
				System.out.println("===");
				
			}
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (count > 0) {

			System.out.println("Test line Final: ");
			System.out.println(userInfo.getUserName());
			return userInfo;
			
		} else {
			return null;
		}

	}

	//Override
	public List<User> findAllUsers() {
		Connection conn = null;
		String sql = "SELECT * FROM root.CTWUSER";
		List<User> users = new ArrayList<User>();
		try {
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("RecordDataService is Called");
				users.add(new User());
				//rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
				//		 rs.getString("USER_NAME"), rs.getString("PASSWORD")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}
	public Boolean isAdmin(User u) {
		System.out.println(u.getUserName() + "->");
		int adminInt = 0;
		//User userInfo = new User();
		//This method WORKS, the problem is that the user variables do not stick when you log in.
		//If the Homepage kept their variables, then there would be no problems.
		
		Connection conn = null;
		String sql = "SELECT * FROM ROOT.CTWUSER WHERE USER_NAME = '" + u.getUserName() + "'";
		//String sql = "SELECT * FROM ROOT.CTWUSER WHERE ADMIN > " + 0 + "";
		System.out.println(sql);
		int count = 0;
		try {
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(count);
			while (rs.next())
			{
				adminInt = rs.getInt("ADMIN");
				System.out.println(adminInt);
				count++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(adminInt > 0) {
			System.out.println(count);
			System.out.println("Is a admin.");
			return true;
		}
		else {
			System.out.println("Is not a admin.");
			System.out.println(count);
			return false;
		}
	}
}