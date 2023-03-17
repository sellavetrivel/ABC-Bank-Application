/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.admin.actions;

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

/**
 *
 * @author Saranya Srinivasan
 */
public class AdminEditCustomerDetails extends HttpServlet {

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
            String bankname = request.getParameter("bankname");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String mail = request.getParameter("mail");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");
            String branchname = request.getParameter("branchname");
            String address = request.getParameter("address");
            String acno = request.getParameter("acno");
            String ifsc = request.getParameter("ifsc");
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));

            try {
                DbConnection dbb = new DbConnection();
                con = dbb.getConnection();
                ps = con.prepareStatement(DbQueries.ADMIN_EDIT_USERS_DATA);
                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.setString(3, mail);
                ps.setString(4, gender);
                ps.setString(5, age);
                ps.setString(6, branchname);
                ps.setString(7, address);
                ps.setString(8, ifsc);
                ps.setString(9, dtf.format(now));
                ps.setString(10, acno);
                
                int x=ps.executeUpdate();
                if(x!=0){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Success...!!!')");
                    out.println("location='AdminHome.jsp'");
                    out.println("</script>");
                }else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed...!!!')");
                    out.println("location='AdminViewUserAccount.jsp'");
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
