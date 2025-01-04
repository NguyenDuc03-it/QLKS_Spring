package com.example.qlks_springboot.repository;

import com.example.qlks_springboot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByPosition(String position);
}
