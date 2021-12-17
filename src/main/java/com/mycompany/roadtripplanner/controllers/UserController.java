package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import com.mycompany.roadtripplanner.dtos.user.UserSaveDTO;
import com.mycompany.roadtripplanner.dtos.user.UserUpdateDTO;
import com.mycompany.roadtripplanner.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping()
    public UserDTO save(@RequestBody UserSaveDTO user){
        return service.save(user);
    }

    @GetMapping()
    public List<UserDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO>find(@PathVariable String id ){
        UserDTO userDto = service.find(id);
        if(userDto ==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userDto);

    }



    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody UserUpdateDTO userUpdateDTO){
        return  ResponseEntity.ok(service.update(userUpdateDTO));
    }

   /* @DeleteMapping()
    public ResponseEntity<Long>deleteById(@RequestBody UserDeleteDTO userDeleteDTO){
        Long response =this.service.deleteById(userDeleteDTO);
        if(response == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }*/
}
