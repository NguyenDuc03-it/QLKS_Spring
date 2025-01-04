package com.example.qlks_springboot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id  ;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "sex")
    private String sex;

    @Column(name = "cccd")
    private String cccd;

    @Column(name = "phone")
    private String phone;

    public Customer() {
    }

    public Customer(String fullname, String sex, String cccd, String phone) {
        this.fullname = fullname;
        this.sex = sex;
        this.cccd = cccd;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Booking> bookings;
}
