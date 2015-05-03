package ro.mysmartcity.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ro.mysmartcity.bean.Event;

@Path("/event")
public class EventManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return Event.class;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(@Context HttpServletRequest request, final @Valid Event base) throws Exception {
		return super.update(request, base);
	}

	@PUT
	@Path("/activate/{id}")
	public void activate(@PathParam("id") Long id) throws Exception {
		super.activate(Event.class, id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String insert(@Context HttpServletRequest request, final @Valid Event base) throws Exception {
		return super.insert(request, base);
	}

}
