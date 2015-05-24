package ro.mysmartcity.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.UserEntity;

@Stateless
public class UserEntityBean extends BaseBean {

	public static final String JNDI = "java:global/restEJB/business/UserEntityBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	public UserEntity getByUserEntityRole(final String user, final String entity, final String roleInEntity) {

		Query query = manager.createQuery("FROM UserEntity WHERE userURL = :userURL AND entityURL = :entityURL AND roleInEntity = :roleInEntity");
		query.setParameter("userURL", user);
		query.setParameter("entityURL", entity);
		query.setParameter("roleInEntity", roleInEntity);

		UserEntity u = null;

		try {
			u = (UserEntity) query.getSingleResult();
		} catch (Exception e) {

		}
		return u;
	}

}
