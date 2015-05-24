package ro.mysmartcity.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.User;

@Stateless
public class UserBean extends BaseBean {

	public static final String JNDI = "java:global/restEJB/business/UserBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	public User get(final String email) {

		Query query = manager.createQuery("FROM User WHERE email = :email");
		query.setParameter("email", email);

		User u = null;

		try {
			u = (User) query.getSingleResult();
		} catch (Exception e) {

		}
		return u;
	}

}
