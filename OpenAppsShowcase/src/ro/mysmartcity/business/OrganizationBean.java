package ro.mysmartcity.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.Organization;

@Stateless
public class OrganizationBean extends BaseBean {

	public static final String JNDI = "java:global/restEJB/business/OrganizationBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	public Organization getByName(final String name) {

		Query query = manager.createQuery("FROM Organization WHERE name = :name");
		query.setParameter("name", name);

		Organization e = null;

		try {
			e = (Organization) query.getSingleResult();
		} catch (Exception ex) {

		}
		return e;
	}

}
