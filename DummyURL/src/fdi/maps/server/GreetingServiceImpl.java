package fdi.maps.server;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fdi.maps.client.GreetingService;
import fdi.maps.shared.ConstantsDummy;
import fdi.maps.shared.FieldVerifier;
import fdi.ucm.server.interconect.model.Parameter;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public List<String> getParams() {

		
		

        HashSet<String> ListaSer=new HashSet<String>();
        
		

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


		
		return new ArrayList<String>(ListaSer);
	}
}
