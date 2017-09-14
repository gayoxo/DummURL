package fdi.maps.server;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fdi.maps.shared.ConstantsDummy;
import fdi.ucm.server.interconect.model.Interconect;
import fdi.ucm.server.interconect.model.Parameter;



@Path("Param")
public class ServiceParamRest  extends RemoteServiceServlet {


	private static final long serialVersionUID = -5608185730468273983L;

	
	@Context
	UriInfo uri;
	
	@Context 
	ServletContext context;
	

		//http://localhost:8080/DummyURL/rest/Param/HolaMundo
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
		
		//http://localhost:8080/DummyURL/rest/Param/active
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
		
				
				
				//http://localhost:8080/DummyURL/rest/Param/getParam
				@Path("getParam")
				@GET
				public String getParam() {
					
					
					
					String[] route = getServletContext().getRealPath("").split(Pattern.quote(File.separator));
			        StringBuffer uploadFolderSb = new StringBuffer(); 
			        //uploadFolderSb.append(File.separator);
			        for (int i = 0; i < route.length - 2; i++) {
						uploadFolderSb.append(route[i]);
						uploadFolderSb.append(File.separator);
					}
			        
			        uploadFolderSb.append("docroot");
			        uploadFolderSb.append(File.separator + "Dummy");
			        uploadFolderSb.append(File.separator);
					
			        
			        HashSet<String> ListaSer=new HashSet<String>();
			        
			        
			        String Path = uploadFolderSb.toString();
					
			        File F=new File(Path+"DummyParams.xml");
			        F.mkdirs();
			        
			        try {
			        	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			        	  DocumentBuilder db = dbf.newDocumentBuilder();
			        	  Document doc = db.parse(F);
			        	  doc.getDocumentElement().normalize();
			        //	  System.out.println("Clavy? " + doc.getDocumentElement().getNodeName());
			        	  NodeList nodeLst = doc.getElementsByTagName("para");
			       // 	  System.out.println("Information of all Editor");
			        	  for (int s = 0; s < nodeLst.getLength(); s++) {
			        		  Node fstNode = nodeLst.item(s);
			        		  ListaSer.add(fstNode.getFirstChild().getNodeValue());
			           	  }
			        	  
					} catch (Exception e) {
						throw new RuntimeException("No tiene editor o los elementos son incorrectos");
					}

					
					
					
					
					
					
					Interconect IT=new Interconect();

					String pathS="http://"+uri.getBaseUri().getHost()+":"+uri.getBaseUri().getPort()+context.getContextPath();
					
					IT.setIcon(pathS+"/"+ConstantsDummy.ICONPATH);
					IT.setName(ConstantsDummy.DUMMY);
					IT.setURLEdicion(pathS+"?"+ConstantsDummy.EDIT+"=true&");
					IT.setURLVisual(pathS+"?"+ConstantsDummy.EDIT+"=false&");
					
					List<Parameter> list=new ArrayList<Parameter>();
					
					for (String parameter : ListaSer) {
						list.add(new Parameter(ConstantsDummy.DUMMY,parameter));
					}
					
					
					
					IT.setParametros(list);
					
					
					
					Gson G=new Gson();
					String Salida = G.toJson(IT);
					return Salida;
					

				}
				
				
				
}
