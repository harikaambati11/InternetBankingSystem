<%-- 
    Document   : transfer
    Created on : 20 Aug, 2020, 9:29:11 PM
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
    <body style="background-image: url('images/bg.jpg');">
        
        <%
         String url="jdbc:mysql://localhost:3306/hms_db";
        String user="root";
        String password="";
        
        String uname=(String)session.getAttribute("username");
        String Query=" select `name`,`verified` from `benef` where `uname`='"+uname+"'";
        
       try
       {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(Query);
            
        %>  
        <center>  <h3>Status of requests</h3>
        <div><table>
                <tr>  <th>Account Name</th> <th>Verified</th>  </tr>
         <% while (rs.next())
            {
                String n=rs.getString(1);
                String v=rs.getString(2);
          %>
                <tr> <td><%=n %></td>  <td><%=v %></td>  </tr>
               
          <%
             }
             Query=" select `name`,`accno` from `benef` where `verified`='Yes' and `uname`='"+uname+"'";
             ResultSet rs1=stmt.executeQuery(Query);
          %>
            </table></div>
        
        <form action="/hms/TransferProcServlet" method="Get">
            <h3>Transfer Funds</h3>
            <div><table>
                    <tr><td><label for="account">Choose Account:</label> </td>  <td> <select name="account" id="account">   
            <%while(rs1.next())
            {
                String u=rs1.getString(2);
                
            %>
                    <option value="<%=u%>"><%=u%></option>  
               
            <%} %>
                    </select></td></tr>
                    <tr><td>Amount</td><td><input type="text" name="amount"></td></tr> 
                    
            </table></div>
            <br>
            <input type = "submit" value="Transfer Now" /> 
            
        </form></center>
        <%       
        }
        catch(Exception e){} 
        %>
    </body>
</html>
 
