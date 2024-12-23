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
import model.Room;
import model.ServiceOfLodgingHouse;
import model.ServiceOfRoom;

/**
 *
 * @author admin
 */
public class ServiceOfRoomDAO extends DBContext {

    public void insertServiceOfRoom(ServiceOfRoom serviceOfRoom) {
        String sql = "INSERT INTO ServiceOfRoom (ServiceId, RoomId, Price) VALUES  (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, serviceOfRoom.getServiceOfLodgingHouse().getServiceId());
            st.setString(2, serviceOfRoom.getRoom().getRoomId());
            st.setDouble(3, serviceOfRoom.getServiceOfLodgingHouse().getPrice());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<ServiceOfRoom> getServiceOfRoom(int lodgingHouseId, String roomId) {
        List<ServiceOfRoom> list = new ArrayList<>();
        String sql = "select * from ServiceOfRoom WHERE RoomId = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, roomId);
            ResultSet rs = st.executeQuery();
            ServiceDAO serviceDAO = new ServiceDAO();
            RoomDAO roomDAO = new RoomDAO();
            ServiceOfLodgingHouseDAO serviceOfLodgingHouseDAO = new ServiceOfLodgingHouseDAO();
            while (rs.next()) {
                ServiceOfLodgingHouse serviceOfLodgingHouse = serviceOfLodgingHouseDAO.getAllServiceOfLodgingHouseUnique(lodgingHouseId, rs.getInt("ServiceId"));
                Room room = roomDAO.getRoomsById(rs.getString("RoomId"));
                ServiceOfRoom serviceOfRoom = new ServiceOfRoom(serviceOfLodgingHouse, room);
                list.add(serviceOfRoom);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void resetServiceInRoom(String roomId) {
        String sql = "DELETE FROM ServiceOfRoom WHERE RoomId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, roomId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        ServiceOfRoomDAO serviceOfRoomDAO = new ServiceOfRoomDAO();
        serviceOfRoomDAO.resetServiceInRoom("3_1");
    }

}
