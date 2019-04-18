package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import business.PetitionsBusinessInterface;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Petitions 
{
	@Inject
	PetitionsBusinessInterface service;

	List<Petition> petitions = new ArrayList<Petition>();
	
	@PostConstruct
	public void init() {
		petitions = service.getPetitions();
	}
	public List<Petition> getPetitions() 
	{
		return petitions;
	}

	public void setPetitions(List<Petition> petitions) 
	{
		this.petitions = petitions;
	}
}
