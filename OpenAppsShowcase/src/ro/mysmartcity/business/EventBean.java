package ro.mysmartcity.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.Event;

@Stateless
public class EventBean extends BaseBean {

	public static final String JNDI = "java:global/restEJB/business/EventBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	public Event getByName(final String name) {

		Query query = manager.createQuery("FROM Event WHERE name = :name");
		query.setParameter("name", name);

		Event e = null;

		try {
			e = (Event) query.getSingleResult();
		} catch (Exception ex) {

		}
		return e;
	}

}
