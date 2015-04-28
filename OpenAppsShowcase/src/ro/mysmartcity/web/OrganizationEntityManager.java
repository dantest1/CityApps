package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.OrganizationEntity;

@Path("/organizationentity")
public class OrganizationEntityManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return OrganizationEntity.class;
	}

}
