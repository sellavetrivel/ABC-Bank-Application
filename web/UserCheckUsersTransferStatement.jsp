<%-- 
    Document   : AdminCheckUsersWithdrawStatement
    Created on : Nov 17, 2020, 3:57:28 PM
    Author     : vetrivel
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
        String acc=request.getParameter("transfer");
        %>
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>
                <tr>
                    <th>Beneficiary Name</th>
                    <th>From Account</th>
                    <th>To Account</th>
                    <th>Beneficiary IFSC Code</th>
                    <th>Beneficiary Branch</th>
                    <th>Transfered Amount</th>
                    <th>Transfered Date & Time</th>
                </tr>
            </thead>

            <tbody>
                <%
                    try {
                        DbConnection dbb = new DbConnection();
                        Connection con = dbb.getConnection();
                        PreparedStatement ps = con.prepareStatement(DbQueries.USER_CHECK_TRANSFER_STATEMENT);
                        ps.setString(1, acc);
                        
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String acno = rs.getString("bname");
                            String ifsc = rs.getString("fromaccno");
                            String branch = rs.getString("toaccno");
                            String amount = rs.getString("ifsc");
                            String datetime = rs.getString("branch");
                            String amo = rs.getString("amount");
                            String datee = rs.getString("datetime");


                %>
                <tr>
                    <td><%=acno%></td>
                    <td><%=ifsc%></td>
                    <td><%=branch%></td>
                    <td><%=amount%></td>
                    <td><%=datetime%></td>
                    <td>Rs.<%=amo%></td>
                    <td><%=datee%></td>

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
