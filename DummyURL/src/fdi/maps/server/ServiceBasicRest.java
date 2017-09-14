package fdi.maps.server;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@Path("Basic")
public class ServiceBasicRest  extends RemoteServiceServlet {


	private static final long serialVersionUID = -5608185730468273983L;
	
	@Context
	UriInfo uri;
	
	@Context 
	ServletContext context;
	

		//http://localhost:8080/Clavy/rest/Basic/HolaMundo
		@Path("HolaMundo")
		@GET
		public Response doGreet() {
			/**
			 * OK 
  			 * 200 OK, see HTTP/1.1 documentation.
			 */
			return Response.status(200)
					.entity("Hola Mundo")
					.build();
		}
		
		//http://localhost:8080/Clavy/rest/Basic/active
				@Path("active")
				@GET
				public Response active() {
					/**
					 * OK 
		  			 * 200 OK, see HTTP/1.1 documentation.
					 */
					return Response.status(200)
							.entity("Activo")
							.build();
				}
		
				
	
}
