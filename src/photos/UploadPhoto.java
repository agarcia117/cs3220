package photos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.Out;

import photos.PhotoEntry;

@WebServlet("/photos/UploadPhoto")
public class UploadPhoto extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // Redirect the user to the upload form
        response.sendRedirect( "../Upload.html" );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig()
            .getServletContext();
        File repository = (File) servletContext
            .getAttribute( "javax.servlet.context.tempdir" );
        
        factory.setRepository( repository );

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload( factory );

        // Count how many files are uploaded
        int count = 0;
        
        // The directory we want to save the uploaded files to.
        String fileDir = getServletContext().getRealPath( "/WEB-INF/uploads" );
        System.out.println(fileDir);
        // Parse the request
        int value = -1;
        try
        {
            List<FileItem> items = upload.parseRequest( request );
            
            for( FileItem item : items ) {
            	if(item.isFormField()) {
            		if(item.getFieldName().equals("albumId")) {
            			value = Integer.parseInt(item.getString());
            		}
            	}
            }
            for( FileItem item : items )
            {
                // If the item is not a form field - meaning it's an uploaded
                // file, we save it to the target dir
                if( !item.isFormField() )
                {
                    // item.getName() will return the full path of the uploaded
                    // file, e.g. "C:/My Documents/files/test.txt", but we only
                    // want the file name part, which is why we first create a
                    // File object, then use File.getName() to get the file
                    // name.
                	// /var/usr/some/temp/dir/some-file.jpg
                	// /user/albert/3220/WEB-INF/uploads   some-file.jpg
                	
                	//item.getContentType();
                	
                    String fileName = (new File( item.getName() )).getName();
                    File file = new File( fileDir, fileName );
                    item.write( file );
                    ++count;
                    
                    //new Photo(filename, path, contentType)
                    
                    
                    // Add the photo to a collection
                    // Get the content type from the item:  item.getContentType()
                    String contentType = item.getContentType();
                    ArrayList<PhotoEntry> photoEntries = (ArrayList<PhotoEntry>) getServletContext().getAttribute("photoEntries");
                    photoEntries.add(new PhotoEntry(value, fileName, fileDir));
                }
                else {
                	String parameterName = item.getFieldName();
                	if (parameterName.equals("id")) {
                		int id = Integer.parseInt( item.toString() );
                	}
                }
            }

        }
        catch( Exception e )
        {
            throw new IOException( e );
        }
        response.sendRedirect( "../photos/albums" );
    }

}