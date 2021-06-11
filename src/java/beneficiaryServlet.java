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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String name=request.getParameter("name");
        
        String accno=request.getParameter("accno");
        HttpSession session1=request.getSession(false);
        String uname=(String)session1.getAttribute("username");
        
        
        String url="jdbc:mysql://localhost:3306/hms_db";
         String user="root";
        String password="";
        String Query="insert into benef(uname,name,accno) values('"+uname+"','"+name+"','"+accno+"')";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            if(stmt.executeUpdate(Query)>0)
            //request.getRequestDispatcher("login.html").include(request,response);
                out.println("<body style=\"background-image: url('images/bg.jpg');\" ><center><br><br><h3>Request for approving the benificiary has been sent. Come back later to make a transaction<br><a href=\"http://localhost:8084/hms/menu.html\">back</a></h3></center></body>");
            else 
                out.println("<body bgcolor='Magenta'><center><br><br><h3>Registration Failed<a href=\"http://localhost:8084/hms/register1.html\">Try again</a></h3></center></body>");
        }
        catch(ClassNotFoundException | SQLException e){}
        
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request,response);
    }
}