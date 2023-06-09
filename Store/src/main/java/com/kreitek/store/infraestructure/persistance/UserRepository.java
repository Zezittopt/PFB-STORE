package com.kreitek.store.infraestructure.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kreitek.store.domain.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
}
