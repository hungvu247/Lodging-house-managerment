/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Service {
    private int ServiceId;
    private String ServiceName;

    public Service(int ServiceId, String ServiceName) {
        this.ServiceId = ServiceId;
        this.ServiceName = ServiceName;
    }

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int ServiceId) {
        this.ServiceId = ServiceId;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }
    
    
}
