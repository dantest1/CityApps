package ro.mysmartcity.business;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.Base.STATUS;
import ro.mysmartcity.bean.IsQueryParam;
import ro.mysmartcity.util.StringHelper;

@Stateless
public class BaseBean {

	enum PARAM {
		limit, page, sortBy, sortOrder
	}

	public static final String JNDI = "java:global/restEJB/business/BaseBean";

	public static final int QUERY_MAX_LIMIT = 100;
	protected static final int QUERY_FIRST_RESULT = 0;

	@PersistenceContext(unitName = "jpa")
	private EntityManager manager;

	protected Query buildQuery(final Class<?> entityClass, final Map<String, String> parameters, final String prefix) {
		String sql = getQuery(parameters, entityClass);

		if (StringHelper.isValid(prefix)) {
			sql = prefix + sql;
		}
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

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Object> getAllIDs(final Class<? extends Base> entityClass, final Map<String, String> parameters) {

		return buildQuery(entityClass, parameters, "SELECT id ").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<? extends Base> getAll(final Class<? extends Base> entityClass, final Map<String, String> parameters) {

		return buildQuery(entityClass, parameters, null).getResultList();
	}

	private void populateQueryParameters(final Class<?> entityClass, final Map<String, String> parameters, Query query) {
		for (final Field field : entityClass.getDeclaredFields()) {

			if (field.isAnnotationPresent(IsQueryParam.class)) {

				if (field.getType().isAssignableFrom(Date.class)) {
					if (field.getType().isAssignableFrom(Date.class)) {

						String fieldName = field.getName() + "From";
						Date dateFrom = getDateParameter(parameters, fieldName);
						if (dateFrom != null) {
							query.setParameter(fieldName, dateFrom);
						}

						fieldName = field.getName() + "To";
						Date dateTo = getDateParameter(parameters, fieldName);
						if (dateTo != null) {
							query.setParameter(fieldName, dateTo);
						}

					}
				} else if (exist(parameters, field.getName())) {
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

		Boolean isFirst = true;
		for (final Field field : entityClass.getDeclaredFields()) {

			if (field.isAnnotationPresent(IsQueryParam.class)) {

				if (field.getType().isAssignableFrom(Date.class)) {

					String fieldName = field.getName() + "From";
					if (getDateParameter(parameters, fieldName) != null) {
						isFirst = appendWhereAnd(isFirst, query);
						query.append(field.getName()).append(" >= ").append(":").append(fieldName);
					}

					fieldName = field.getName() + "To";
					if (getDateParameter(parameters, fieldName) != null) {
						isFirst = appendWhereAnd(isFirst, query);
						query.append(field.getName()).append(" <= ").append(":").append(fieldName);
					}

				} else if (exist(parameters, field.getName())) {
					final String param = parameters.get(field.getName());
					if (isValidString(param)) {

						isFirst = appendWhereAnd(isFirst, query);
						query.append(field.getName()).append(" LIKE ").append(":").append(field.getName());
					}
				}
			}
		}

		// TODO add this filter just when user is not platform admin
		// status must be active
		try {
			entityClass.getDeclaredField("status");
			isFirst = appendWhereAnd(isFirst, query);
			query.append("status='" + Base.STATUS.ACTIVE.name() + "'");
		} catch (final Exception e) {
			// eat it
		}

		if (parameters.containsKey(PARAM.sortBy.name())) {
			query.append(" ORDER BY ").append(parameters.get(PARAM.sortBy.name())); //TODO accept just filterable fields for performance reasons

			if (parameters.containsKey(PARAM.sortOrder.name())) {
				if ("DESC".equals(parameters.get(PARAM.sortOrder.name()).toUpperCase())) {
					query.append(" DESC");
				} else {
					query.append(" ASC");
				}

			}
		}
		return query.toString();
	}

	private Date getDateParameter(final Map<String, String> parameters, final String fieldName) {

		try {
			final String param = parameters.get(fieldName);

			Date dateFrom = Base.DATE_TIME_FORMAT.parse(param);
			return dateFrom;
		} catch (Exception e) {
			return null;
		}

	}

	private boolean appendWhereAnd(boolean isFirst, final StringBuffer query) {
		query.append(isFirst ? " WHERE " : " AND ");
		return false;
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void activate(final Class<?> entity, final Long id) throws NoSuchMethodException {

		try {
			Method m = entity.getDeclaredMethod("setStatus", STATUS.class);
			Base base = get(entity, id);

			if (base == null) {
				throw new IllegalArgumentException("Entity with id: " + id + " doesn't exist! Activate is not possible.");
			}

			m.invoke(base, STATUS.ACTIVE);
			update(base);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Entity with id: " + id + " doesn't exist! Activate is not possible.");
		} catch (Exception e) {
			throw new NoSuchMethodException("Activate not supported on Class: " + entity.getName());
		}

	}
}
