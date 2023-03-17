<%-- 
    Document   : UserGetCheckBalance
    Created on : Nov 19, 2020, 10:05:52 PM
    Author     : Vetrivel
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.bank.database.DbQueries"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.bank.database.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String acc = request.getParameter("balance");
        %>
        <table class="table table-bordered table-responsive table-active table-hover"  width="100%" cellspacing="0">

            <%
                try {
                    DbConnection dbb = new DbConnection();
                    Connection con = dbb.getConnection();
                    PreparedStatement ps = con.prepareStatement(DbQueries.USER_GET_BALANCE);
                    ps.setString(1, acc);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {

                        String accno = rs.getString("acno");
                        String ifsc = rs.getString("ifsc");
                        String branch = rs.getString("branch");
                        String amount = rs.getString("amount");

            %>
            
            <tr><td>Account Number</td><td><%=accno%></td></tr>
            <tr><td>IFSC Code</td><td><%=ifsc%></td></tr>
            <tr><td>Branch</td><td><%=branch%></td></tr>
            <tr><td>Balance Amount</td><td>Rs.<%=amount%></td></tr>


            <%            }
                } catch (Exception e) {
                    System.out.println(e);
                }
            %>

        </table>
    </body>
</html>
