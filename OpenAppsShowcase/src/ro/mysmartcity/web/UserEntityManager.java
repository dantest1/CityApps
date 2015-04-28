package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.UserEntity;

@Path("/userentity")
public class UserEntityManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return UserEntity.class;
	}

}
