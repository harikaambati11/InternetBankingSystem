<%-- 
    Document   : verify
    Created on : 20 Aug, 2020, 8:48:32 PM
    Author     : Administrator
--%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Applications</title>
    </head>
    <body style="background-image: url('images/bg.jpg');">
        
        <%
         String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";

        String name=(String)session.getAttribute("name");
        String Query=" select * from `benef` where `verified`='no'";
        
       try
       {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(Query);
        %>    
        <form action="/hms/ApproveProcServlet" method="Get">
            <center> <h3>Select accounts to verify</h3>
            <div><table>
            <%while(rs.next())
            {
                String n=rs.getString(2);
                String a=rs.getString(3);
                
            %>
                
            <tr><td><input type = "checkbox" name = "accounts" value="<%=a%>" /></td><td> <%=n%></td> <td><%=a%></td></tr>
            <br>    
            <%} %>
            </table></div>
            <br>
            <input type = "submit" value="verify" /> 
            </center>
        </form>
        <%       
        }
        catch(Exception e){} 
        %>
    </body>
</html>

