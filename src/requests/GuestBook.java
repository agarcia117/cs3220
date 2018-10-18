package requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GuestBookEntry;


@WebServlet(urlPatterns= {"/lab3/GuestBook"}, loadOnStartup=1)
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Create a local array list of guest book entries
		ArrayList<GuestBookEntry> guestbookEntries = new ArrayList<GuestBookEntry>();
		
		// Pre-populate the guest book with some entries
		guestbookEntries.add( new GuestBookEntry("John Doe", "Hello, World!"));
		guestbookEntries.add( new GuestBookEntry("Mary Jane", "Hi!"));
		guestbookEntries.add( new GuestBookEntry("Joe Boxer", "Howdy."));
		
		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("guestbookEntries", guestbookEntries);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Guest Book</title>");
        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        
        out.println("<h1>Guest Book</h1>");
        
        out.println("<form action=\"GuestBook\" method=\"get\">");
        String searchQuery = request.getParameter("searchQuery");
        if(searchQuery == null) {
        	searchQuery = "";
        }
        out.println("  <input type=\"text\" name=\"searchQuery\" value=\"" + searchQuery + "\">");
        out.println("  <select name=\"searchType\">");
        if(request.getParameter("searchType") == null || request.getParameter("searchType").equals("ID")) {
	        out.println("    <option>ID</option>");
	        out.println("    <option>Name</option>");
	        out.println("    <option>Message</option>");
	        out.println("    <option>All Text</option>");
        }
        else if(request.getParameter("searchType").equals("Name")) {
        	out.println("    <option>Name</option>");
        	out.println("    <option>ID</option>");        
	        out.println("    <option>Message</option>");
	        out.println("    <option>All Text</option>");
        }
        else if(request.getParameter("searchType").equals("Message")) {
        	out.println("    <option>Message</option>");
        	out.println("    <option>Name</option>");
        	out.println("    <option>ID</option>");        	       
	        out.println("    <option>All Text</option>");
        }
        else{
        	out.println("    <option>All Text</option>");
        	out.println("    <option>Name</option>");
        	out.println("    <option>ID</option>");        
	        out.println("    <option>Message</option>");
        }
        out.println("  </select>");
        out.println("  <input type=\"submit\" name=\"searchBtn\" value=\"Search\">");
        out.println("</form>");
        
        out.println("<hr>");
        
        out.println("<table class=\"table table-bordered table-striped table-hover\">");
        out.println("  <tr>");
        out.println("    <th>Name</th>");
        out.println("    <th>Message</th>");
        out.println("    <th>Actions</th>");
        out.println("  </tr>");
        
        // Get a reference to the guestbook
        ArrayList<GuestBookEntry> guestbookEntries 
        	= (ArrayList<GuestBookEntry>) getServletContext().getAttribute("guestbookEntries");
        
        // Was a search submitted?
        if (request.getParameter("searchBtn") != null) {
        	String searchType = request.getParameter("searchType");
        	
        	// If we are searching by ID, execute this code block
        	if (searchType.equals("ID")) {
        		// If searching by ID, the 'searchQuery' parameter should contain an integer
        		// So, we parse the integer out of the string
        		int id = Integer.parseInt(request.getParameter("searchQuery"));
        		
        		// Create a new array list to store our search results
        		ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
        		
        		// Iterate over EVERY guest book entry, and those that match the search criteria
        		// will be added to our search results array list
        		for (GuestBookEntry entry : guestbookEntries) {
        			if (entry.getId() == id) {
        				searchResults.add(entry);
        				break;
        			}
        		}
        		
        		// When done searching, the search results array list should contain
        		// all matches.
        		
        		// To display matches, we simply re-assign the 'guestbookEntries' reference variable
        		// to the new search results array list.
        		guestbookEntries = searchResults;
        	}
        	
        	if(searchType.contains("Name")) {
		        		
		        String name = request.getParameter("searchQuery");
		        		
		        ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
		        		
		        for (GuestBookEntry entry : guestbookEntries) {
		        	if (entry.getName().toLowerCase().contains(name.toLowerCase())) {
		        		searchResults.add(entry);
		        	}
		        }
		        guestbookEntries = searchResults;
		     }
        	
			if(searchType.contains("Message")) {
			        		
				String message = request.getParameter("searchQuery");
			        		
			        		// Create a new array list to store our search results
			    ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
			        		
			        		// Iterate over EVERY guest book entry, and those that match the search criteria
			        		// will be added to our search results array list
			    for (GuestBookEntry entry : guestbookEntries) {
			    	if (entry.getMessage().toLowerCase().contains(message.toLowerCase())) {
			    		searchResults.add(entry);
			        }
			    }
			    guestbookEntries = searchResults;
			 }
			
			if(searchType.contains("All Text")) {
        		
				String allText = request.getParameter("searchQuery");
			        		
			    ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
			        		
			    for (GuestBookEntry entry : guestbookEntries) {
			    	if (entry.getMessage().toLowerCase().contains(allText.toLowerCase())) {
			    		searchResults.add(entry);
			        }
			    	else if (entry.getName().toLowerCase().contains(allText.toLowerCase())) {
			    		searchResults.add(entry);
			        }
			    }
			    guestbookEntries = searchResults;
			 }
        }
        
        
        for (GuestBookEntry entry : guestbookEntries) {
        	out.println("  <tr>");
            out.println("    <td>" + entry.getName() + "</td>");
            out.println("    <td>" + entry.getMessage() + "</td>");
            out.println("    <td>");
            out.println(" <a href=\"EditComment?id=" + entry.getId() + "\">Edit</a> ");
            out.println(" | ");
            out.println(" <a href=\"DeleteComment?id=" + entry.getId() + "\">Delete</a>");
            out.println("    </td>");   
            out.println("  </tr>");
        }
        
        out.println("</table>");
        
        out.println("<a class=\"btn btn-primary\" href=\"AddComment\">Add Comment</a>");
        
        out.println("</div>");
        out.println("</body>");        
        out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}