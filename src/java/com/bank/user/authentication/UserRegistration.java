/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.user.authentication;

import com.bank.database.DbConnection;
import com.bank.database.DbQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Saranya Srinivasan
 */
public class UserRegistration extends HttpServlet {

    Connection con = null;
    PreparedStatement ps = null;

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
            String bank[] = bankname.split(" ");
            String ban = bank[0];
            String bann = bank[1];
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String mail = request.getParameter("mail");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");
            String branch = request.getParameter("branch");
            String address = request.getParameter("address");

            //Account Number
            Random randomGenerator = new Random();
            int ka = randomGenerator.nextInt(1000000000);
            String acno = Integer.toString(ka);

            //IFSC
            Random ifs = new Random();
            int ifscc = ifs.nextInt(100000);
            String ifsc = Integer.toString(ifscc);

            //Password
            Random pas = new Random();
            int passs = pas.nextInt(1000000);
            String pass = Integer.toString(passs);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));

            try {
                DbConnection dbb = new DbConnection();
                con = dbb.getConnection();
                ps = con.prepareStatement(DbQueries.ADMIN_ACCOUNT_CREAT_FOR_USER);
                ps.setString(1, bankname);
                ps.setString(2, firstname);
                ps.setString(3, lastname);
                ps.setString(4, mail);
                ps.setString(5, gender);
                ps.setString(6, age);
                ps.setString(7, branch);
                ps.setString(8, address);
                ps.setString(9, acno);
                ps.setString(10, ban+"000"+ifsc);
                ps.setString(11, pass);
                ps.setString(12, dtf.format(now));
                int update=ps.executeUpdate();
                if(update!=0){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sucess...')");
                    out.println("location='AdminHome.jsp'");
                    out.println("</script>");
                }else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed...')");
                    out.println("location='AdminCreateAccountForUser.jsp'");
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
