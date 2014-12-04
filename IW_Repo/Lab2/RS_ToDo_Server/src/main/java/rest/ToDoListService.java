package rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import common.ToDoElement;
import common.ToDoList;

/**
 * A service that manipulates contacts in an address book.
 *
 */
@Path("/todo")
public class ToDoListService {

	/**
	 * The (shared) address book object. 
	 */
	@Inject
	ToDoList tdlist;
	
	/**
	 * A GET /contacts request should return the address book in JSON.
	 * @return a JSON representation of the address book.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ToDoList getToDoList() {
		return tdlist;
	}

	/**
	 * A POST /contacts request should add a new entry to the address book.
	 * @param info the URI information of the request
	 * @param person the posted entity
	 * @return a JSON representation of the new entry that should be available at /contacts/person/{id}.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addElem(@Context UriInfo info, ToDoElement elem) {
		elem.setId(getId());
		elem.setHref(info.getAbsolutePathBuilder().path("elem/{id}").build(elem.getId()));
		tdlist.getToDoList().add(elem);
		return Response.created(elem.getHref()).entity(elem).build();
	}

	/**
	 * A GET /contacts/person/{id} request should return a entry from the address book
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new entry or 404
	 */
	@GET
	@Path("/elem/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getElem(@PathParam("id") int id) {
		for (ToDoElement e : tdlist.getToDoList()) {
			if (e.getId() == id) {
				return Response.ok(e).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * A PUT /contacts/person/{id} should update a entry if exists
	 * @param info the URI information of the request
	 * @param person the posted entity
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new updated entry or 400 if the id is not a key
	 */
	@PUT
	@Path("/elem/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateElem(@Context UriInfo info,
			@PathParam("id") int id, ToDoElement elem) {
		for (int i = 0; i < tdlist.getToDoList().size(); i++) {
			if (tdlist.getToDoList().get(i).getId() == id) {
				elem.setId(id);
				elem.setHref(info.getAbsolutePath());
				tdlist.getToDoList().set(i, elem);
				return Response.ok(elem).build();
			}
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	/**
	 * A DELETE /contacts/person/{id} should delete a entry if exists
	 * @param id the unique identifier of a person
	 * @return 204 if the request is successful, 404 if the id is not a key
	 */
	@DELETE
	@Path("/elem/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") int id) {
		for (int i = 0; i < tdlist.getToDoList().size(); i++) {
			if (tdlist.getToDoList().get(i).getId() == id) {
				tdlist.getToDoList().remove(i);
				return Response.noContent().build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	private int getId(){
		int val = -1;
		int size = tdlist.getToDoList().size();
		if(size == 0){ // If list is empty -> Assign id 1
			val = 1;
		}
		else { // If list is NOT empty -> Assign id of last element + 1 
			val = tdlist.getElem(size - 1).getId() + 1;
		}
		return val;
//		return id++;
	}
}
