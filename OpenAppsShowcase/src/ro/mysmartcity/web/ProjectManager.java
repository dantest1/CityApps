package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.Project;

@Path("/project")
public class ProjectManager extends Manager<Project> {

	@Override
	protected Class<? extends Base> getEntityClass() {
		return Project.class;
	}

}
