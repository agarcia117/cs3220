package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab3.GuestBookEntry;
import photos.AlbumEntry;

@WebServlet("/Album")
public class Album extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
//			out.println("  <input type=\"text\" name=\"searchQuery\">");
//			out.println("  <select name=\"searchType\">");
//			out.println("    <option>ID</option>");
//			out.println("    <option>Name</option>");
//			out.println("    <option>Message</option>");
//			out.println("  </select>");
//			out.println("  <input type=\"submit\" name=\"searchBtn\" value=\"Search\">");
			out.println("</form>");
			
			// Display a table of all guest book entries
			out.println("<table class=\"table table-bordered table-striped table-hover\">");
			out.println("  <tr>");
			out.println("    <th>Name</th>");
			out.println("    <th>Description</th>");
			out.println("    <th>Actions</th>");
			out.println("  </tr>");
			
			for( AlbumEntry entry : albumEntries ) {
				out.println("<tr>");
				out.println("  <td>" + entry.getAlbumName() + "</td>");
				out.println("  <td>" + entry.getDescription() + "</td>");
				out.println("  <td>");
				out.println("       <a href=\"EditComment?id=" + entry.getId() + "\">View</a>");
				out.println("       | ");
				out.println("       <a href=\"DeleteComment?id=" + entry.getId() + "\">Delete Album</a>");
				out.println("  </td>");
				out.println("</tr>");

			}
			
			out.println("</table>");
	    }
	 
	        out.println("</div>");
	        out.println("</body>");        
	        out.println("</html>");
	        
	        
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	

}
