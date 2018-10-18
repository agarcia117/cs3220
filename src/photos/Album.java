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

@WebServlet("/photos/albums")
public class Album extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
		String path = "";
		int count = 0;
	
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			
			// Created a local, empty array list of type Guest Book Entry
			ArrayList<AlbumEntry> albumEntries = new ArrayList<AlbumEntry>();
			ArrayList<PhotoEntry> photoEntries = new ArrayList<PhotoEntry>();
			
			// Pre-populate my album book with some entries
			albumEntries.add( new AlbumEntry("Alan's Album", "Test the description"));
			albumEntries.add( new AlbumEntry("Ryan's Album", "Hi!"));
			
			
			// Add the array list to the application scope (Servlet Context)
			getServletContext().setAttribute("albumEntries", albumEntries);
			getServletContext().setAttribute("photoEntries", photoEntries);
			
		}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		 	ArrayList<AlbumEntry> albumEntries = (ArrayList<AlbumEntry>) getServletContext().getAttribute("albumEntries");
		 	ArrayList<PhotoEntry> photoEntries = (ArrayList<PhotoEntry>) getServletContext().getAttribute("photoEntries");
		 	
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
	        out.println("You have " + albumEntries.size() + " albums");
			out.println("<div class=\"card-deck\">");
			
			for(AlbumEntry entry : albumEntries ) {
				count = 0;
				for(PhotoEntry entrys : photoEntries ) {
					if(entrys.getAlbumId() == entry.getId()) {
						path = getServletContext()
		    					.getRealPath( "/WEB-INF/uploads/" + photoEntries.get(count).getFileName());
						break;
					}
					count++;
				}
				out.println("<div class=\"col-4\">");
				out.println("	<div class=\"card mt-2\">");
				if(path == "") {
					path = "https://www.livemeshthemes.com/enigmatic/wp-content/uploads/sites/9/2012/07/placeholder1.jpg";
				}
				out.println("		<img class=\"card-img-top\" style=\"height: 200px;\" src=\"" + path + "\"alt=\"Card image cap\">");
				path = "";
				out.println("		<div class=\"card-body\">");
				out.println("			<h5 class=\"card-title\">" + entry.getAlbumName() + "</h5>");
				out.println("			<p class=\"card-text\">" + entry.getDescription() + "</p>");
				out.println("			<a href=\"Photos?albumId=" + entry.getId() + "\" class=\"card-link\">View Album</a>");
				out.println("			<a href=\"DeleteAlbum?Id=" + entry.getId() + "\" class=\"card-link\">Delete Album</a>");
				out.println("		</div>");
				out.println("	</div>");
				out.println("</div>");
				count++;
			}
			out.println("<div class=\"col-4\">");
			out.println("	<div class=\"card mt-2\">");
			String pathGray = getServletContext()
					.getRealPath( "/WEB-INF/uploads/blank.jpg");
			out.println("		<img class=\"card-img-top\" style=\"height: 200px;\" src=\"https://www.livemeshthemes.com/enigmatic/wp-content/uploads/sites/9/2012/07/placeholder1.jpg\" alt=\"Card image cap\">");
			out.println("		<div class=\"card-body\">");
			out.println("			<h5 class=\"card-title\">Sample Album Title</h5>");
			out.println("			<p class=\"card-text\">Sample Description</p>");
			out.println("			<a class=\"btn btn-primary\" href=\"AddAlbum\">Add Album</a>");
			out.println("		</div>");
			out.println("	</div>");
			out.println("</div>");
			out.println("</div>");
			
			out.println("</div>");
		    out.println("</body>");        
		    out.println("</html>");
	    }
	 
	       
	        
	        
	    

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	doGet(request, response);
	    }
}