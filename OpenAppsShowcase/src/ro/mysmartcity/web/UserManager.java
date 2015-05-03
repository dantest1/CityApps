package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.User;

@Path("/user")
public class UserManager extends Manager<User> {

	@Override
	protected Class<? extends Base> getEntityClass() {
		return User.class;
	}
}
