package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Petition;
import beans.User;

import business.PetitionsBusinessInterface;
import data.UserData;

@ManagedBean
@ViewScoped
public class FormController 
{
	@Inject
	PetitionsBusinessInterface service;
	
	@Inject
	UserData usd;
	
	
	public String onSubmit()
	{
		// Get the User Managed Bean
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Forward to Test Response View along with the User Managed Bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}
	
	public PetitionsBusinessInterface getService()
	{
		return service;
	}
	public String onInsert(Petition p) {
		//for test send some message to the console.
		System.out.print("This is a message from the Page Controller class. You clicked the onInsert button for item "+p.getPetitionName());
		
		service.insertPetition(p);
		
		//send the user back login form
		return "HomePage.xhtml";
	}
	public String onDelete(Petition p) {
		//for test send some message to the console.
		System.out.print("This is a message from the Page Controller class. You clicked the onDelete button for item "+p.getPetitionName());
		
		//call the delete method in the OrderBusinessInterface
		service.deletePetition(p.getPetitionID());
		
		//send the user back login form
		return "HomePage.xhtml";
	}
	public String onShowEdit(Petition petition) {
		//for test send message to console
				FacesContext context = FacesContext.getCurrentInstance();
				User user = context.getApplication().evaluateExpressionGet(context, "#{user}",User.class);
				System.out.println("Form Controller test 1 " + user.getUserName());
				System.out.println("This is a message from the Page Controller class. You clicked the showEdit button for item "+petition.getPetitionID());
				if(usd.isAdmin(user)==true) {
					
					//service.processUpdate(petition);
					FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("petition", petition);
					return "EditPetition.xhtml";
				}
				else {
					return "Homepage.xhtml";
				}
	}
	public String onEdit(Petition petition) {
		FacesContext context = FacesContext.getCurrentInstance();
		petition = context.getApplication().evaluateExpressionGet(context, "#{petition}", Petition.class);
		service.update(petition);
		return "HomePage.xhtml";
		
	}
	public String AdminEdit(Petition petition)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}",User.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("petition", petition);
		return "HomePage.xhtml";
	}
	public String registerSubmit()
	{
		System.out.println("\n\n===========RegisterSubmit===========\n\n");
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		usd.create(user);
		
		System.out.println(user);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		usd.findUser(user);
		if(usd.findUser(user) != null) 
		{
			
			System.out.println("Form Controller test 2 " + user.getUserName());
			//usd.findAllUsers();
			service.findAll();
			return "HomePage.xhtml";
			
		}else {
			System.out.println("Form Controller test 3");
			return "Register.xhtml";
		}
	}
	public String loginSubmit()
	{
				FacesContext context = FacesContext.getCurrentInstance();
				User user = context.getApplication().evaluateExpressionGet(context, "#{user}",User.class);
				System.out.println("Form Controller test 1 " + user.getUserName());
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				
				if(usd.findUser(user) != null) 
				{
					
					if (user.getAdmin() == 0)
					{
						System.out.println("Form Controller test 2 " + user.getAdmin());
						service.findAll();
						return "AdminPage.xhtml";
					}
					else
					{
					System.out.println("Form Controller test 2.5ish " + user.getAdmin());
					service.findAll();
					return "HomePage.xhtml";
					}
				}
					else {
					System.out.println("Form Controller test ");
					return "Login.xhtml";
				}
				
	}

	public UserData usd()
	{
		return usd;
	}
	public String upVote(Petition p) {
		System.out.print("This is a message from the Page Controller class. You clicked the upVote button for item "+p.getPetitionName());
		
		service.updateUpVote(p);
		
		//send the user back login form
		return "HomePage.xhtml";
	}
	public String downVote(Petition p) {
		System.out.print("This is a message from the Page Controller class. You clicked the upVote button for item "+p.getPetitionName());
		
		service.updateDownVote(p);
		
		//send the user back login form
		return "HomePage.xhtml";
	}
	public String upVoteAdmin(Petition p) {
		System.out.print("This is a message from the Page Controller class. You clicked the upVote button for item "+p.getPetitionName());
		
		service.updateUpVote(p);
		
		//send the user back login form
		return "AdminPage.xhtml";
	}
	public String downVoteAdmin(Petition p) {
		System.out.print("This is a message from the Page Controller class. You clicked the upVote button for item "+p.getPetitionName());
		
		service.updateDownVote(p);
		
		//send the user back login form
		return "AdminPage.xhtml";
	}
	
	
	public String Search(String search)
	{
		service.findByString(search);
		return "Search.xhtml";
	}
	public String viewPetition(){
		System.out.println("Test from ViewPet! Form Controller");
		return "ViewPet.xhtml";
	}
}
