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
import ro.mysmartcity.bean.User;
import ro.mysmartcity.business.UserBean;

@Path("/user")
public class UserManager extends Manager<User> {

	final static String GET_BY_EMAIL = "/url/email={email}";

	@Override
	protected Class<? extends Base> getEntityClass() {
		return User.class;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(GET_BY_EMAIL)
	public String getByEmail(@PathParam("email") String email) throws Exception {

		final javax.naming.Context c = new javax.naming.InitialContext();
		UserBean ub = (UserBean) c.lookup(UserBean.JNDI);
		User u = ub.get(email);

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

		list.add(getMethodMap("getUser by email address", "GET", request.getRequestURL().toString().replace("/help", "") + GET_BY_EMAIL, false));

		return list;

	}
}
