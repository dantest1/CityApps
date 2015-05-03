package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.OrganizationEntity;

@Path("/organizationentity")
public class OrganizationEntityManager extends Manager<OrganizationEntity> {

	@Override
	protected Class<? extends Base> getEntityClass() {
		return OrganizationEntity.class;
	}

}
