package data;

import javax.ejb.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import beans.Petition;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class PetitionsDataService implements DataAccessInterface<Petition>{

	//Connection setup
	Connection conn = null;
	String url = "jdbc:derby:C:\\Users\\Joe\\MyDB";
	String user = "root";
	String pass = "derby";
	
	public PetitionsDataService() {
	}
	//INSERT================================================================================
	@Override
	public boolean insertPetition(Petition p) {
		String sql = "INSERT INTO root.CTWPETITION (P_USER, P_NAME, P_DESCRIPTION, P_BODY, VOTES_NEEDED, UP_VOTES, DOWN_VOTES) VALUES"+ 
				"('"+p.getPetitionUser()+"', '"+p.getPetitionName()+"', '"+p.getDesciption()+"', '"+p.getPetitionBody()+"', "+p.getVotesNeeded()+", "+p.getUpVotes()+", "+p.getDownVotes()+")";
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Inserted Item" +p.getPetitionName());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();
			}
		}
		}
		return true;
	}
	//UPDATE================================================================================
	@Override
	public boolean update(Petition p) {
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			
			PreparedStatement myStmt = conn.prepareStatement("UPDATE root.CTWPETITION SET P_NAME=?, P_DESCRIPTION=?, P_BODY=?, VOTES_NEEDED=? WHERE P_ID=?");
			myStmt.setString(1, p.getPetitionName());
			myStmt.setString(2, p.getDesciption());
			myStmt.setString(3, p.getPetitionBody());
			myStmt.setInt(4, p.getVotesNeeded());
			myStmt.setInt(5, p.getPetitionID());
			myStmt.executeUpdate();
			System.out.println("Update item" +p.getPetitionName());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();
			}
		}
		}
		return true;
	}
	//UPDATE UP VOTE========================================================================
	@Override
	public boolean updateUpVote(Petition p) {
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			
			PreparedStatement myStmt = conn.prepareStatement("UPDATE root.CTWPETITION SET UP_VOTES= UP_VOTES + 1 WHERE P_ID=?");
			myStmt.setInt(1, p.getPetitionID());
			myStmt.executeUpdate();
			System.out.println("Update votes on" +p.getPetitionName());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();
			}
		}
		}
		return true;
	}
	//UPDATE DOWN VOTE======================================================================
	@Override
	public boolean updateDownVote(Petition p) {
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			
			PreparedStatement myStmt = conn.prepareStatement("UPDATE root.CTWPETITION SET DOWN_VOTES= DOWN_VOTES + 1 WHERE P_ID=?");
			myStmt.setInt(1, p.getPetitionID());
			myStmt.executeUpdate();
			System.out.println("Update votes on" +p.getPetitionName());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();
			}
		}
		}
		return true;
	}
	//DELETE================================================================================
	@Override
	public boolean deletePetition(int ID) {
		String sql = "DELETE FROM root.CTWPETITION WHERE P_ID = " +ID+ "";
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Deleted item" +ID);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();
			}
		}
		}
		return true;
	}
	//FINDALL===============================================================================
	@Override
	public List<Petition> findAll() {
		//petitions array
		List<Petition> petitions = new ArrayList<Petition>();
		System.out.println("====================Test01====================\n\n");		
		String sql = "SELECT * FROM root.CTWPETITION";
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//Petition(String pN, String d, String pB, int uV, int vN, int dV)
				petitions.add(new Petition(rs.getInt("P_ID"),rs.getString("P_USER"),rs.getString("P_NAME"),rs.getString("P_DESCRIPTION"), rs.getString("P_BODY"), rs.getInt("UP_VOTES"),rs.getInt("VOTES_NEEDED"),rs.getInt("DOWN_VOTES")));
				System.out.println("Getting new petition "+rs.getString("P_NAME"));
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			System.out.println("******************* Failure connecting to db");
			e.printStackTrace();
		}	finally {
			if(conn !=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();}
			}
			if(conn !=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();}
			}
		}
		return petitions;
	}
	//FINDBYID==============================================================================
	@Override
	public Petition findById(String id) {
		Petition p = null;
		int petitionID = Integer.parseInt(id);
		String sql = "SELECT * FROM ROOT.CTWPETITION WHERE P_ID = " +petitionID+ "";
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("******************* Success connecting to db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//Petition(String pN, String d, String pB, int uV, int vN, int dV)
				p = new Petition(rs.getInt("P_ID"),rs.getString("P_USER"),rs.getString("P_NAME"),rs.getString("P_DESCRIPTION"), rs.getString("P_BODY"), rs.getInt("UP_VOTES"),rs.getInt("VOTES_NEEDED"),rs.getInt("DOWN_VOTES"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {conn.close();}
				catch(SQLException e) {e.printStackTrace();
			}
		}
		}
		return p;
	}

	@Override
	public List<Petition> findByString(String n) {
		//petitions array
				List<Petition> petitions = new ArrayList<Petition>();
				System.out.println("====================Test01====================\n\n");		
				String sql = "SELECT * FROM root.CTWPETITION WHERE P_NAME LIKE %" +n+ "% OR P_DESCRIPTION LIKE %" + n + "% OR P_BODY LIKE %"+n+"%";
				try {
					conn = DriverManager.getConnection(url, user, pass);
					System.out.println("******************* Success connecting to db");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
						//Petition(String pN, String d, String pB, int uV, int vN, int dV)
						petitions.add(new Petition(rs.getInt("P_ID"),rs.getString("P_USER"),rs.getString("P_NAME"),rs.getString("P_DESCRIPTION"), rs.getString("P_BODY"), rs.getInt("UP_VOTES"),rs.getInt("VOTES_NEEDED"),rs.getInt("DOWN_VOTES")));
						System.out.println("Getting new petition "+rs.getString("P_NAME"));
					}
					rs.close();
					stmt.close();
				}catch(SQLException e) {
					System.out.println("******************* Failure connecting to db");
					e.printStackTrace();
				}	finally {
					if(conn !=null) {
						try {conn.close();}
						catch(SQLException e) {e.printStackTrace();}
					}
					if(conn !=null) {
						try {conn.close();}
						catch(SQLException e) {e.printStackTrace();}
					}
				}
				return petitions;
			}
}
