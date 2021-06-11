/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginProcessServlet1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
   
        String uname=request.getParameter("uname");
        String pwd=request.getParameter("pwd");
        
        String url="jdbc:mysql://localhost:3306/hms_db";
         String user="root";
        String password="";
        String Query=" select `name`,`pwd` from `eaccts` where `uname`='"+uname+"'";
        
       try
       {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(Query);
    
            while(rs.next())
            {
                
                if(rs.getString(2).equals(pwd))
                {
                //out.println("<body bgcolor='Magenta'><center><br><br><h3>Welcome "+rs.getString(1)+"</h3></center></body>");
                   // ServletContext sc = this.getServletContext();
                  // RequestDispatcher rd = sc.getRequestDispatcher("Apply.jsp");
                  // rd.include(request, response);
               
                request.getRequestDispatcher("menu1.html").include(request,response);
                request.getSession().setAttribute("username",uname);
                
                
                }   
                else
                {
                out.println("<body bgcolor='Magenta'><center><br><br><h3>Invalid Credentials<br><a href=\"http://localhost:8084/hms/login1.html\">Try again</a></h3></center></body>");
                //request.getRequestDispatcher("login.html").include(request, response);
                }
            
                
            } 
       } 
       catch(Exception e)
       {
                out.println("Unauthorized access");
       }
    }
}