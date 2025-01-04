package com.example.qlks_springboot.entity;

import javax.validation.constraints.NotBlank;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;


    @Column(name = "room_number")
    private String roomNumber;


    @Column(name = "room_type")
    private String roomType;


    @Column(name = "price")
    private String price;


    @Column(name = "status")
    private String status;

    public Room() {
    }



    @OneToMany(mappedBy = "room", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Booking> bookings;


    public Room(String roomNumber, String roomType, String price, String status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
