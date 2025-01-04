package com.example.qlks_springboot.service.impl;

import com.example.qlks_springboot.entity.*;
import com.example.qlks_springboot.repository.*;
import com.example.qlks_springboot.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Room> getAllRoom() {
        return (List<Room>) roomRepository.findAll();
    }

    @Override
    public List<Booking> getAllBooking() {
        return (List<Booking>) bookingRepository.findAll();
    }

    @Override
    public List<Payment> getAllPayment() {
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public User loginUser(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        if(!users.isEmpty()) {
            return users.get(0);
        }
        else return null;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void saveRoom(Room room) {
        Optional<Room> existingRoom = roomRepository.findByRoomNumber(room.getRoomNumber());
        if(existingRoom.isPresent()) {
            throw new RuntimeException("Phòng đã tồn tại!");
        }
        else{
            roomRepository.save(room);
        }
    }

    @Override
    public void updateRoom(Room updatedRoom) {
        Room existingRoom = roomRepository.findById(updatedRoom.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Chỉ cập nhật giá tiền nếu giá tiền mới được cung cấp
        if (!Objects.equals(updatedRoom.getPrice(), "")) {
            existingRoom.setPrice(updatedRoom.getPrice());
        }

        // Chỉ cập nhật loại phòng nếu loại phòng mới được cung cấp
        if (!Objects.equals(updatedRoom.getRoomType(), "")) {
            existingRoom.setRoomType(updatedRoom.getRoomType());
        }

        if (!Objects.equals(updatedRoom.getStatus(), "")) {
            existingRoom.setStatus(updatedRoom.getStatus());
        }


        roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<Room> searchRoom(String searchTerm) {
        return roomRepository.findByRoomNumberContainingOrRoomTypeContainingOrPriceContainingOrStatusContaining(searchTerm, searchTerm, searchTerm, searchTerm);
    }

    @Override
    public List<Room> getAllByOrderByRoomNumberAsc() {
        return roomRepository.findAllByOrderByRoomNumber();
    }


}
