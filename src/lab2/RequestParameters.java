package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/lab2/RequestParameters"}, loadOnStartup=1)
public class RequestParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
	        response.setContentType("text/html");
	        
	        PrintWriter out = response.getWriter();
	        out.println("<!DOCTYPE html>");
	        out.println("<html lang=\"en\">");
	        
	        out.println("<head>");
	        out.println("    <meta charset=\"UTF-8\">");
	        out.println("    <title>Document</title>");
	        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class=\"container\">");
	        
	        // Page-Specific Content goes here
	        
	        out.println("    Request Method:");
	        out.println("    " + request.getMethod());
	        
	        Date d = new Date();
	        out.println("    <br>Date and Time:  ");
	        out.println("    " + d);
	        
	        out.println("<table class=\"table table-bordered table-striped table-hover\">");
	        
	        out.println("  <tr>");
	        out.println("    <th>Parameter</th>");
	        out.println("    <th>Value</th>");
	        out.println("  </tr>");   
	        
	        Enumeration<String> parameters = request.getParameterNames();
	        while (parameters.hasMoreElements()) {
				String parameterName = parameters.nextElement();
				
				out.println("  <tr>");
				out.print("<td>" + parameterName + "</td>");	
				String[] parameterValue = request.getParameterValues(parameterName);
				out.println("<td>");
				for(int i = 0; i < parameterValue.length; i++) {
					out.print(parameterValue[i] + "  ");
				}
				out.println("</td>");
				out.println("  </tr>");
			}
	        
	        out.println("  </table>");
	        
	        out.println("<table class=\"table table-bordered table-striped table-hover\">");
	        out.println("  <tr>");
	        out.println("    <th>Header</th>");
	        out.println("    <th>Value</th>");
	        out.println("  </tr>");
	              
	        Enumeration<String> headers = request.getHeaderNames();
			while (headers.hasMoreElements()) {
				String headerName = headers.nextElement();
				out.println("  <tr>");
				out.print("<td>" + headerName + "</td>");
				String headerValue = request.getHeader(headerName);
				out.print("<td>" + headerValue + "</td>");
				out.println("  </tr>");
			}
	        
			out.println("<br>");
			
	        out.println("  </table>");
	        out.println("</div>");
	        out.println("</body>");        
	        out.println("</html>");   
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}
