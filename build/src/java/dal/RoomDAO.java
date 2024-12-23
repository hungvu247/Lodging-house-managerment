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
import model.RoomLodging;

/**
 *
 * @author ASUS
 */
public class RoomDAO extends DBContext {

    public RoomLodging getRoomById(int id) {
        String sql = "SELECT r.RoomId,r.Price,r.MaxOfQuantity,r.Image,r.Description,r.LodgingHouseId,r.AccountId,l.NameLodgingHouse\n"
                + ",l.Province,l.District,l.Ward,l.AddressDetail,l.Image AS ImageLodging,l.NumberOfRoom\n"
                + "FROM dbo.Rooms r INNER JOIN dbo.LodgingHouses l\n"
                + "ON l.LodgingHouseId = r.LodgingHouseId\n"
                + "WHERE r.AccountId = ?";
        RoomLodging roomLodging = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id); // Set the value for the first parameter

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    roomLodging = new RoomLodging(
                            rs.getInt("RoomId"),
                            rs.getDouble("Price"),
                            rs.getInt("MaxOfQuantity"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getInt("LodgingHouseId"),
                            rs.getInt("AccountID"),
                            rs.getString("NameLodgingHouse"),
                            rs.getString("Province"),
                            rs.getString("District"),
                            rs.getString("Ward"),
                            rs.getString("AddressDetail"),
                            rs.getString("ImageLodging"),
                            rs.getInt("NumberOfRoom")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getRoomById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getRoomById: " + e.getMessage());
        }
        return roomLodging;
    }

    public Room getRoomsById(String roomId) {      
        String sql = "SELECT * FROM Rooms WHERE RoomId = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, roomId);

            ResultSet rs = st.executeQuery();

            LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();

            if (rs.next()) {
                Room room = new Room(rs.getString("RoomId"),
                        rs.getDouble("Price"),
                        rs.getInt("MaxOfQuantity"),
                        rs.getInt("Status"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        lodgingHousesDAO.getLodgingHouseById(rs.getInt("LodgingHouseId")),
                        rs.getInt("StatusDelete"),
                        rs.getString("RoomName"));
                return room;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Room> getRoomsByLodgingHouseId(int lodgingHouseId) {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM Rooms WHERE LodgingHouseId = ? AND StatusDelete = 1";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lodgingHouseId);

            ResultSet rs = st.executeQuery();

            LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();

            while (rs.next()) {
                Room room = new Room(rs.getString("RoomId"),
                        rs.getDouble("Price"),
                        rs.getInt("MaxOfQuantity"),
                        rs.getInt("Status"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        lodgingHousesDAO.getLodgingHouseById(rs.getInt("LodgingHouseId")),
                        rs.getInt("StatusDelete"),
                        rs.getString("RoomName"));

                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertRoom(Room room) {
        String sql = """
                     INSERT INTO Rooms (RoomId, Price, MaxOfQuantity, Status, Image, Description, LodgingHouseId, StatusDelete, RoomName)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, room.getRoomId());
            st.setDouble(2, room.getPrice());
            st.setInt(3, room.getMaxOfQuantity());
            st.setInt(4, room.getStatus());
            st.setString(5, room.getImage());
            st.setString(6, room.getDescription());
            st.setInt(7, room.getLodgingHouse().getLodgingHouseId());
            st.setInt(8, room.getStatusDelete());
            st.setString(9, room.getRoomName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public int getNumberRoomInLodgingHouse(int lodgingHouseId) {
        String sql = "SELECT count(*) as num from Rooms where LodgingHouseId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lodgingHouseId);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("num");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        RoomDAO r = new RoomDAO();
        System.out.println(r.getRoomsByLodgingHouseId(3).size());
    }
}
