package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.LoginDTO;
import com.kreitek.store.application.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(long idUser);

    UserDTO saveUser(UserDTO user);

    void deleteUser(long idUser);

    boolean UserNameExist(String userName);

    LoginDTO loginAuthentication(LoginDTO loginDTO);
    
    List<Long> getFavoritesByUserName(String userName);

    boolean insertFavoriteByUserIdAndByItemid(String userName, Long itemId);

    boolean deleteFavoriteByItemId(String userName, Long itemId);
}
