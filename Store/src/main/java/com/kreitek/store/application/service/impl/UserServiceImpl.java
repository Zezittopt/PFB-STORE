package com.kreitek.store.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.LoginDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.mapper.ItemMapper;
import com.kreitek.store.application.mapper.UserMapper;
import com.kreitek.store.application.service.UserService;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistance.ItemPersistance;
import com.kreitek.store.domain.persistance.UserPersistance;
import com.kreitek.store.utils.Encrypter;

@Service
public class UserServiceImpl implements UserService {
    private final UserPersistance userPersistance;
    private final ItemPersistance itemPersistance;
    private final UserMapper mapper;
    private final ItemMapper itemMapper;

    public UserServiceImpl(UserPersistance userPersistance, ItemPersistance itemPersistance, UserMapper mapper, ItemMapper itemMapper) 
    {
        this.userPersistance = userPersistance;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userPersistance.getAllUsers();
        return mapper.toDto(users);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(long idUser) {
        return this.userPersistance.getUserById(idUser).map(mapper::toDto);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        String password = userDTO.getPassword();
        String passwordEncrypt = Encrypter.getMD5(password);
        userDTO.setPassword(passwordEncrypt);
        User userSaved = this.userPersistance.saveUser(this.mapper.toEntity(userDTO));
        return this.mapper.toDto(userSaved);
    }

    @Override
    public void deleteUser(long idUser) {
        this.userPersistance.deleteUser(idUser);
    }

    @Override
       public LoginDTO loginAuthentication(LoginDTO loginDTO) {
        String userName = loginDTO.getUserName();
        User user = this.userPersistance.getUserByUserName(userName);
        String password = loginDTO.getPassword();
        String passwordEncrypt = Encrypter.getMD5(password);

        if (passwordEncrypt.equals(user.getPassword())) {
            return loginDTO;
        } else {
            return null;
        }
    }
    @Override
    public boolean UserNameExist(String userName) {
        User user = this.userPersistance.getUserByUserName(userName);
        if (user == null) {
            return false;
        }
        return true;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Long> getFavoritesByUserName(String userName) {
        UserDTO userDTO = mapper.toDto(userPersistance.getUserByUserName(userName));
        if (userDTO != null) {
            return userDTO.getFavorites().stream()
                    .map(ItemDTO::getId)
                    .collect(Collectors.toList());
        } else {
            List<Long> empty = new ArrayList<>();
            return empty;
        }
    }

    @Override
    @Transactional
    public boolean insertFavoriteByUserIdAndByItemid(String userName, Long itemId) {
        UserDTO userDto = this.mapper.toDto(userPersistance.getUserByUserName(userName));
        if (userDto != null) {
            Optional<Item> items = itemPersistance.getItemById(itemId);
            if (items.isPresent()) {
                Item itemNewFav = items.get();
                userDto.getFavorites().add(itemMapper.toDto(itemNewFav));
                userPersistance.saveUser(mapper.toEntity(userDto));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteFavoriteByItemId(String userName, Long itemId) {
        User user = userPersistance.getUserByUserName(userName);
        if (user != null) {
            Optional<Item> items = itemPersistance.getItemById(itemId);
            if (items.isPresent()) {
                Item itemRemoveFav = items.get();
                user.getFavorites().remove(itemRemoveFav);
                userPersistance.saveUser(user);
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

}
