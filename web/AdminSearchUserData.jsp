<%-- 
    Document   : AdminSearchUserData
    Created on : Nov 15, 2020, 4:16:06 PM
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
            String id = request.getParameter("search");
        %>


        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>E-mail</th>
                    <th>Gender</th>
                    <th>Age</th>
                    <th>Branch</th>
                    <th>Address</th>
                    <th>Account Number</th>
                    <th>IFSC Code</th>
                </tr>
            </thead>

            <tbody>
                <%
                    try {
                        DbConnection dbb = new DbConnection();
                        Connection con = dbb.getConnection();
                        PreparedStatement ps = con.prepareStatement(DbQueries.ADMIN_SEARCH_USERS_DATA);
                        ps.setString(1, id);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String first = rs.getString("firstname");
                            String last = rs.getString("lastname");
                            String mail = rs.getString("mail");
                            String gen = rs.getString("gender");
                            String age = rs.getString("age");
                            String branchname = rs.getString("branchname");
                            String adress = rs.getString("address");
                            String accno = rs.getString("accno");
                            String ifsc = rs.getString("ifsc");


                %>
                <tr>
                    <td><%=first%></td>
                    <td><%=last%></td>
                    <td><%=mail%></td>
                    <td><%=gen%></td>
                    <td><%=age%></td>
                    <td><%=branchname%></td>
                    <td><%=adress%></td>
                    <td><%=accno%></td>
                    <td><%=ifsc%></td>

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
