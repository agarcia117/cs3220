package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photos.AlbumEntry;

@WebServlet("/photos/Album")
public class Album extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			
			// Created a local, empty array list of type Guest Book Entry
			ArrayList<AlbumEntry> albumEntries = new ArrayList<AlbumEntry>();
			
			// Pre-populate my guest book with some entries
			albumEntries.add( new AlbumEntry("John Doe", "Hello, World!"));
			albumEntries.add( new AlbumEntry("Mary Jane", "Hi!"));
			albumEntries.add( new AlbumEntry("Joe Boxer", "Howdy!"));
			
			// Add the array list to the application scope (Servlet Context)
			getServletContext().setAttribute("albumEntries", albumEntries);
			
		}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		 	ArrayList<AlbumEntry> albumEntries = (ArrayList<AlbumEntry>) getServletContext().getAttribute("albumEntries");
		 	
	        response.setContentType("text/html");
	        
	        PrintWriter out = response.getWriter();
	        out.println("<!DOCTYPE html>");
	        out.println("<html lang=\"en\">");
	        
	        out.println("<head>");
	        out.println("    <meta charset=\"UTF-8\">");
	        out.println("    <title>Main Page</title>");
	        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class=\"container\">");
	        
	        // Page-Specific Content goes here
	        out.println("<h1>Photo Albums</h1>");
	        
	        out.println("<form action=\"Album\" method=\"get\">");

			out.println("</form>");
			
			out.println("<div class=\"card-deck\">");
			for(AlbumEntry entry : albumEntries ) {
				out.println("<div class=\"card\">");
				out.println("<img class=\"card-img-top\" src=\"https://picsum.photos/300\" alt=\"Card image cap\">");
				out.println("<div class=\"card-body\">");
				out.println("<h5 class=\"card-title\">" + entry.getAlbumName() + "</h5>");
				out.println("<p class=\"card-text\">" + entry.getDescription() + "</p>");
				out.println("<a href=\"#\" class=\"card-link\">View Album</a>");
				out.println("<a href=\"DeleteAlbum?id=" + entry.getId() + "\" class=\"card-link\">Delete Album</a>");
				out.println("</div>");
				out.println("</div>");
			}
			out.println("</div>");
//			if(albumEntries.isEmpty()) {
//				out.println("<img src=\"smiley.gif\" alt=\"Smiley face\">");
//			}
			
			
			out.println("</div>");
		    out.println("</body>");        
		    out.println("</html>");
	    }
	 
	       
	        
	        
	    

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	doGet(request, response);
	    }
}