package org.example;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/spot")
public class SpotController {
	@GET
	@Path("/")
	public Response getActiveIntances() {
		String response = "List all active instances...";
		return Response.status(Status.OK).entity(response).build();
	}
	
	@GET
	@Path("/{instanceId}")
	public Response getActiveIntanceById(@PathParam("instanceId") String instanceId) {
		String response = "Get information about the instance: " + instanceId;
		return Response.status(Status.OK).entity(response).build();
	}
	
	@POST
	@Path("/{instanceType}/{ami}")
	public Response createInstance(@PathParam("instanceType") String instanceType, @PathParam("ami") String ami) {
		String response = "Create ec2 instance with type: " + instanceType + " with ami: " + ami;
		return Response.status(Status.OK).entity(response).build();
	}
	
	@DELETE
	@Path("/{instanceId}")
	public Response deleteInstanceById(@PathParam("instanceId") String instanceId) {
		String response = "Delete instance: " + instanceId;
		return Response.status(Status.OK).entity(response).build();
	}
}
