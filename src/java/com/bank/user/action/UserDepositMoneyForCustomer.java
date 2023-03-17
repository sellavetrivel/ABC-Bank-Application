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
public class UserDepositMoneyForCustomer extends HttpServlet {

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

            HttpSession s = request.getSession();
            String name = s.getAttribute("name").toString();

            String accno = request.getParameter("accno");
            String ifsc = request.getParameter("ifsc");
            String branch = request.getParameter("branch");
            String amount = request.getParameter("amount");
            float f = Float.parseFloat(amount);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            float amou = Float.parseFloat(amount);
            try {
                int xx = 0;
                DbConnection dbb = new DbConnection();
                con = dbb.getConnection();
                ps = con.prepareStatement(DbQueries.USER_VIEW_ACCOUNT_ALREADY_HAVE);
                ps.setString(1, accno);
                rs = ps.executeQuery();
                while (rs.next()) {
                    xx++;
                }
                if (xx == 0) {
                    ps = con.prepareStatement(DbQueries.USER_DEPOSIT_MONEY);
                    ps.setString(1, name);
                    ps.setString(2, accno);
                    ps.setString(3, ifsc);
                    ps.setString(4, branch);
                    ps.setString(5, amount);
                    ps.setString(6, dtf.format(now));
                    int update = ps.executeUpdate();
                } else {
                    String am = null;
                    float total = 0.0f;
                    float amm = 0.0f;
                    int yyy = 0;
                    ps = con.prepareStatement(DbQueries.USER_VIEW_ACCOUNT_ALREADY_HAVE);
                    ps.setString(1, accno);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        am = rs.getString("amount");
                        amm = Float.parseFloat(am);
                        yyy++;
                    }
                    total = amm + amou;
                    if (xx != 0) {
                        ps = con.prepareStatement(DbQueries.USER_UPDATE_ACCOUNT);
                        ps.setFloat(1, total);
                        ps.setString(2, accno);
                        int yy = ps.executeUpdate();

                    }
                }

                int x = ps.executeUpdate();
                if (x != 0) {
                    ps = con.prepareStatement(DbQueries.USER_ALL_ACCOUNT_DETAILS);
                    ps.setString(1, name);
                    ps.setString(2, accno);
                    ps.setString(3, ifsc);
                    ps.setString(4, branch);
                    ps.setString(5, amount);
                    ps.setString(6, dtf.format(now));
                    ps.setString(7, "Deposited");
                    int insert = ps.executeUpdate();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Success..!!')");
                    out.println("location='UserHome.jsp'");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed..!!')");
                    out.println("location='UserDepoist.jsp'");
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
