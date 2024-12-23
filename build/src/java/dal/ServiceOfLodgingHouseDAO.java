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
import model.ServiceOfLodgingHouse;

/**
 *
 * @author ASUS ZenBook
 */
public class ServiceOfLodgingHouseDAO extends DBContext {

    public List<ServiceOfLodgingHouse> getAllServiceOfLodgingHouse() {
        List<ServiceOfLodgingHouse> list = new ArrayList<>();

        String sql = "SELECT * FROM ServiceOfLodgingHouse";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    list.add(new ServiceOfLodgingHouse(
                            rs.getInt("ServiceId"),
                            rs.getInt("LodgingHouseId"),
                            rs.getDouble("Price")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return list;
    }

    public List<ServiceOfLodgingHouse> getAllServiceOfLodgingHouseByLodgingHouseId(int lodgingHouseId) {
        List<ServiceOfLodgingHouse> list = new ArrayList<>();

        String sql = "SELECT * FROM ServiceOfLodgingHouse where LodgingHouseId = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, lodgingHouseId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    list.add(new ServiceOfLodgingHouse(
                            rs.getInt("ServiceId"),
                            rs.getInt("LodgingHouseId"),
                            rs.getDouble("Price")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return list;
    }

    public int insertServiceOfLodgingHouse(ServiceOfLodgingHouse serviceOfLodgingHouse) {
        int rowAffected = 0;

        String sql = "INSERT INTO [dbo].[ServiceOfLodgingHouse]\n"
                + "           ([ServiceId]\n"
                + "           ,[LodgingHouseId]\n"
                + "           ,[Price])\n"
                + "     VALUES  "
                + "(?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, serviceOfLodgingHouse.getServiceId());
            ps.setInt(2, serviceOfLodgingHouse.getLodgingHouseId());
            ps.setDouble(3, serviceOfLodgingHouse.getPrice());

            // nếu như insert 1 customer giống username thì sẽ lỗi.
            rowAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowAffected;
    }
    
    public ServiceOfLodgingHouse getAllServiceOfLodgingHouseUnique(int lodgingHouseId, int serviceId) {
      

        String sql = "SELECT * FROM ServiceOfLodgingHouse where LodgingHouseId = ? AND ServiceId = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, lodgingHouseId);
            pstm.setInt(2, serviceId);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    ServiceOfLodgingHouse serviceOfLodgingHouse= new ServiceOfLodgingHouse(
                            rs.getInt("ServiceId"),
                            rs.getInt("LodgingHouseId"),
                            rs.getDouble("Price")
                    );
                    return serviceOfLodgingHouse;
                }
                
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        ServiceOfLodgingHouseDAO dao = new ServiceOfLodgingHouseDAO();
        ServiceOfLodgingHouse serviceOfLodgingHouse = dao.getAllServiceOfLodgingHouseUnique(3, 1);
        System.out.println(serviceOfLodgingHouse.getPrice());
        
    }

}


