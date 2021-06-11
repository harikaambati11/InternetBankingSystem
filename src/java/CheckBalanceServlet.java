import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckBalanceServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String account=request.getParameter("account");
        int amount=Integer.parseInt(request.getParameter("amount"));       
        String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";
        String Query="Select 'amt' from accts";
       
        
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
                    out.println("Money Sent");
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
