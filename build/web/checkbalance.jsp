<%-- 
    Document   : checkbalance
    Created on : 21 Aug, 2020, 1:24:36 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status</title>
    </head>
    <body style="background-image: url('images/bg.jpg');"">
        
    <%
         String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";
        
        String uname=(String)session.getAttribute("username");
        String Query=" select `name`,`accno`,`amt` from `accts` where `uname`='"+uname+"'";
        
       try
       {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(Query);
            
        %>  
        <center>  <h3>Account Details</h3>
        <div><table>
                <tr>  <th>Account Name</th> <th>  Account Number</th>  <th>  Balance</th></tr>
         <% while (rs.next())
            {
                String n=rs.getString(1);
                String ac=rs.getString(2);
                int b=Integer.parseInt(rs.getString(3));
          %>
                <tr> <td><%= n %></td>  <td><%=  ac %></td> <td><%=  b %></td> </tr>
               
          <%
             }
             
          %>
            </table></div>     
        
            <a href="menu.html">Back</a>
                    
            
                    <%
        }
        catch(Exception e){} 
        %>
    </body>
</html>