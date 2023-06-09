package com.kreitek.store.infraestructure.rest;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.LoginDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/users", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDTO){
        boolean UserNameExist = this.userService.UserNameExist(userDTO.getUserName());
        if (UserNameExist) {
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }else{
            userDTO = this.userService.saveUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/users/login", produces = "application/json", consumes = "application/json")
    ResponseEntity<LoginDTO> logintUser(@RequestBody LoginDTO loginDTO) {

        boolean UserNameExist = this.userService.UserNameExist(loginDTO.getUserName());

        if (UserNameExist) {
            LoginDTO loginExist = this.userService.loginAuthentication(loginDTO);
            if (loginExist == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PutMapping(value= "/users/{userName}/favorites/{itemId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<ItemDTO>> insertItemsInUsers(@PathVariable Long itemId, @PathVariable String userName){
       boolean insertOk = userService.insertFavoriteByUserIdAndByItemid(userName, itemId);
       if(insertOk){
           return new ResponseEntity<>(HttpStatus.OK);
       }else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @CrossOrigin
    @DeleteMapping(value="/users/{userName}/favorites/remove/{itemId}")
    public ResponseEntity<Void> deleteFavoriteByUserNameAndByItemId(@PathVariable String userName, @PathVariable Long itemId){
        boolean deleteOk = userService.deleteFavoriteByItemId(userName, itemId);
        if(deleteOk){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping(value="/users/{userName}/favorites")
    public ResponseEntity<List<Long>> getFavoritesByUserName(@PathVariable String username){
        List<Long> favoritesList = this.userService.getFavoritesByUserName(username);
        return new ResponseEntity<>(favoritesList, HttpStatus.OK);
    }
}
