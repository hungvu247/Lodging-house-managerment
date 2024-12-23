/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class ServiceOfLodgingHouse {
    private int serviceId, lodgingHouseId;
    private double price;

    public ServiceOfLodgingHouse() {
    }

    public ServiceOfLodgingHouse(int serviceId, int lodgingHouseId, double price) {
        this.serviceId = serviceId;
        this.lodgingHouseId = lodgingHouseId;      
        this.price = price;
    }

    public int getLodgingHouseId() {
        return lodgingHouseId;
    }

    public void setLodgingHouseId(int lodgingHouseId) {
        this.lodgingHouseId = lodgingHouseId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
       
}

