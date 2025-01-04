package com.example.qlks_springboot.repository;

import com.example.qlks_springboot.entity.Booking;
import com.example.qlks_springboot.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);
    List<Room> findAllByOrderByRoomNumber();
    List<Room> findByRoomNumberContainingOrRoomTypeContainingOrPriceContainingOrStatusContaining(String roomNumber, String roomType, String price, String status);
}
