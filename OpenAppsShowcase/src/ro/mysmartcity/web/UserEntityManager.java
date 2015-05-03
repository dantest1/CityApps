package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.UserEntity;

@Path("/userentity")
public class UserEntityManager extends Manager<UserEntity> {

	@Override
	protected Class<? extends Base> getEntityClass() {
		return UserEntity.class;
	}

}
