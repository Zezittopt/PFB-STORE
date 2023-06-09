package com.kreitek.store.infraestructure.persistance;

import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistance.UserPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistanceImpl implements UserPersistance {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistanceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long idUser) {
        return this.userRepository.findById(idUser);
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(long idUser) {
        this.userRepository.deleteById(idUser);

    }
    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }
}
