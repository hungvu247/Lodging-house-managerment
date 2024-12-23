/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Notification;

/**
 *
 * @author admin
 */
public class NotificationDAO extends DBContext {

    public List<Notification> getNotificationByAccountId(int accountId) {
        List<Notification> listN = new ArrayList<>();
        String sql = """
                      SELECT  [NotificationId]
                            ,[NotificationMessage]
                            ,[NotificationDateTime]                   
                            ,[ReceiverId]
                            ,[SenderId]
                            ,[ConfirmationStatus]
                        FROM [LHMS1].[dbo].[Notification] Where [ReceiverId]=?""";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Notification noti = new Notification(rs.getInt("NotificationId"),
                        rs.getString("NotificationMessage"),
                        rs.getDate("NotificationDateTime"),
                        rs.getInt("receiverId"),
                        rs.getInt("senderId"),
                        rs.getInt("confirmationStatus"));
                listN.add(noti);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi ở dao này");
        }
        return listN;
    }

    public Notification getNotificationById(String id) {

        String sql = """
                      SELECT  [NotificationId]
                            ,[NotificationMessage]
                            ,[NotificationDateTime]
                            ,[ReceiverId]
                            ,[SenderId]
                            ,[ConfirmationStatus]
                        FROM [LHMS1].[dbo].[Notification] Where [NotificationId]=?""";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Notification noti = new Notification(rs.getInt("NotificationId"),
                        rs.getString("NotificationMessage"),
                        rs.getDate("NotificationDateTime"),
                        rs.getInt("receiverId"),
                        rs.getInt("senderId"),
                        rs.getInt("confirmationStatus"));
                return noti;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi ở dao này");
        }
        return null;
    }

    public void insertNotification(Notification newNotification) {
        String sql = """
                INSERT INTO [dbo].[Notification]
                          ([NotificationMessage]
                          ,[NotificationDateTime]
                          ,[ReceiverId]
                          ,[SenderId]
                          ,[ConfirmationStatus])
                VALUES
                          (?,?,?,?,?)""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newNotification.getNotificationMessage());
            java.util.Date utilDate = newNotification.getNotificationDateTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setDate(2, sqlDate);
            st.setInt(3, newNotification.getReceiverId());
            st.setInt(4, newNotification.getSenderId());
            st.setInt(5, newNotification.isConfirmationStatus());

            int rowsInserted = st.executeUpdate(); // Thực thi câu lệnh INSERT

            if (rowsInserted > 0) {
                System.out.println("Thông báo đã được thêm vào cơ sở dữ liệu.");
            } else {
                System.out.println("Không có thông báo nào được thêm vào cơ sở dữ liệu.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm thông báo vào cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void confirmNotification(Notification newNotificaiton) {

        String sql = """
                    UPDATE [dbo].[Notification]
                                      SET [NotificationMessage] = ?
                                         ,[NotificationDateTime] = ?
                                         ,[ReceiverId] = ?
                                         ,[SenderId] = ?
                                         ,[ConfirmationStatus] = ?
                                    WHERE NotificationId =?""";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newNotificaiton.getNotificationMessage());
            java.util.Date utilDate = newNotificaiton.getNotificationDateTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setDate(2, sqlDate);
            st.setInt(3, newNotificaiton.getReceiverId());
            st.setInt(4, newNotificaiton.getSenderId());
            st.setInt(5, 2);
            st.setInt(6, newNotificaiton.getNotificationId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException e) {
            System.out.println("Lỗi ở dao này");
        }
    }

    public void RejectNotification(Notification newNotificaiton) {

        String sql = """
                    UPDATE [dbo].[Notification]
                                      SET [NotificationMessage] = ?
                                         ,[NotificationDateTime] = ?
                                         ,[ReceiverId] = ?
                                         ,[SenderId] = ?
                                         ,[ConfirmationStatus] = ?
                                    WHERE NotificationId =?""";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newNotificaiton.getNotificationMessage());
            java.util.Date utilDate = newNotificaiton.getNotificationDateTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setDate(2, sqlDate);
            st.setInt(3, newNotificaiton.getReceiverId());
            st.setInt(4, newNotificaiton.getSenderId());
            st.setInt(5, 1);
            st.setInt(6, newNotificaiton.getNotificationId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException e) {
            System.out.println("Lỗi ở dao này");
        }
    }
    public static void main(String[] args) {
        NotificationDAO no=new NotificationDAO();
           List<Notification> listN=no.getNotificationByAccountId(3);
           for (Notification notification : listN) {
            
               System.out.println(notification);
        }
    }
}
