package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Event;

@Path("/event")
public class EventManager extends Manager {

	@Override
	protected Class<?> getEntityClass() {
		return Event.class;
	}

}
