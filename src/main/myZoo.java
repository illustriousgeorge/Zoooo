import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import java.util.HashMap;

@Path("animals")
@Produces(MediaType.APPLICATION_JSON)
public class myZoo {

	static HashMap<Integer, HashMap<String, String>> animals = new HashMap<>();
	static Integer index = 0;

	@GET
	public Response getAnimals(){
		return Response.ok(animals).build();
	}

	@GET
	@Path("{index}")
	public Response getAnimal(@PathParam("index") Integer index) {
		if(index != null && animals.get(index) != null){
			return Response.ok(animals.get(index)).build();
		} else {
			return Response.ok("Not Found").build();
		}

	}

	@POST
	public Response createAnimal(@FormParam("name") String name, @FormParam("age") String age) {
		if(name != null && age != null ){
			HashMap<String,String> newAnimal = new HashMap<>();
			newAnimal.put("name", name);
			newAnimal.put("age", age);
			animals.put(index, newAnimal);
			index++;
			return Response.ok(animals).build();
		} else {
			return Response.ok("Give Animal Info").build();
		}

	}

	@PUT
	@Path("{index}")
	public Response updateAnimal(@PathParam("index") Integer index,@FormParam("name") String name ,@FormParam("age") String age) {
		if(animals.get(index) != null && index != null && name != null && age != null){
			HashMap<String,String> newAnimal = new HashMap<>();
			newAnimal.put("name", name);
			newAnimal.put("age", age);
			animals.put(index, newAnimal);
		}
		return Response.ok(animals).build();
	}

	@DELETE
	@Path("{index}")
	public Response removeAnimal(@PathParam("index") Integer index) {
		if(index != null){
			animals.remove(index);
		}
		return Response.ok(animals).build();
	}

}