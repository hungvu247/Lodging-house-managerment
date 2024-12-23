/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Contract {
    private Room room;
    private Account tenantId;
    private Account Manager;
    private Date dateFrom;
    private Date dateTo;
    private int statusDelete;
    private int statusAccept;

    public Contract(Room room, Account tenantId, Account Manager, Date dateFrom, Date dateTo, int statusDelete, int statusAccept) {
        this.room = room;
        this.tenantId = tenantId;
        this.Manager = Manager;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.statusDelete = statusDelete;
        this.statusAccept = statusAccept;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Account getTenantId() {
        return tenantId;
    }

    public void setTenantId(Account tenantId) {
        this.tenantId = tenantId;
    }

    public Account getManager() {
        return Manager;
    }

    public void setManager(Account Manager) {
        this.Manager = Manager;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(int statusDelete) {
        this.statusDelete = statusDelete;
    }

    public int getStatusAccept() {
        return statusAccept;
    }

    public void setStatusAccept(int statusAccept) {
        this.statusAccept = statusAccept;
    }
    
    
}
