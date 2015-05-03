package ro.mysmartcity.web;

import javax.ws.rs.Path;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.Event;

@Path("/event")
public class EventManager extends Manager<Event> {

	@Override
	protected Class<? extends Base> getEntityClass() {
		return Event.class;
	}

}
