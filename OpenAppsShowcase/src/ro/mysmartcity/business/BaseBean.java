package ro.mysmartcity.business;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.IsQueryParam;

/*
 * Base class for all managers 
 */
@Stateless
public class BaseBean {

	public static final String JNDI = "java:global/restEJB/business/BaseBean";

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	enum PARAM {
		limit, page
	}

	protected static final int QUERY_MAX_LIMIT = 100;
	protected static final int QUERY_FIRST_RESULT = 0;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Object> getAllIDs(final Class<?> objectClass, final Map<String, String> parameters) {

		String sql = getQuery(parameters, objectClass);
		sql = "SELECT id " + sql;
		Query query = buildQuery(parameters, manager, sql);
		@SuppressWarnings("unchecked")
		List<Object> ids = query.getResultList();

		return ids;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Base get(final Class<?> objectClass, final Object id) {

		return (Base) manager.find(objectClass, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(final Class<?> objectClass, final Object id) {

		manager.remove(manager.find(objectClass, id));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(final Base base) {

		manager.merge(base);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Base insert(final Base base) {

		manager.persist(base);
		return base;
	}

	protected int getQueryLimit(final Map<String, String> parameters) {

		if (exist(parameters, PARAM.limit.name())) {
			String param = parameters.get(PARAM.limit.name());
			if (isValidInteger(param)) {
				Integer count = Integer.valueOf(param);
				if (count <= 100) {
					return count;
				}
			}
		}

		return QUERY_MAX_LIMIT;
	}

	protected int getQueryFirst(final Map<String, String> parameters) {

		if (exist(parameters, PARAM.page.name())) {
			String param = parameters.get(PARAM.page.name());
			if (isValidInteger(param)) {
				Integer page = Integer.valueOf(param);
				return page * getQueryLimit(parameters);
			}
		}

		return QUERY_FIRST_RESULT;
	}

	protected Query buildQuery(final Map<String, String> parameters, final EntityManager manager, final String sql) {
		Query query = manager.createQuery(sql);
		query.setFirstResult(getQueryFirst(parameters));
		query.setMaxResults(getQueryLimit(parameters));

		return query;
	}

	protected String getQuery(final Map<String, String> parameters, final Class<?> entityClass) {

		String query = "FROM " + entityClass.getSimpleName();
		boolean isFirst = true;
		for (Field field : entityClass.getDeclaredFields()) {

			if (field.isAnnotationPresent(IsQueryParam.class)) {

				if (exist(parameters, field.getName())) {
					String param = parameters.get(field.getName());
					if (isValidString(param)) {

						if (isFirst) {
							query = query + " WHERE " + field.getName() + " LIKE '" + param + "'";
							isFirst = false;
						} else {
							query = query + " AND " + field.getName() + " LIKE '" + param + "'";
						}
					}

				}
			}

		}

		return query;
	}

	boolean exist(final Map<String, String> parameters, final String paramName) {
		return parameters != null && parameters.get(paramName) != null;
	}

	boolean isValidInteger(final String number) {
		try {
			Integer.valueOf(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	boolean isValidString(final String value) {

		if (value != null && !value.trim().equals("")) {
			return true;
		}

		return false;

	}

}
