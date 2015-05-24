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
import ro.mysmartcity.bean.Project;
import ro.mysmartcity.business.ProjectBean;

@Path("/project")
public class ProjectManager extends Manager<Project> {

	final static String GET_BY_EVENT_AND_NAME = "/url/eventURL={eventURL}/name={name}";

	@Override
	protected Class<? extends Base> getEntityClass() {
		return Project.class;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(GET_BY_EVENT_AND_NAME)
	public String getByEventAndName(@PathParam("eventURL") String eventURL, @PathParam("name") String name) throws Exception {

		final javax.naming.Context c = new javax.naming.InitialContext();
		ProjectBean ub = (ProjectBean) c.lookup(ProjectBean.JNDI);
		Project p = ub.getByNameAndEvent(name, eventURL);

		if (p != null) {
			return super.buildLoadURL(p.getId());
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

		list.add(getMethodMap("get Project by event and name", "GET",
				request.getRequestURL().toString().replace("/help", "") + GET_BY_EVENT_AND_NAME, false));

		return list;

	}
}
