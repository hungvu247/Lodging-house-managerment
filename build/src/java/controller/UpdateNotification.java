/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.NotificationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Notification;

/**
 *
 * @author admin
 */
public class UpdateNotification extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      String notificationId = request.getParameter("notificationId");
        System.out.println(notificationId);
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        NotificationDAO d = new NotificationDAO();
        Notification newNotificaiton = d.getNotificationById(notificationId);
        String[] detail = newNotificaiton.getNotificationMessage().split(",");
        String lodgingHouseId_raw = detail[2];
        // Xử lý logic dựa trên action (check hoặc cross)
        if ("check".equals(action)) {
            d.confirmNotification(newNotificaiton);
            response.sendRedirect("home-manager?LodgingHouseID="+lodgingHouseId_raw);
        } else if ("cross".equals(action)) {
            d.RejectNotification(newNotificaiton);
            response.sendRedirect("list-notification");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
           String notificationId = request.getParameter("notificationId");
        System.out.println(notificationId);
        String action = request.getParameter("action");
        System.out.println(action);
        HttpSession session = request.getSession();
        NotificationDAO d = new NotificationDAO();
        Notification newNotificaiton = d.getNotificationById(notificationId);
        String[] detail = newNotificaiton.getNotificationMessage().split(",");
        String lodgingHouseId_raw = detail[2];
        // Xử lý logic dựa trên action (check hoặc cross)
        if ("check".equals(action)) {

            session.setAttribute("lodgingID", lodgingHouseId_raw);
            d.confirmNotification(newNotificaiton);
            response.sendRedirect("home-manager");
        } else if ("cross".equals(action)) {
            d.RejectNotification(newNotificaiton);
        }

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
