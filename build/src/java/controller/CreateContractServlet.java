/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LodgingHouse;
import dal.LodgingHousesDAO;
import model.InformationOfUser;
import dal.InformationOfUserDAO;
import model.Room;
import dal.RoomDAO;
import java.util.List;
import model.Account;
import dal.AccountDAO;

/**
 *
 * @author admin
 */
public class CreateContractServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateContractServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateContractServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();
        InformationOfUserDAO informationOfUserDAO = new InformationOfUserDAO();
        RoomDAO roomDAO = new RoomDAO();
        AccountDAO accountDAO = new AccountDAO();
        try {
            String lodgingHouseIDRaw = (String) session.getAttribute("lodgingID");
            String roomID = (String) session.getAttribute("roomId");
            int lodgingHouseID = Integer.parseInt(lodgingHouseIDRaw);
            LodgingHouse lodgingHouse = lodgingHousesDAO.getLodgingHouseById(lodgingHouseID);
            InformationOfUser manager = informationOfUserDAO.getManagerInfoByLodgingHouseId(lodgingHouseID);
            Room room = roomDAO.getRoomsById(roomID);
            List<Account> listAccount = accountDAO.getAccountByRole(3);
            for (Account account : listAccount) {
                System.out.println("------------------------------");
                System.out.println(account.getEmail());
            }
          
            request.setAttribute("listTenant", listAccount);
            request.setAttribute("lodgingHouse", lodgingHouse);
            request.setAttribute("manager", manager);
            request.setAttribute("room", room); 
            request.getRequestDispatcher("view/manager/create-contract.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("dm");
        }

      
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
        LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();
        RoomDAO roomDAO = new RoomDAO();
        AccountDAO accountDAO = new AccountDAO();
        InformationOfUserDAO informationOfUserDAO = new InformationOfUserDAO();
        String lodgingHouseIdRaw = request.getParameter("lodgingHouseId");
        String roomId = request.getParameter("room");
        String managerIdRaw = request.getParameter("managerId");
        String email = request.getParameter("email");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        System.out.println(roomId);
        System.out.println(dateFrom);
        try {
            int lodgingHouseId = Integer.parseInt(lodgingHouseIdRaw);
            int managerId = Integer.parseInt(managerIdRaw);
            LodgingHouse lodgingHouse = lodgingHousesDAO.getLodgingHouseById(lodgingHouseId);
            Room room = roomDAO.getRoomsById(roomId);
            Account tenant = accountDAO.getAccountByUserEmail(email);
            InformationOfUser informationOfTenant = informationOfUserDAO.getInformationByAccountID(tenant.getAccountID());
            InformationOfUser informationOfManager = informationOfUserDAO.getInformationByAccountID(managerId);
            // Tạo nội dung hợp đồng (thay thế các biến bằng dữ liệu thật)
            String contractContent = String.format(
                    "<p><strong>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</strong><br>Độc lập – Tự do – Hạnh phúc</p>"
                    + "<p><strong>HỢP ĐỒNG THUÊ PHÒNG TRỌ</strong></p>"
                    + "<p>Hôm nay ngày …… tháng …. năm ……..; <br>"
                    + "Tại địa chỉ:     %s - %s - %s - %s   <br>"
                    + "…………………………………………………………………………………<br>"
                    + "Chúng tôi gồm:<br>"
                    + "1.Đại diện bên cho thuê phòng trọ (Bên A):<br>"
                    + "Ông/bà:%s Sinh ngày: %s.<br>"
                    + "Số CMND: %s. <br>"
                    + "Số điện thoại:%s.<br>"
                    + "2. Bên thuê phòng trọ (Bên B):<br>"
                    + "Ông/bà: %s<br>"
                    + "Sinh ngày: %s.<br>"
                    + "Số CMND: %s.<br>"
                    + "Số điện thoại:%s.<br>"
                    + "Sau khi bàn bạc trên tinh thần dân chủ, hai bên cùng có lợi, cùng thống nhất như sau:</p>"
                    + "<p>Bên A đồng ý cho bên B thuê 01 phòng ở tại địa chỉ:<br>"
                    + " %s - %s - %s - %s .<br>"
                    + "Giá thuê: %f. đ/tháng<br>"
                    + "………………………………………………………………………………....…<br>"
                    + "Hợp đồng có giá trị kể từ ngày %s đến ngày %s.</p>"
                    + "<p><strong>TRÁCH NHIỆM CỦA CÁC BÊN</strong><br>"
                    + "* Trách nhiệm của bên A:<br>"
                    + "- Tạo mọi điều kiện thuận lợi để bên B thực hiện theo hợp đồng.<br>"
                    + "- Cung cấp nguồn điện, nước, wifi cho bên B sử dụng.<br>"
                    + "* Trách nhiệm của bên B:<br>"
                    + "- Thanh toán đầy đủ các khoản tiền theo đúng thỏa thuận.<br>"
                    + "- Bảo quản các trang thiết bị và cơ sở vật chất của bên A trang bị cho ban đầu (làm hỏng phải sửa, mất phải đền).<br>"
                    + "- Không được tự ý sửa chữa, cải tạo cơ sở vật chất khi chưa được sự đồng ý của bên A.<br>"
                    + "- Giữ gìn vệ sinh trong và ngoài khuôn viên của phòng trọ.<br>"
                    + "- Bên B phải chấp hành mọi quy định của pháp luật Nhà nước và quy định của địa phương.<br>"
                    + "- Nếu bên B cho khách ở qua đêm thì phải báo và được sự đồng ý của chủ nhà đồng thời phải chịu trách nhiệm về các hành vi vi phạm pháp luật của khách trong thời gian ở lại.</p>"
                    + "<p><strong>TRÁCH NHIỆM CHUNG</strong><br>"
                    + "- Hai bên phải tạo điều kiện cho nhau thực hiện hợp đồng.<br>"
                    + "- Trong thời gian hợp đồng còn hiệu lực nếu bên nào vi phạm các điều khoản đã thỏa thuận thì bên còn lại có quyền đơn phương chấm dứt hợp đồng; nếu sự vi phạm hợp đồng đó gây tổn thất cho bên bị vi phạm hợp đồng thì bên vi phạm hợp đồng phải bồi thường thiệt hại.<br>"
                    + "- Một trong hai bên muốn chấm dứt hợp đồng trước thời hạn thì phải báo trước cho bên kia ít nhất 30 ngày và hai bên phải có sự thống nhất.<br>"
                    + "- Bên A phải trả lại tiền đặt cọc cho bên B.<br>"
                    + "- Bên nào vi phạm điều khoản chung thì phải chịu trách nhiệm trước pháp luật.<br>"
                    + "- Hợp đồng được lập thành 02 bản có giá trị pháp lý như nhau, mỗi bên giữ một bản.</p>"
                    + "<p>ĐẠI DIỆN BÊN B                                                           ĐẠI DIỆN BÊN A</p>",
                    lodgingHouse.getAddressDetail(),
                    lodgingHouse.getWard(),
                    lodgingHouse.getDistrict(),
                    lodgingHouse.getProvince(),
                    informationOfManager.getFullName(),
                    informationOfManager.getDob(),
                    informationOfManager.getCic(),
                    informationOfManager.getPhoneNumber(),
                    informationOfTenant.getFullName(),
                    informationOfTenant.getDob(),
                    informationOfTenant.getCic(),
                    informationOfTenant.getPhoneNumber(),
                    lodgingHouse.getAddressDetail(),
                    lodgingHouse.getWard(),
                    lodgingHouse.getDistrict(),
                    lodgingHouse.getProvince(),
                    room.getPrice(),
                    dateFrom,
                    dateTo
            );

            // Trả về kết quả
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(contractContent.toString());

        } catch (Exception e) {
        }

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
