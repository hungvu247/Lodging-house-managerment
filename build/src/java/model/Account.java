package model;

import java.util.Date;

public class Account {

    private int accountID;
    private String email;
    private String username, password;
    private int roleId;
    private String typeOfLogin;

    public Account() {
    }

    public Account(int accountID, String email, String username, String password, int roleId, String typeOfLogin) {
        this.accountID = accountID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.typeOfLogin = typeOfLogin;
    }

    public Account(int accountID, String fullName, String province, String district, String ward, String addressDetail, String email, String phoneNumber, int gender, String username, String avatar, String cic, Date dob) {
        this.accountID = accountID;
        this.email = email;
        this.username = username;
    }

    public Account(String email, String username, String password, int roleId, String typeOfLogin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.typeOfLogin = typeOfLogin;
    }
    
    public Account(String email, int roleId, String typeOfLogin) {
        this.email = email;
        this.roleId = roleId;
        this.typeOfLogin = typeOfLogin;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTypeOfLogin() {
        return typeOfLogin;
    }

    public void setTypeOfLogin(String typeOfLogin) {
        this.typeOfLogin = typeOfLogin;
    }

//    @Override
//    public String toString() {
//        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", email=" + email + ", username=" + username + ", password=" + password + ", roleId=" + roleId + ", typeOfLogin=" + typeOfLogin + '}';
    }

}