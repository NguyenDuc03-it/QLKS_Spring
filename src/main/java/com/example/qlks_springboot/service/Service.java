package com.example.qlks_springboot.service;

import com.example.qlks_springboot.entity.*;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<User> getAllUsers();
    List<Room> getAllRoom();
    List<Booking> getAllBooking();
    List<Payment> getAllPayment();
    List<Customer> getAllCustomer();
    User loginUser(String username, String password);
    Optional<User> findUserById(Long id);

    // ROOM
    void saveRoom(Room room);
    List<Room> getAllByOrderByRoomNumberAsc();
    void updateRoom(Room room);
    void deleteRoom(Long id);
    List<Room> searchRoom(String searchTerm);
}
