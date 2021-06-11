import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TransferProcServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
         HttpSession session1=request.getSession(false);
        String uname=(String)session1.getAttribute("username");
        String account=request.getParameter("account");
        int amount=Integer.parseInt(request.getParameter("amount"));       
        String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";
        String Query="Select amt from accts where accno='"+account+"'";
       
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(Query);
    
            while(rs.next())
            {
                int amt=Integer.parseInt(rs.getString(1))+amount;
                Query="Update accts set amt='"+amt+"' where accno='"+account+"'";
                if(stmt.executeUpdate(Query)>0){
                    Query="select amt from accts where uname='"+uname+"'";
                    ResultSet rs1=stmt.executeQuery(Query);
                    while(rs1.next())
                    {
                       int amt1=Integer.parseInt(rs1.getString(1))-amount;
                       Query="Update accts set amt='"+amt1+"' where uname='"+uname+"'";
                       if(stmt.executeUpdate(Query)>0){
                                 out.println("<body style=\"background-image: url('images/bg.jpg');\"><center><br><br><h3>Money sent.<br><a href=\"http://localhost:8084/hms/menu.html\">back</a></h3></center></body>");
                       }
                    }
                }
                
            }
             
          
        }
        catch(Exception e){out.println("");}
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request,response);
    }
}
        
