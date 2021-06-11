/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class beneficiaryServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String name=request.getParameter("name");
        
        String accno=request.getParameter("accno");
        HttpSession session1=request.getSession(false);
        String uname=(String)session1.getAttribute("username");
        
        
        String url="jdbc:mysql://localhost:3306/hms_db";
         String user="hms";
        String password="7LpC7Hoqn2wBYiCC";
        String Query="insert into benef(uname,name,accno) values('"+uname+"','"+name+"','"+accno+"')";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            if(stmt.executeUpdate(Query)>0)
            //request.getRequestDispatcher("login.html").include(request,response);
                out.println("<body bgcolor='Magenta'><center><br><br><h3>Added successfully<br><a href=\"http://localhost:8084/hms/balance.html\">Login now</a></h3></center></body>");
            else 
                out.println("<body bgcolor='Magenta'><center><br><br><h3>Registration Failed<a href=\"http://localhost:8084/hms/register1.html\">Try again</a></h3></center></body>");
        }
        catch(Exception e){}
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request,response);
    }
}