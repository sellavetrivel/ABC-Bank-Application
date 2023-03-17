<%-- 
    Document   : AdminCheckUsersWithdrawStatement
    Created on : Nov 18, 2020, 3:57:28 PM
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
        String acc=request.getParameter("withdraw");
        %>
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>
                <tr>
                    <th>Account Number</th>
                    <th>IFSC Code</th>
                    <th>Branch</th>
                    <th>Withdraw Amount</th>
                    <th>Update Date & Time</th>
                </tr>
            </thead>

            <tbody>
                <%
                    try {
                        DbConnection dbb = new DbConnection();
                        Connection con = dbb.getConnection();
                        PreparedStatement ps = con.prepareStatement(DbQueries.USER_CHECK_WITHDRAW_STATEMENT);
                        ps.setString(1, acc);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String acno = rs.getString("acno");
                            String ifsc = rs.getString("ifsc");
                            String branch = rs.getString("branch");
                            String amount = rs.getString("amount");
                            String datetime = rs.getString("datetime");


                %>
                <tr>
                    <td><%=acno%></td>
                    <td><%=ifsc%></td>
                    <td><%=branch%></td>
                    <td>Rs.<%=amount%></td>
                    <td><%=datetime%></td>

                </tr>

                <%                              }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                %>

            </tbody>
        </table>
    </body>
</html>
