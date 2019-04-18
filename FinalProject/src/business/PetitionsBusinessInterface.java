package business;

import java.util.List;

import javax.ejb.Local;

import beans.Petition;

@Local
public interface PetitionsBusinessInterface 
{
	public List<Petition> getPetitions();
	public void deletePetition(int ID);
	public void processUpdate(Petition petition);
	public Petition findById(String id);
	public void insertPetition(Petition p);
	public List<Petition> findAll();
	public boolean updateUpVote(Petition p);
	public boolean updateDownVote(Petition p);
	public List<Petition> findByString(String n);
	public boolean update(Petition p);
}
