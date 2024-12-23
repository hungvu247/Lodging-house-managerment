/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dal.RoomDAO;
import dal.ServiceDAO;
import dal.ServiceOfLodgingHouseDAO;
import model.ServiceOfRoom;
import model.Room;
import model.Service;
import dal.ServiceOfRoomDAO;
import model.ServiceOfLodgingHouse;

/**
 *
 * @author admin
 */
public class ServiceInRoomServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiceInRoomServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceInRoomServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        RoomDAO roomDAO = new RoomDAO();
        ServiceOfRoomDAO serviceOfRoomDAO = new ServiceOfRoomDAO();
        ServiceOfLodgingHouseDAO serviceOfLodgingHouseDAO = new ServiceOfLodgingHouseDAO();
        String[] listService = request.getParameterValues("service");
        String roomId = (String) session.getAttribute("roomId");
        String lodgingHouseIDRaw = (String) session.getAttribute("lodgingID");
        int lodgingHouseID = Integer.parseInt(lodgingHouseIDRaw);
        out.print(roomId);

        Room room = roomDAO.getRoomsById(roomId);
        out.print(room.getRoomName());
        serviceOfRoomDAO.resetServiceInRoom(roomId);
        for (String serviceIdRaw : listService) {
            int serviceId = Integer.parseInt(serviceIdRaw);
            ServiceOfLodgingHouse serviceOfLodgingHouse = serviceOfLodgingHouseDAO.getAllServiceOfLodgingHouseUnique(lodgingHouseID, serviceId);        
            ServiceOfRoom serviceOfRoom = new ServiceOfRoom(serviceOfLodgingHouse, room);
            serviceOfRoomDAO.insertServiceOfRoom(serviceOfRoom);
        }
        response.sendRedirect("room-detail-servlet?id=" + roomId);
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
