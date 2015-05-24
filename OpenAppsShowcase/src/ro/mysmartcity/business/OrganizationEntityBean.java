package ro.mysmartcity.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.OrganizationEntity;

@Stateless
public class OrganizationEntityBean extends BaseBean {

	public static final String JNDI = "java:global/restEJB/business/OrganizationEntityBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	public OrganizationEntity getByOrganizationEntityRole(final String organization, final String entity, final String roleInEntity) {

		Query query = manager
				.createQuery("FROM OrganizationEntity WHERE organizationURL = :organizationURL AND entityURL = :entityURL AND roleInEntity = :roleInEntity");
		query.setParameter("organizationURL", organization);
		query.setParameter("entityURL", entity);
		query.setParameter("roleInEntity", roleInEntity);

		OrganizationEntity e = null;

		try {
			e = (OrganizationEntity) query.getSingleResult();
		} catch (Exception ex) {

		}
		return e;
	}

}
