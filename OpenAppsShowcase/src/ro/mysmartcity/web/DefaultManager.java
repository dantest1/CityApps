package ro.mysmartcity.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@SuppressWarnings("rawtypes")
@Path("/")
public class DefaultManager extends Manager {

	@GET
	@Produces(value = { "application/json" })
	public List<String> urls(@Context HttpServletRequest request) throws Exception {

		List<String> urls = new ArrayList<String>();
		// String url = request.getRequestURL().toString();

		for (String path : MyApplication.urls) {
			// urls.add(buildLoadURL(url, path));

			System.out.println(path);
			System.out.println(buildLoadURL(path));
			urls.add(buildLoadURL(path));
		}

		return urls;
	}

	@Override
	protected Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
