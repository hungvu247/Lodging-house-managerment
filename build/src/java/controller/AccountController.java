/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;
import utils.BCrypt;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.ValidatePassword;
import com.google.gson.Gson;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

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
        HttpSession session = request.getSession();
        AccountDAO accountDao = new AccountDAO();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
        }
        int id = account.getAccountID();
        Account showAccount = accountDao.getAccountById(id);
        String service = request.getParameter("service");
        if (service == null) {
            service = "showProfile";
        }
        if (service.equals("showProfile")) {
            request.setAttribute("tagMenu", "showProfile");
            request.setAttribute("account", showAccount);
            request.getRequestDispatcher("view/tenant/tenant-profile.jsp").forward(request, response);
        }
        if (service.equals("showChangePassword")) {
            request.setAttribute("tagMenu", "changePassword");

            request.getRequestDispatcher("view/tenant/change-password.jsp").forward(request, response);
        }
        if (service.equals("logout")) {
            session.removeAttribute("account");
            response.sendRedirect("view/login/login.jsp");
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
        processRequest(request, response);
        HttpSession session = request.getSession();
        AccountDAO accountDao = new AccountDAO();
        Account account = (Account) session.getAttribute("account");
        int id = account.getAccountID();
        String service = request.getParameter("service");
        if (service == null) {
            service = "";
        }
        if (service.equals("update")) {
            updateProfile(request, response, id);
        }
        if (service.equals("changePassword")) {
            changePassword(request, response, id);
        }
        if (service.equals("checkUserAndEmail")) {
            checkUserAndEmail(request, response, id);
        }
    }
    
    
    private static void updateProfile(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, ServletException {
        AccountDAO accountDao = new AccountDAO();
        try {
                String fullName = request.getParameter("fullName");
                String provinceRaw = request.getParameter("province");
                String districtRaw = request.getParameter("district");
                String wardRaw = request.getParameter("ward");
                String addressDetail = request.getParameter("address");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phone");
                int gender = Integer.parseInt(request.getParameter("gender"));
                String avatar = request.getParameter("avatar");
                String cic = request.getParameter("cic");
                String date = request.getParameter("dob");
                String userName = request.getParameter("userName");
                Date dob = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);

                String ward = "";
                String district = "";
                String province = "";

                //get data from API
                URL urlobj = new URL("https://esgoo.net/api-tinhthanh/5/" + wardRaw + ".htm");
                HttpURLConnection connection = (HttpURLConnection) urlobj.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                try {

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        StringBuilder sb = new StringBuilder();
                        Scanner sc = new Scanner(urlobj.openStream());
                        while (sc.hasNext()) {
                            sb.append(sc.nextLine());
                        }
                        sc.close();
                        JSONParser parser = new JSONParser();
                        JSONObject json = (JSONObject) parser.parse(sb.toString());
                        JSONObject json1 = (JSONObject) parser.parse(String.valueOf(json.get("data")));
                        // Get the value of the key "full_name"
                        System.out.println(json1.toJSONString());
                        String fullName1 = (String) json1.get("full_name");
                        String[] location = fullName1.split(", ");
                        ward = location[0];
                        district = location[1];
                        province = location[2];

                    } else {
                        System.out.println("Error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                //set province, cut numberID part
                if (containsDigit(provinceRaw + province)) {
                    province = (provinceRaw + province).substring(2);
                } else {
                    province = provinceRaw + province;
                }
                
                //set district, cut numberID part
                if (containsDigit(districtRaw + district)) {
                    district = (districtRaw + district).substring(3);
                } else {
                    district = districtRaw + district;
                }
                
                //set ward, cut numberID part
                if (containsDigit(wardRaw + ward)) {
                    ward = (wardRaw + ward).substring(5);
                } else {
                    ward = wardRaw + ward;
                }
                
                //Initialize object account
                Account accountUpdate = new Account(id, fullName, province, 
                        district, ward, addressDetail, email, phoneNumber, gender, 
                        userName, avatar, cic, dob);
                
                //update Account
                if (accountDao.updateProfile(accountUpdate)) {
                    response.sendRedirect("account?service=showProfile");
                }

            } catch (java.text.ParseException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private static void checkUserAndEmail(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        Map<String, String> result = new HashMap<>();
        if (!checkUsername(userName, id)) {
            result.put("userNameError", "*User Name đã tồn tại*");
        }
        if (!checkEmail(email, id)) {
            result.put("emailError", "*Email đã tồn tại.*");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(result));
    }

    private static void changePassword(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, ServletException {
        AccountDAO accountDao = new AccountDAO();
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("reNewPassword");

        //get account
        Account showAccount = accountDao.getAccountById(id);
        String userName = showAccount.getUsername();
        ValidatePassword validatePass = new ValidatePassword();
        BCrypt bcrypt = new BCrypt();
        if (accountDao.checkUsernameAndPassword(userName, currentPassword)) {
            if (validatePass.validatePassword(newPassword)) {
                if (newPassword.equals(reNewPassword)) {
                    newPassword = bcrypt.hashpw(newPassword, bcrypt.gensalt());
                    if (accountDao.changePassword(newPassword, id)) {
                        request.setAttribute("messageChangeSuccess", "*Thay đổi mật khẩu thành công*");
                        request.getRequestDispatcher("view/tenant/change-password.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("password", currentPassword);
                    request.setAttribute("messageReNewPassword", "*Mật khẩu không trùng khớp*");
                    request.getRequestDispatcher("view/tenant/change-password.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("password", currentPassword);
                request.setAttribute("messageNewPassword", "*Mật khẩu từ 8 - 32 kí tự,bao gồm chữ thường, chữ hoa, số và kí tự đặc biệt*");
                request.getRequestDispatcher("view/tenant/change-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("messagePassword", "*Mật khẩu không chính xác*");
            request.getRequestDispatcher("view/tenant/change-password.jsp").forward(request, response);
        }
    }

    public static boolean containsDigit(String str) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean checkUsername(String userName, int id) {
        AccountDAO ad = new AccountDAO();
        if (ad.getAccountByUserName(userName) != null
                && ad.getAccountByUserName(userName).getAccountID() != id) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEmail(String email, int id) {
        AccountDAO ad = new AccountDAO();
        if (ad.getAccountByUserEmail(email) != null
                && ad.getAccountByUserEmail(email).getAccountID() != id) {
            return false;
        } else {
            return true;
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
