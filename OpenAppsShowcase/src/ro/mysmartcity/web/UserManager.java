package ro.mysmartcity.web;

import javax.ws.rs.Path;
import javax.xml.registry.infomodel.User;

@Path("/user")
public class UserManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return User.class;
	}

}
