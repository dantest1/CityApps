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

	enum PARAM {
		limit, page
	}

	public static final String JNDI = "java:global/restEJB/business/BaseBean";

	protected static final int QUERY_MAX_LIMIT = 100;

	protected static final int QUERY_FIRST_RESULT = 0;
	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	protected Query buildQuery(final Class<?> entityClass, final Map<String, String> parameters, final EntityManager manager) {
		String sql = getQuery(parameters, entityClass);
		sql = "SELECT id " + sql;
		final Query query = manager.createQuery(sql);

		populateQueryParameters(entityClass, parameters, query);

		query.setFirstResult(getQueryFirst(parameters));
		query.setMaxResults(getQueryLimit(parameters));

		return query;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(final Class<?> objectClass, final Object id) {

		manager.remove(manager.find(objectClass, id));
	}

	boolean exist(final Map<String, String> parameters, final String paramName) {
		return parameters != null && parameters.get(paramName) != null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Base get(final Class<?> objectClass, final Object id) {

		return (Base) manager.find(objectClass, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Object> getAllIDs(final Class<?> entityClass, final Map<String, String> parameters) {

		final Query query = buildQuery(entityClass, parameters, manager);
		@SuppressWarnings("unchecked")
		final List<Object> ids = query.getResultList();

		return ids;
	}

	private void populateQueryParameters(final Class<?> entityClass, final Map<String, String> parameters, Query query) {
		for (final Field field : entityClass.getDeclaredFields()) {

			if (field.isAnnotationPresent(IsQueryParam.class)) {

				if (exist(parameters, field.getName())) {
					final String param = parameters.get(field.getName());
					if (isValidString(param)) {

						if (field.getType().isAssignableFrom(String.class)) {
							query.setParameter(field.getName(), param);
						} else if (field.isEnumConstant()) {
							query.setParameter(field.getName(), param);
						}
					}
				}
			}

		}
	}

	protected String getQuery(final Map<String, String> parameters, final Class<?> entityClass) {

		final StringBuffer query = new StringBuffer("FROM ");
		query.append(entityClass.getSimpleName());

		boolean isFirst = true;
		for (final Field field : entityClass.getDeclaredFields()) {

			if (field.isAnnotationPresent(IsQueryParam.class)) {

				if (exist(parameters, field.getName())) {
					final String param = parameters.get(field.getName());
					if (isValidString(param)) {

						if (isFirst) {
							query.append(" WHERE ");
							isFirst = false;
						} else {
							query.append(" AND ");
						}

						query.append(field.getName()).append(" LIKE ").append(":").append(field.getName());
					}
				}
			}

		}

		// TODO if exist field status, than add status = ACTIVE to query

		return query.toString();
	}

	protected int getQueryFirst(final Map<String, String> parameters) {

		if (exist(parameters, PARAM.page.name())) {
			final String param = parameters.get(PARAM.page.name());
			if (isValidInteger(param)) {
				final Integer page = Integer.valueOf(param);
				return page * getQueryLimit(parameters);
			}
		}

		return QUERY_FIRST_RESULT;
	}

	protected int getQueryLimit(final Map<String, String> parameters) {

		if (exist(parameters, PARAM.limit.name())) {
			final String param = parameters.get(PARAM.limit.name());
			if (isValidInteger(param)) {
				final Integer count = Integer.valueOf(param);
				if (count <= 100) {
					return count;
				}
			}
		}

		return QUERY_MAX_LIMIT;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Base insert(final Base base) {

		manager.persist(base);
		return base;
	}

	boolean isValidInteger(final String number) {
		try {
			Integer.valueOf(number);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	boolean isValidString(final String value) {

		if (value != null && !value.trim().equals("")) {
			return true;
		}

		return false;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(final Base base) {

		manager.merge(base);
	}

}
