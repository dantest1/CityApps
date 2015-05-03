package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.Organization;

@Path("/organization")
public class OrganizationManager extends Manager<Organization> {

	@Override
	protected Class<? extends Base> getEntityClass() {
		return Organization.class;
	}

}
