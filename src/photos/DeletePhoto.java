package photos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photos/DeletePhoto")
public class DeletePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry to be deleted
		int id = Integer.parseInt( request.getParameter("id"));
		int albumId = Integer.parseInt( request.getParameter("albumId"));
		
		// Get a reference to the guest book
		ArrayList<PhotoEntry> photoEntries = (ArrayList<PhotoEntry>) getServletContext().getAttribute("photoEntries");
				
		// Remove the element that matches the specified ID
		for (PhotoEntry entry : photoEntries) {
			if (entry.getId() == id && entry.getAlbumId() == albumId) {
				File file = new File(entry.getFileDir(), entry.fileName);
				file.delete();
				photoEntries.remove(entry);
				break;
			}
		}
		
		// Redirect the User back to the main page
		response.sendRedirect("albums");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}