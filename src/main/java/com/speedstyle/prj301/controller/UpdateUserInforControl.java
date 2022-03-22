/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.speedstyle.prj301.controller;

import com.speedstyle.prj301.dao.OrderDAO;
import com.speedstyle.prj301.dao.UserDAO;
import com.speedstyle.prj301.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avillX
 */
@WebServlet(name = "UpdateUserInforControl", urlPatterns = {"/updateU"})
public class UpdateUserInforControl extends HttpServlet {

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
        HttpSession ss = request.getSession();
        User us =  (User) ss.getAttribute("LOGIN_USER");

        UserDAO dao = new UserDAO();
        String act = request.getParameter("act");
        if(act.equals("infor")){
            User newUs = new User();
            newUs.setFullname(request.getParameter("fullname")); 
            newUs.setPhone(request.getParameter("phone"));
            newUs.setEmail(request.getParameter("email"));
            newUs.setAddress(request.getParameter("address"));
            newUs.setUsername(us.getUsername());
            dao.UpdateUser(newUs);
        } else if(act.equals("pass")){
            User newUs = new User();
            String OldPass = request.getParameter("OldPass");
            User check = dao.checkLogin(us.getUsername(), OldPass);
            if(check!= null) {
                newUs.setPassword(request.getParameter("NewPass"));
                newUs.setUsername(us.getUsername());
                dao.UpdateUserPass(newUs);
            }  
        }         
        
        response.sendRedirect(request.getContextPath()+"/user_infor");
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
