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

import photos.PhotoEntry;

@WebServlet("/photos/Photos")
public class Photos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
		ArrayList<PhotoEntry> photoEntries = new ArrayList<PhotoEntry>();
		getServletContext().setAttribute("photoEntries", photoEntries);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
        
        out.println("<div class=\"card-deck\">");
        
        for(PhotoEntry entry : photoEntries) {
        	if(entry.getAlbumId() == Integer.parseInt(request.getParameter("albumId"))) {
	        	String path = getServletContext()
						.getRealPath( "/WEB-INF/uploads/" + entry.fileName);
	        	out.println("<div class=\"col-4 mb-2\">");
	        	out.println("<div class=\"card\">");
	        	out.println("	<img class=\"card-img-top\" style=\"height: 200px;\" src=\"" + path+ "\" alt=\"Card image\">");
	        	out.println("	<div class=\"card-body\">");
	        	out.println("			<a href=\"ViewPhoto?albumId=" + entry.getAlbumId() + "&id=" + entry.getId()+ "\">View</a>");
	        	out.println("			&nbsp;");
				out.println("			<a href=\"DeletePhoto?albumId=" + entry.getAlbumId() + "&id=" + entry.getId()+"\">Delete</a>");
				out.println("			&nbsp;");
				out.println("			<a href=\"DownloadPhoto?albumId=" + entry.getAlbumId() + "&photoId=" + entry.getId()+ "\">Download</a>");
				out.println("	</div>");
	        	out.println("</div>");
	        	out.println("</div>");
        	}
        }
       	out.println("</div>");
        PhotoEntry entry = new PhotoEntry();
    	out.println("			<form action=\"UploadPhoto\" method=\"post\" enctype=\"multipart/form-data\">");
    	out.println("				<input type=\"hidden\" name=\"albumId\" value=\"" + request.getParameter("albumId") + "\"> <br />");
    	out.println("				File: <input type=\"file\" name=\"id\" value=\"" + entry.getAlbumId() + "\"/><br> <br />");
    	out.println("				<input type=\"submit\" name=\"upload\" value=\"Upload\" />");
    	out.println("			</form>");
    	out.println("</div>");
	    out.println("</body>");        
	    out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
