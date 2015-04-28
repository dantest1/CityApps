package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Project;

@Path("/project")
public class ProjectManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return Project.class;
	}

}
