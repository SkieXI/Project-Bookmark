package business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Petition;
import java.util.List;

@RequestScoped
@Path("/petitions")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PetitionsRestService {
	@Inject
	PetitionsBusinessInterface service;
	
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Petition> getPetitionsAsJson(){
		return service.getPetitions();
	}
	
	@GET
	@Path("/getjsonid/{petitionnumber}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Petition getPetitionAsJason(@PathParam("petitionnumber")String petitionnumber) {
		return service.findById(petitionnumber);
	}
	
	@GET
	@Path("/getjsonstring/{stg}/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Petition> getPetitionAsJasonString(@PathParam("stg")String stg) {
		return service.findByString(stg);
	}
	
	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public Petition[] getPetitionsAsXML() {
		List<Petition> petitions = service.getPetitions();
		return petitions.toArray(new Petition[petitions.size()]);
	}
}
