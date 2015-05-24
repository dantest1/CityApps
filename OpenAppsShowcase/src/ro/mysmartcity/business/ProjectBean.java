package ro.mysmartcity.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.Project;

@Stateless
public class ProjectBean extends BaseBean {

	public static final String JNDI = "java:global/restEJB/business/ProjectBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	public Project getByNameAndEvent(final String name, final String eventURL) {

		Query query = manager.createQuery("FROM Project WHERE name = :name AND eventURL = :eventURL");
		query.setParameter("name", name);
		query.setParameter("eventURL", eventURL);

		Project u = null;

		try {
			u = (Project) query.getSingleResult();
		} catch (Exception e) {

		}
		return u;
	}

}
