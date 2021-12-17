package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import com.mycompany.roadtripplanner.dtos.user.UserSaveDTO;
import com.mycompany.roadtripplanner.dtos.user.UserUpdateDTO;
import com.mycompany.roadtripplanner.entities.User;
import com.mycompany.roadtripplanner.repositories.UserRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private ModelMapper mapper;
    private UserRepositoryImpl repository;

    /**
     * Constructeur pour le modèle mapper et je l'interface repository
     * @param mapper
     * @param repository
     */
    public UserService(ModelMapper mapper, UserRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Méthode qui permet de créer un utilisateur
     * @param user
     * elle transforme l'objet relationnel en objet java
     * elle effectue la requete de notre transformation par le repository
     * Elle retransforme notre objet recupéré du repository
     * @return un utilisateur sauvegardé
     */
    public UserDTO save(UserSaveDTO obj) {
        User userToSave = mapper.map(obj, User.class);
        User user = repository.save(userToSave);
        UserDTO userSaved = mapper.map(user, UserDTO.class);
        return userSaved;
    }

    /**
     *Méthode Qui permet de récupérer une liste d'utilisateur
     * Elle crée une liste d'utilisateur
     * Elle effectue une boucle pour transforme l'ensemble des utilisateurs trouver dans le repository
     * tant qu'il y en à pour les inserer dans la liste.
     * @return une liste avec l'ensemble des utilisateurs
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
     * La requête va recupérer l'utilisateur qui possède cette id passé en parametre
     * On instancie le userDTO a null puis dans notre condition Si notre optionnal n'est vide
     * nous passerons ses valeur dans le UserDto
     * @return les informations de l'utilisateur
     */
   public User find(String id) {
        Optional<User>userOptional = repository.findById(id);
        User userDTO=null;
        if(userOptional.isPresent()){
            userDTO= userOptional.get();
        }
        return userDTO;
    }

    /**
     * Méthode permettant de modifier les informations d'un utlisdateur
     * @param userUpdateDTO
     * elle transformera les informations modifié qui les enverra au repository qui les sauvegardera
     * Elle transformera l'objet recupérer du repository
     * @return un utilisateur avec les informations modifier
     */
    public Object update(UserUpdateDTO userUpdateDTO) {
        User userToSave = mapper.map(userUpdateDTO,User.class);
        User userSaving = repository.save(userToSave);
        UserDTO userRetour= mapper.map(userSaving,UserDTO.class);
        return userRetour;
    }

    /**
     * Méthode qui supprimera l'utilisateur
     * @param id
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void deleteById(String id) {
        repository.deleteById(id);

    }
}
