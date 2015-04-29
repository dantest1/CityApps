package ro.mysmartcity.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.business.BaseBean;

public abstract class Manager {

	protected abstract Class<?> getEntityClass();

	@GET
	@Produces(value = { "application/json" })
	public List<String> get(@Context HttpServletRequest request) throws Exception {

		final List<Object> list = getBean(BaseBean.JNDI).getAllIDs(getEntityClass(), getMap(request));
		final List<String> urls = new ArrayList<String>();
		final String url = request.getRequestURL().toString();

		for (final Object id : list) {
			urls.add(buildLoadURL(url, id));
		}

		return urls;
	}

	@GET
	@Produces(value = { "application/json" })
	@Path("/{id}")
	public Base get(@PathParam("id") Long id) throws Exception {

		return getBean(BaseBean.JNDI).get(getEntityClass(), id);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) throws Exception {

		getBean(BaseBean.JNDI).delete(getEntityClass(), id);
	}

	public String update(@Context HttpServletRequest request, final Base base) throws Exception {

		getBean(BaseBean.JNDI).update(base);
		return buildLoadURL(request, base.getId());
	}

	public String insert(@Context HttpServletRequest request, final Base base) throws Exception {

		final Base b = getBean(BaseBean.JNDI).insert(base);
		return buildLoadURL(request, b.getId());
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

	protected String buildLoadURL(String url, Object end) {

		String endPart = end + "";
		if (endPart.startsWith("/")) {
			endPart = endPart.substring(1);
		}

		return url + (url.charAt(url.length() - 1) == '/' ? "" : "/") + endPart;
	}

	protected String buildLoadURL(HttpServletRequest request, Object id) {
		final String url = request.getRequestURL().toString();
		return buildLoadURL(url, id);
	}

}
