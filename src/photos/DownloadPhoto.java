package photos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ViewImage?albumId=1&photoId=3

@WebServlet(urlPatterns = {"/photos/DownloadPhoto"})
public class DownloadPhoto extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    String path = "";
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);	
		ArrayList<PhotoEntry> photoEntries = new ArrayList<PhotoEntry>();
		getServletContext().setAttribute("photoEntries", photoEntries);
	}
       

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
    	ArrayList<PhotoEntry> photoEntries = (ArrayList<PhotoEntry>) getServletContext().getAttribute("photoEntries");
    	
    	int photoId = Integer.parseInt(request.getParameter("photoId"));
    	int albumId = Integer.parseInt(request.getParameter("albumId"));
//    	
//    	models.Photo photo = getPhoto(albumId, photoId);
    	
        // Get the path to the file and create a java.io.File object
    	for(PhotoEntry entry : photoEntries) {
        	if(entry.getAlbumId() == albumId && entry.getId() == photoId) {
        		path = getServletContext()
    					.getRealPath( "/WEB-INF/uploads/" + entry.getFileName());
        	}
    	}
        File file = new File( path );

        // Set the response headers. File.length() returns the size of the file
        // as a long, which we need to convert to a String.
        response.setContentType( "image/jpg" );
        response.setHeader( "Content-Length", "" + file.length() );
        response.setHeader( "Content-Disposition",
            "attachment; filename=" + file.getName() );

        // Binary files need to read/written in bytes.
        FileInputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
    }

}



