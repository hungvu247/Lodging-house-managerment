package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import utils.BCrypt;

public class AccountDAO extends DBContext {

    public Account getAccountById(int id) {
        String sql = "SELECT * FROM Account WHERE AccountId = ?";
        Account account = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id); // Set the value for the first parameter

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    account = new Account(
                            rs.getInt("AccountId"),
                            rs.getString("Email"),
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            rs.getInt("RoleID"),
                            rs.getString("TypeOfLogin")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return account;
    }

    public Account getAccountByUserName(String userName) {
        String sql = "SELECT * FROM dbo.Account WHERE UserName=?";
        Account account = null;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, userName); // Set the value for the first parameter

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    account = new Account(
                            rs.getInt("AccountId"),
                            rs.getString("Email"),
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            rs.getInt("RoleID"),
                            rs.getString("TypeOfLogin")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return account;
    }

    public Account getAccountByUserEmail(String email) {
        String sql = "SELECT * FROM dbo.Account WHERE email=?";
        Account account = null;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, email); // Set the value for the first parameter

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    account = new Account(
                            rs.getInt("AccountId"),
                            rs.getString("Email"),
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            rs.getInt("RoleID"),
                            rs.getString("TypeOfLogin")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return account;
    }

    public List<Account> getAllAccount() {
        String sql = """
                       SELECT a.*
                             FROM Account a
                             LEFT JOIN AccountInRoom air ON a.AccountId = air.AccountId
                             WHERE air.AccountId IS NULL
                             AND a.RoleID NOT IN (1, 3);
                     """;
        List<Account> list = new ArrayList<>();
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account(
                            rs.getInt("AccountId"),
                            rs.getString("Email"),
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            rs.getInt("RoleID"),
                            rs.getString("TypeOfLogin")
                    );
                    list.add(account);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return list;
    }

    public List<Account> getAccountByRole(int role) {
        String sql = "SELECT * FROM dbo.Account WHERE [RoleID]=?";
        List<Account> list = new ArrayList<>();
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, role); // Set the value for the first parameter

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account(
                            rs.getInt("AccountId"),
                            rs.getString("Email"),
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            rs.getInt("RoleID"),
                            rs.getString("TypeOfLogin")
                    );
                    list.add(account);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return list;
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        BCrypt bcrypt = new BCrypt();

        //khi so sánh chuỗi và dùng setString thì không cần dùng dấu ''
        String sql = "select * from Account where UserName = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String pwHashed = rs.getString("PassWord");
                if (bcrypt.checkpw(password, pwHashed)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insertAccount(Account account) {
        int rowAffected = 0;

        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([Email]\n"
                + "           ,[UserName]\n"
                + "           ,[PassWord]\n"
                + "           ,[RoleID]\n"
                + "           ,[TypeOfLogin])\n"
                + "     VALUES  "
                + "(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setInt(4, account.getRoleId());
            ps.setString(5, account.getTypeOfLogin());

            // nếu như insert 1 customer giống username thì sẽ lỗi.
            rowAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowAffected;
    }

    public boolean changePassword(String password, int id) {
        String sql = "UPDATE Account\n"
                + "SET PassWord = ?\n"
                + "WHERE AccountID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, password);
            stm.setInt(2, id);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int updateAccount(Account account) {
        int rowAffected = 0;

        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [Email] = ?\n"
                + "      ,[UserName] = ?\n"
                + "      ,[PassWord] = ?\n"
                + "      ,[RoleID] = ?\n"
                + "      ,[TypeOfLogin] = ?\n"
                + " WHERE AccountId = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setInt(4, account.getRoleId());
            ps.setString(5, account.getTypeOfLogin());
            ps.setInt(6, account.getAccountID());

            rowAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowAffected;
    }

    public List<Account> getAccountKeyWord(String keyWord) {
        String sql = "SELECT * FROM  Account WHERE Email like ?";

        List<Account> list = new ArrayList<>();
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, "%" + keyWord + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account(
                            rs.getInt("AccountId"),
                            rs.getString("Email"),
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            rs.getInt("RoleID"),
                            rs.getString("TypeOfLogin")
                    );
                    list.add(account);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return list;
    }
    
  

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();
        List<Account> list = ad.getAccountByRole(3);
        for (Account account : list) {
            System.out.println(account.getEmail());
        }
        
    }

}
