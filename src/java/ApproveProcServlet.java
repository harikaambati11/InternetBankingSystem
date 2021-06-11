/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApproveProcServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        //HttpSession session1=request.getSession(false);
        //String cname=(String)session1.getAttribute("collegename");
      
        String[] names = request.getParameterValues("accounts");
        List list = Arrays.asList(names);
        Iterator<String> itr = list.iterator();
        
        String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";
        String st="Yes";
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            while (itr.hasNext()){
            
		if (stmt.executeUpdate("Update benef set verified='Yes' where  accno='"+itr.next()+"'")>0) 
                {
                    out.println("<body style=\"background-image: url('images/bg.jpg');\" ><center><br><br><h3>Approved<br><br><a href=\"http://localhost:8084/hms/menu1.html\">back</a></h3></center></body>");
                    //out.println("<body bgcolor='coral'><center><br><br><h3><br><a href=\"http://localhost:8084/hms/menu.html\">Return</a></h3></center></body>");
                    //out.println("<body style=\"background-image: url('images/bg.jpg');><center><br><br><h3><br><a href=\"http://localhost:8084/hms/login.html\">Logout</a></h3></center></body>");
                }
                else
                {
                    out.println("Already approved ");  
		}
            }
            
        }
        catch(Exception ex){
                
            
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        doGet(request,response);
    }

}