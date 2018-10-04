package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/Members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String getEmail( HttpServletRequest request )
    {
        Cookie[] cookies = request.getCookies();
        if( cookies != null )
            for( Cookie cookie : cookies )
                if( cookie.getName().equals( "email" ) )
                    return cookie.getValue();

        return null;
    }
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
	        response.setContentType("text/html");
	        
	        PrintWriter out = response.getWriter();
	       
	        
	        if(getEmail(request) != null) {
	        	out.println("<!DOCTYPE html>");
	 	        out.println("<html lang=\"en\">");
	        	out.println("<head>");
	   	        out.println("    <meta charset=\"UTF-8\">");
	   	        out.println("    <title>Members</title>");
	   	        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
	   	        out.println("</head>");
	   	        out.println("<body>");
	   	        out.println("<div class=\"container\">");
	   	        
	   	        // Page-Specific Content goes here
	   	        out.println("<p> Welcome! </p>");
	   	        out.println(getEmail(request));
	   	        out.println("</div>");
	   	        out.println("</body>");        
	   	        out.println("</html>");
	   	        
	        }
	        else {
	        	response.sendRedirect("../login/Login");
	        }  
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}