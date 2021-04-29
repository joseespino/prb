package api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import api.service.Service;

@Path("/asteroids")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldRest {

	@Inject
	private Service service;

	@GET
	public ResponseBuilder get(@QueryParam("planet") String planet) throws Exception {
		if(planet != null)
			return Response.ok(Service.get(planet));
		else 
			return Response.ok("Campo planet no informado.");
	}
	
    @GET  
    public Response sayHello() {     
        return Response.ok("Hello World desde el API REST",MediaType.APPLICATION_JSON).build();   
    } 

}