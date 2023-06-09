package com.kreitek.store.domain.persistance;

import com.kreitek.store.domain.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserPersistance {

    List<User> getAllUsers();
    Optional<User> getUserById(long idUser);
    User saveUser(User user);
    void deleteUser(long idUser);
    User getUserByUserName(String userName);
}
