/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class ServiceOfRoom {

    private ServiceOfLodgingHouse serviceOfLodgingHouse;
    private Room room;

    public ServiceOfRoom(ServiceOfLodgingHouse serviceOfLodgingHouse, Room room) {
        this.serviceOfLodgingHouse = serviceOfLodgingHouse;
        this.room = room;
    }

    public ServiceOfLodgingHouse getServiceOfLodgingHouse() {
        return serviceOfLodgingHouse;
    }

    public void setServiceOfLodgingHouse(ServiceOfLodgingHouse serviceOfLodgingHouse) {
        this.serviceOfLodgingHouse = serviceOfLodgingHouse;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    

    
}
