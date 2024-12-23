/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.RoomDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Room;
import utils.Pagination;

/**
 *
 * @author admin
 */
public class HomeManager extends HttpServlet {

    String lodgingIDRepo;
    String currentPageRepo;

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
        RoomDAO roomDAO = new RoomDAO();
        HttpSession session = request.getSession();
        System.out.println("===========================================");
        String lodgingID = request.getParameter("LodgingHouseID");
        System.out.println(lodgingID);
        if (lodgingID != null) {
            lodgingIDRepo = lodgingID;
        }
        int numberPerPage = 3;
        String curentPageRaw = request.getParameter("curentPage");
        if (curentPageRaw != null) {
            currentPageRepo = curentPageRaw;
        }

        session.setAttribute("lodgingID", lodgingIDRepo);

        List<Room> listRoom = roomDAO.getRoomsByLodgingHouseId(Integer.parseInt(lodgingIDRepo));

        Pagination<Room> pagination = new Pagination<>(currentPageRepo, numberPerPage, listRoom);
        request.setAttribute("paginationRoom", pagination);
        System.out.println(pagination.getListObject().size());
        request.getRequestDispatcher("view/manager/Home-manager.jsp").forward(request, response);

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
        RoomDAO roomDAO = new RoomDAO();
        HttpSession session = request.getSession();
        String lodgingID = request.getParameter("LodgingHouseID");
        
        if (lodgingID != null) {
            lodgingIDRepo = lodgingID;
        }

        int numberPerPage = 3;
        String curentPageRaw = request.getParameter("curentPage");
        if (curentPageRaw != null) {
            currentPageRepo = curentPageRaw;
        }

        session.setAttribute("lodgingID", lodgingIDRepo);
        List<Room> listRoom = roomDAO.getRoomsByLodgingHouseId(Integer.parseInt(lodgingIDRepo));

        Pagination<Room> pagination = new Pagination<>(currentPageRepo, numberPerPage, listRoom);
        request.setAttribute("paginationRoom", pagination);
        request.getRequestDispatcher("view/manager/Home-manager.jsp").forward(request, response);
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
