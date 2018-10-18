package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photos/AddAlbum")
public class AddAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
	        response.setContentType("text/html");
	        
	        PrintWriter out = response.getWriter();
	        out.println("<!DOCTYPE html>");
	        out.println("<html lang=\"en\">");
	        
	        out.println("<head>");
	        out.println("    <meta charset=\"UTF-8\">");
	        out.println("    <title>Login</title>");
	        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class=\"container\">");
	        
	        // Page-Specific Content goes here
	        String name = request.getParameter("name");
	        String description = request.getParameter("description");
	        
	        if (name == null)
	        	name = "";
	        
	        description = description == null ? "" : description;
	        
	        out.println("<h1>Photo Albums</h1>");
	        out.println("<form action=\"AddAlbum\" method=\"post\">");
	        out.println(" 	Album Name: <input type=\"text\" name=\"name\" value=\"" + name + "\"> <br>");
	        
	        if (request.getAttribute("nameError") != null)
	        	out.println("   <p class=\"text-danger\">Please enter a name</p>");
	        
	        out.println(" 	Description: <br>");
	        out.println(" 	<textarea name=\"description\">" + description + "</textarea><br>");
	        
	        if (request.getAttribute("messageError") != null)
	        	out.println("   <p class=\"text-danger\">Please enter a description</p>");
	        
	        out.println(" 	<input type=\"submit\" name=\"submitBtn\" value=\"Add Album\">");
	        out.println("</form>");
	        out.println("</div>");
	        out.println("</body>");        
	        out.println("</html>");
	        
	        
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			ArrayList<AlbumEntry> albumEntries = (ArrayList<AlbumEntry>) getServletContext().getAttribute("albumEntries");
			
			// Add a new entry
			albumEntries.add(new AlbumEntry(name, description));
			
			// Redirect the User back to the main page
			response.sendRedirect("albums");
			
	    	doGet(request, response);
	    }

}