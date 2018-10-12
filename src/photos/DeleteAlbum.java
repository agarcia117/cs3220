package photos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photos/DeleteAlbum")
public class DeleteAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry to be deleted
		int id = Integer.parseInt( request.getParameter("id"));
		
		// Get a reference to the guest book
		ArrayList<AlbumEntry> albumEntries = (ArrayList<AlbumEntry>) getServletContext().getAttribute("albumEntries");
				
		// Remove the element that matches the specified ID
		for (AlbumEntry entry : albumEntries) {
			if (entry.getId() == id) {
				albumEntries.remove(entry);
				break;
			}
		}
		
		// Redirect the User back to the main page
		response.sendRedirect("Album");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}