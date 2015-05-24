package ro.mysmartcity.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.OrganizationEntity;
import ro.mysmartcity.business.OrganizationEntityBean;

@Path("/organizationentity")
public class OrganizationEntityManager extends Manager<OrganizationEntity> {

	final static String GET_BY_ORGANIZATION_AND_ENTITY = "/url/organizationURL={organizationURL}/entityURL={entityURL}/roleInEntity={roleInEntity}";

	@Override
	protected Class<? extends Base> getEntityClass() {
		return OrganizationEntity.class;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(GET_BY_ORGANIZATION_AND_ENTITY)
	public String getByEventAndName(@PathParam("organizationURL") String organizationURL, @PathParam("entityURL") String entityURL,
			@PathParam("roleInEntity") String roleInEntity) throws Exception {

		final javax.naming.Context c = new javax.naming.InitialContext();
		OrganizationEntityBean ub = (OrganizationEntityBean) c.lookup(OrganizationEntityBean.JNDI);
		OrganizationEntity oe = ub.getByOrganizationEntityRole(organizationURL, entityURL, roleInEntity);

		if (oe != null) {
			return super.buildLoadURL(oe.getId());
		} else {
			return "";
		}
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/help")
	public List<Map<String, Object>> help(@Context HttpServletRequest request) {
		List<Map<String, Object>> list = super.help(request);

		list.add(getMethodMap("get OrganizationEntity by organization and entity", "GET", request.getRequestURL().toString().replace("/help", "")
				+ GET_BY_ORGANIZATION_AND_ENTITY, false));

		return list;

	}
}
