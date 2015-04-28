package ro.mysmartcity.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class MyApplication extends Application {

	static Set<Class<?>> classes = new HashSet<Class<?>>();
	static List<String> urls = new ArrayList<String>();

	static {
		classes.add(ro.mysmartcity.web.DefaultManager.class);
		classes.add(ro.mysmartcity.web.EventManager.class);
		classes.add(ro.mysmartcity.web.UserManager.class);
		classes.add(ro.mysmartcity.web.ProjectManager.class);
		classes.add(ro.mysmartcity.web.OrganizationManager.class);
		classes.add(ro.mysmartcity.web.UserEntityManager.class);
		classes.add(ro.mysmartcity.web.OrganizationEntityManager.class);

		for (Class<?> c : classes) {
			urls.add(c.getAnnotation(Path.class).value());
		}
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return new HashSet<Object>();
	}
}
