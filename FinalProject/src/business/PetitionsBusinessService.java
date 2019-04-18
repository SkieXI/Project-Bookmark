package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Petition;
import data.PetitionsDataService;

/**
 * Session Bean implementation class TestBusinessService
 */
@Stateless
@Local(PetitionsBusinessInterface.class)
@Alternative
public class PetitionsBusinessService implements PetitionsBusinessInterface 
{
	List<Petition> petitions = new ArrayList<Petition>();
	@EJB
	PetitionsDataService service;
	public PetitionsBusinessService() 
    {
		petitions.add(new Petition(0, "Kelvin", "Kelvin is better then Celecius", "Measuing in Celecius is not accurate, we demand Kelvin", "Kelvin is a much more reliabale way to measure tempurature and it is approved by science!.", 5, 10000, 20));
    }

	public List<Petition> getPetitions()
	{
		return service.findAll();
	}

	@Override
	public void deletePetition(int ID) {
		// TODO Auto-generated method stub
		service.deletePetition(ID);
	}

	@Override
	public void processUpdate(Petition petition) {
		// TODO Auto-generated method stub
		System.out.println("Petition business service processUpdate function working...");
		System.out.println("Going to update item "+petition.getPetitionID());
		service.update(petition);
	}

	@Override
	public Petition findById(String petitionnumber) {
		// TODO Auto-generated method stub
		return service.findById(petitionnumber);
	}

	@Override
	public void insertPetition(Petition p) {
		// TODO Auto-generated method stub
		System.out.println("Petition business service Insert function working...");
		System.out.println("Going to insert item "+p.getPetitionID());
		service.insertPetition(p);
	}

	@Override
	public List<Petition> findAll() {
		return service.findAll();
	}
	
	@Override
	public boolean updateUpVote(Petition p) {
		return service.updateUpVote(p);
	}

	@Override
	public boolean updateDownVote(Petition p) {
		return service.updateDownVote(p);
	}
	public List<Petition> findByString(String n){
		System.out.println("Calling all petitions made with name:"+n);
		return service.findByString(n);
	}
	
	public boolean update(Petition p) {
		System.out.println("Updating existing petiton.");
		return service.update(p);
	}

}
