package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/Login")
public class Login extends HttpServlet {
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
	        	response.sendRedirect("../login/Members");
	        }
	        else{
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
	 	        out.println("<h1>Login</h1>");
	 	        out.println("<form action=\"Login\" method=\"post\">");
	 	        out.println("<div class=\"form-group\">");
	 	        out.println("	E-mail: <input type=\"email\" class=\"form-control\" name=\"email\"> <br>");
	 	        out.println("	Password: <input type=\"password\" class=\"form-control\" name=\"password\"> <br>");
	 	        out.println("</div>");
	 	        out.println("	<input type=\"submit\" href=\"members\" name=\"submitBtn\" value=\"Login\">");
	 	        out.println("</form>");
	 	        out.println("</div>");
	 	        out.println("</body>");        
	 	        out.println("</html>");
	        } 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("submitBtn") != null) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if (email.equals("acervan5@calstatela.edu") && password.equals("abcd")) {
				Cookie cookie = new Cookie("email", email);
				response.addCookie(cookie);
				response.sendRedirect("../login/Members");
			}
			else {
				response.sendRedirect("../login/Login");
			}
		}
	}

}