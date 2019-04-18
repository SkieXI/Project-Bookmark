package data;

import java.util.List;

import beans.Petition;
import beans.User;

public interface DataAccessInterface <T> {
	//petitions
	public boolean insertPetition(Petition p);
	public boolean update(Petition p);
	public boolean updateUpVote(Petition p);
	public boolean updateDownVote(Petition p);
	public boolean deletePetition(int ID);
	public List<Petition> findAll();
	public Petition findById(String id);
	public List<Petition> findByString(String n);
}
