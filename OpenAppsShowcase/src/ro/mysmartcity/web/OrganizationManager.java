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
import ro.mysmartcity.bean.Organization;
import ro.mysmartcity.business.OrganizationBean;

@Path("/organization")
public class OrganizationManager extends Manager<Organization> {

	final static String GET_BY_NAME = "/url/name={name}";

	@Override
	protected Class<? extends Base> getEntityClass() {
		return Organization.class;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(GET_BY_NAME)
	public String getByEmail(@PathParam("name") String name) throws Exception {

		final javax.naming.Context c = new javax.naming.InitialContext();
		OrganizationBean bean = (OrganizationBean) c.lookup(OrganizationBean.JNDI);
		Organization u = bean.getByName(name);

		if (u != null) {
			return super.buildLoadURL(u.getId());
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

		list.add(getMethodMap("get Organization by name", "GET", request.getRequestURL().toString().replace("/help", "") + GET_BY_NAME, false));

		return list;

	}
}
