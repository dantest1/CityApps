package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Organization;

@Path("/organization")
public class OrganizationManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return Organization.class;
	}

}
