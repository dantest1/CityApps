package ro.mysmartcity.web;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.Description;
import ro.mysmartcity.bean.IsQueryParam;
import ro.mysmartcity.business.BaseBean;

public abstract class Manager<T extends Base> {

	final static String SERVER_URL = "http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com";

	protected abstract Class<? extends Base> getEntityClass();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/url")
	public List<String> getIDs(@Context HttpServletRequest request) throws Exception {

		final List<Object> list = getBean(BaseBean.JNDI).getAllIDs(getEntityClass(), getMap(request));
		final List<String> urls = new ArrayList<String>();

		for (final Object id : list) {
			urls.add(buildLoadURL(id));
		}

		return urls;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<?> getAll(@Context HttpServletRequest request) throws Exception {

		return getBean(BaseBean.JNDI).getAll(getEntityClass(), getMap(request));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Base get(@PathParam("id") Long id) throws Exception {

		return getBean(BaseBean.JNDI).get(getEntityClass(), id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/help")
	public List<Map<String, Object>> help(@Context HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String getDescription = BaseBean.QUERY_MAX_LIMIT
				+ " entities. Accepted query parameters are: 'limit' (number of returned entities, not more than "
				+ BaseBean.QUERY_MAX_LIMIT
				+ "), 'page' (pagination), 'sortBy' (sort by specified field, which must be declared as filerable) and 'sortOrder' (ASC or DESC) and all entity fields on which filter is accepted.";

		list.add(getMethodMap("get urls of first " + getDescription, "GET", request.getRequestURL().toString().replace("/help", "") + "/url", false));
		list.add(getMethodMap("get first " + getDescription, "GET", request.getRequestURL().toString().replace("/help", ""), false));

		list.add(getMethodMap("get entity with specified id", "GET", request.getRequestURL().toString().replace("/help", "") + "/{id}", false));
		list.add(getMethodMap("delete entity with specified id", "DELETE", request.getRequestURL().toString().replace("/help", "") + "/{id}", true));
		list.add(getMethodMap("add new entity", "POST", request.getRequestURL().toString().replace("/help", ""), true));
		list.add(getMethodMap("update existing entity", "PUT", request.getRequestURL().toString().replace("/help", ""), true));

		if (Base.hasActivate(getEntityClass())) {
			list.add(getMethodMap("activate an existing entity", "PUT", request.getRequestURL().toString().replace("/help", "/activate/{id}"), true));
		}

		List<Map<String, Object>> fields = new ArrayList<Map<String, Object>>();

		for (final Field field : getEntityClass().getDeclaredFields()) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", field.getName());
			map.put("type", field.getType().getSimpleName());
			if (field.isAnnotationPresent(IsQueryParam.class)) {
				map.put("filterable", true);
				if (field.getType().isAssignableFrom(Date.class)) {
					map.put("FROM filter parameter", field.getName() + "From");
					map.put("TO filter Parameter", field.getName() + "To");
					map.put("dateTimePattern", Base.DATE_TIME_PATTERN);
				}
			}

			if (field.isAnnotationPresent(NotNull.class)) {
				map.put("isMandatory", true);
			}
			if (field.isAnnotationPresent(Description.class)) {
				map.put("description", field.getAnnotation(Description.class).description());
			}
			fields.add(map);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if (getEntityClass().isAnnotationPresent(Description.class)) {
			map.put("entityDescription", getEntityClass().getAnnotation(Description.class).description());
		}
		map.put("entityName", getEntityClass().getSimpleName());
		map.put("entityFields", fields);
		map.put("STATUS options: ", Arrays.asList(Base.STATUS.values()));
		map.put("LICENSE options: ", Arrays.asList(Base.LICENSE.values()));
		map.put("STATE options: ", Arrays.asList(Base.STATE.values()));
		list.add(map);

		return list;
	}

	Map<String, Object> getMethodMap(final String description, final String method, final String url, final boolean isSecured) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("description", description);
		map.put("method", method);
		map.put("isSecured", isSecured);

		return map;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) throws Exception {

		getBean(BaseBean.JNDI).delete(getEntityClass(), id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(final @Valid T base) throws Exception {
		getBean(BaseBean.JNDI).update(base);
		// return buildLoadURL(request, base.getId());
		return buildLoadURL(base.getId());
	}

	@PUT
	@Path("/activate/{id}")
	public void activate(@PathParam("id") Long id) throws Exception {
		getBean(BaseBean.JNDI).activate(getEntityClass(), id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String insert(final @Valid T base) throws Exception {
		final Base b = getBean(BaseBean.JNDI).insert(base);
		// return buildLoadURL(request, b.getId());
		return buildLoadURL(b.getId());
	}

	protected BaseBean getBean(final String jndi) throws Exception {
		final javax.naming.Context c = new javax.naming.InitialContext();
		return (BaseBean) c.lookup(jndi);
	}

	protected Map<String, String> getMap(HttpServletRequest request) {

		final Map<String, String> map = new HashMap<String, String>();

		@SuppressWarnings("rawtypes")
		final Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			final String key = (String) enumeration.nextElement();
			final String value = request.getParameter(key);
			map.put(key, value);
		}

		return map;
	}

	protected String buildLoadURL(Object end) {

		String url = SERVER_URL + (this.getEntityClass() != null ? this.getClass().getAnnotation(Path.class).value() : "");

		String endPart = end + "";
		if (endPart.startsWith("/")) {
			endPart = endPart.substring(1);
		}

		String finalURL = url + (url.charAt(url.length() - 1) == '/' ? "" : "/") + endPart;

		return finalURL;
	}

	// protected String buildLoadURL(String url, Object end) {
	//
	// String endPart = end + "";
	// if (endPart.startsWith("/")) {
	// endPart = endPart.substring(1);
	// }
	//
	// return url + (url.charAt(url.length() - 1) == '/' ? "" : "/") + endPart;
	// }

	// protected String buildLoadURL(HttpServletRequest request, Object id) {
	// final String url = request.getRequestURL().toString();
	// return buildLoadURL(url, id);
	// }

}
