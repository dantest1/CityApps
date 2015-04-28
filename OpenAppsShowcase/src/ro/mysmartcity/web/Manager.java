package ro.mysmartcity.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

		List<Object> list = getBean(BaseBean.JNDI).getAllIDs(getEntityClass(), getMap(request));
		List<String> urls = new ArrayList<String>();
		String url = request.getRequestURL().toString();

		for (Object id : list) {
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

	@PUT
	@Consumes(value = { "application/json" })
	public String update(@Context HttpServletRequest request, final Base base) throws Exception {

		getBean(BaseBean.JNDI).update(base);
		return buildLoadURL(request, base.getId());
	}

	@POST
	@Consumes(value = { "application/json" })
	public String insert(@Context HttpServletRequest request, final Base base) throws Exception {

		Base b = getBean(BaseBean.JNDI).insert(base);
		return buildLoadURL(request, b.getId());
	}

	protected BaseBean getBean(final String jndi) throws Exception {
		javax.naming.Context c = new javax.naming.InitialContext();
		return (BaseBean) c.lookup(jndi);
	}

	protected Map<String, String> getMap(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		@SuppressWarnings("rawtypes")
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			String value = request.getParameter(key);
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
		String url = request.getRequestURL().toString();
		return buildLoadURL(url, id);
	}

}
