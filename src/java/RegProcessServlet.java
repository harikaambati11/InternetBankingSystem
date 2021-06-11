import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegProcessServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String amt=request.getParameter("amt");
        String accno=request.getParameter("accno");
        String uname=request.getParameter("uname");
        String pwd=request.getParameter("pwd");
        
        String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";
        String Query="insert into accts values('"+name+"','"+email+"','"+amt+"','"+accno+"', '"+uname+"','"+pwd+"')";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            if(stmt.executeUpdate(Query)>0)
            //request.getRequestDispatcher("login.html").include(request,response);
                out.println("<body  style=\"background-image: url('images/bg.jpg');\"bgcolor='Magenta'><center><br><br><h3>Registration successful<br><a href=\"http://localhost:8084/hms/login.html\">Login now</a></h3></center></body>");
            else 
                out.println("<body bgcolor='Magenta'><center><br><br><h3>Registration Failed<a href=\"http://localhost:8084//register.html\">Try again</a></h3></center></body>");
        }
        catch(Exception e){out.println("catch");}
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request,response);
    }
}
        
