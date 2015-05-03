package ro.mysmartcity.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {

		Map<String, String> response = new HashMap<String, String>();
		response.put("errorMessage", e.getMessage());
		return Response.serverError().entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
