/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.user.action;

import com.bank.database.DbConnection;
import com.bank.database.DbQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Saranya Srinivasan
 */
public class UserWithdrawMoneyForCustomer extends HttpServlet {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accno = request.getParameter("accno");
            String ifsc = request.getParameter("ifsc");
            String branch = request.getParameter("branch");
            String amount = request.getParameter("amount");

            HttpSession s = request.getSession();
            String name = s.getAttribute("name").toString();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            String money = null;
            try {
                DbConnection dbb = new DbConnection();
                con = dbb.getConnection();
                ps = con.prepareStatement(DbQueries.UESR_SELECT_EXSISTING_USER_ACCOUNT);
                ps.setString(1, accno);
                ps.setString(2, ifsc);
                rs = ps.executeQuery();
                while (rs.next()) {
                    money = rs.getString("amount");
                }
                float dbamo = Float.parseFloat(money);
                float useramo = Float.parseFloat(amount);

                if (dbamo >= useramo) {

                    float reduce = dbamo - useramo;

                    ps = con.prepareStatement(DbQueries.USER_UPDATE_WITHDRAW);
                    ps.setFloat(1, reduce);
                    ps.setString(2, accno);
                    ps.setString(3, ifsc);
                    int y = ps.executeUpdate();
                    if (y != 0) {
                        ps = con.prepareStatement(DbQueries.USER_WITHDRAW_FOR_USER);
                        ps.setString(1, accno);
                        ps.setString(2, ifsc);
                        ps.setString(3, branch);
                        ps.setFloat(4, useramo);
                        ps.setString(5, dtf.format(now));
                        int z = ps.executeUpdate();
                        if (z != 0) {
                            ps = con.prepareStatement(DbQueries.USER_ALL_ACCOUNT_DETAILS);
                            ps.setString(1, name);
                            ps.setString(2, accno);
                            ps.setString(3, ifsc);
                            ps.setString(4, branch);
                            ps.setString(5, amount);
                            ps.setString(6, dtf.format(now));
                            ps.setString(7, "Withdraw");
                            int insert = ps.executeUpdate();
                        }
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Successfully Withdraw Amount...!!!')");
                        out.println("location='UserHome.jsp'");
                        out.println("</script>");

                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Sorry..Failed...!!!')");
                        out.println("location='UserWithdrawAmount.jsp'");
                        out.println("</script>");
                    }

                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry..Insufficient Balance...!!!')");
                    out.println("location='UserHome.jsp'");
                    out.println("</script>");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
