package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import com.mycompany.roadtripplanner.dtos.user.UserDeleteDTO;
import com.mycompany.roadtripplanner.dtos.user.UserSaveDTO;
import com.mycompany.roadtripplanner.dtos.user.UserUpdateDTO;
import com.mycompany.roadtripplanner.entities.User;
import com.mycompany.roadtripplanner.repositories.UserRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private ModelMapper mapper;
    private UserRepositoryImpl repository;

    public UserService(ModelMapper mapper, UserRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    /**
     *
     * @return
     */
    public List<UserDTO>findAll(){
        List<UserDTO>users = new ArrayList<>();
         repository.findAll().forEach(user -> {
             users.add(mapper.map(user,UserDTO.class));
         });
        return users;
    }

    /**
     * Méthode qui permet de récupere les informations d'un utilisateur
     * @param id
     * @return les informations de l'utilisateur
     */
   public UserDTO find(String id) {
        Optional<User>user = repository.findById(id);
        UserDTO userDTO=null;
        if(user.isPresent()){
            userDTO= mapper.map(user,UserDTO.class);
        }
        return userDTO;
    }

    /**
     * Méthode qui permet de créer un utilisateur
     * @param user
     * @return un utilisateur sauvegardé
     */
    public UserDTO save(UserSaveDTO obj) {
        User userToSave = mapper.map(obj, User.class);
        User user = repository.save(userToSave);
        UserDTO userSaved = mapper.map(user, UserDTO.class);
        return userSaved;
    }

    /**
     * Méthode qui permet de modifier les informations de l'utilisateur
     * @param userUpdateDTO
     * @return
     */
    public Object update(UserUpdateDTO userUpdateDTO) {
        User userToSave = mapper.map(userUpdateDTO,User.class);
        User userSaving = repository.save(userToSave);
        UserDTO userRetour= mapper.map(userSaving,UserDTO.class);
        return userRetour;
    }

}
