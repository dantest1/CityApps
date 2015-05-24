package ro.mysmartcity.web;

import java.util.TimeZone;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import ro.mysmartcity.bean.Base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

	private final ObjectMapper objectMapper;

	public JacksonConfig() throws Exception {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(Base.DATE_TIME_FORMAT);
		objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest"));
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}
}
