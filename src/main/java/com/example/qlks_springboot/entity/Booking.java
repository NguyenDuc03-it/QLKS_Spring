package com.example.qlks_springboot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "check_in_date")
    private String check_in_date;

    @Column(name = "check_out_date")
    private String check_out_date;

    @Column(name = "status")
    private String status;

    public Booking() {
    }

    public Booking(Customer customer, Room room, String check_in_date, String check_out_date, String status) {
        this.customer = customer;
        this.room = room;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(String check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(String check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Payment> payments;
}
