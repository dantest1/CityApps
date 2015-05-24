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
import ro.mysmartcity.bean.UserEntity;
import ro.mysmartcity.business.UserEntityBean;

@Path("/userentity")
public class UserEntityManager extends Manager<UserEntity> {

	final static String GET_BY_USER_AND_ENTITY = "/url/userURL={userURL}/entityURL={entityURL}/roleInEntity={roleInEntity}";

	@Override
	protected Class<? extends Base> getEntityClass() {
		return UserEntity.class;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(GET_BY_USER_AND_ENTITY)
	public String getByEventAndName(@PathParam("userURL") String userURL, @PathParam("entityURL") String entityURL,
			@PathParam("roleInEntity") String roleInEntity) throws Exception {

		final javax.naming.Context c = new javax.naming.InitialContext();
		UserEntityBean ub = (UserEntityBean) c.lookup(UserEntityBean.JNDI);
		UserEntity ue = ub.getByUserEntityRole(userURL, entityURL, roleInEntity);

		if (ue != null) {
			return super.buildLoadURL(ue.getId());
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

		list.add(getMethodMap("get UserEntity by user and entity", "GET", request.getRequestURL().toString().replace("/help", "")
				+ GET_BY_USER_AND_ENTITY, false));

		return list;

	}
}
