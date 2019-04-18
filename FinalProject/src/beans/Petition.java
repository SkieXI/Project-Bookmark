package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean
@ViewScoped
@XmlRootElement(name="Petition")
public class Petition 
{
	//insert what will go inside a petition
	// Variables
	public int petitionID;
	public String petitionUser;
	@NotNull(message = "Please enter a petition name. This is a required field.")
	@Size(min=3, max=50, message = "Petition Name need to be min 3, max 50 characters. ")
	public String petitionName;
	@NotNull(message = "Please enter a petition description. This is a required field.")
	@Size(min=7, max=255, message = "Petition Description need to be min 7, max 255 characters. ")
	public String desciption;
	@NotNull(message = "Please enter text for the body. This is a required field.")
	@Size(min=15, max=1020, message = "Petition Body need to be min 15, max 1020 characters. ")
	public String petitionBody;
	public int votesNeeded;
	public int upVotes = 0;
	public int downVotes = 0;
	//Constructors
	public Petition(int ID, String user, String pN, String d, String pB, int uV, int vN, int dV) {
		this.petitionID = ID;
		this.petitionUser = user;
		this.petitionName = pN;
		this.desciption = d;
		this.petitionBody = pB;
		this.votesNeeded = vN;
		this.upVotes = uV;
		this.downVotes = dV;
	}
	public Petition() {
		this.petitionUser = "Defualt User";
		this.petitionName = "Test Petition";
		this.desciption = "This is a test descirption of a normal petition.";
		this.petitionBody =  "Please join the petition!";
		this.upVotes = 0;
		this.votesNeeded = 0;
		this.downVotes = 0;	
	}
	//Getters and Setters
	public int getPetitionID() {
		return petitionID;
	}
	public void setPetitionID(int petitionID) {
		this.petitionID = petitionID;
	}
	public String getPetitionUser() {
		return petitionUser;
	}
	public void setPetitionUser(String petitionUser) {
		this.petitionUser = petitionUser;
	}
	public String getPetitionName() {
		return petitionName;
	}
	public void setPetitionName(String petitionName) {
		this.petitionName = petitionName;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getPetitionBody() {
		return petitionBody;
	}
	public void setPetitionBody(String petitionBody) {
		this.petitionBody = petitionBody;
	}
	public int getVotesNeeded() {
		return votesNeeded;
	}
	public void setVotesNeeded(int votesNeeded) {
		this.votesNeeded = votesNeeded;
	}
	public int getUpVotes() {
		return upVotes;
	}
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	public int getDownVotes() {
		return downVotes;
	}
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}
	
}
