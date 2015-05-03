package ro.mysmartcity.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ro.mysmartcity.bean.Organization;

@Path("/organization")
public class OrganizationManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return Organization.class;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(@Context HttpServletRequest request, final @Valid Organization base) throws Exception {
		return super.update(request, base);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String insert(@Context HttpServletRequest request, final @Valid Organization base) throws Exception {
		return super.insert(request, base);
	}
}
