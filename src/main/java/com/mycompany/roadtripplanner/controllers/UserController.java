package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.user.*;
import com.mycompany.roadtripplanner.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    /**
     * Controlleur qui au sevice de créer un utilisateur avec les données du Request body transmit
     * @param user
     * @return l'utilisateur sauvegardé
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserGetSaveDTO save(@RequestBody UserSaveDTO user){
        return service.save(user);
    }

    /**
     * Controlleur qui demande au service une list d'utilisateur
     * @return une liste d'utilisateur
     */
    @GetMapping()
    public List<UserDTO> findAll(){
        return service.findAll();
    }

    /**
     * Constructeur Qui demande au servie de trouver un utilisateur par l'id donnée en path variable
     * @param id qui doit être récupere par le path varible
     * @return un message d'erreur si l'id n'existe pas
     * @return l'utisateur ainsi que c'est information si l'utilisateur existe
     */
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id ){
        try{
            return ResponseEntity.ok(service.find(id).get());
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Controlleur qui demande au service de modifier un utilisateur avec
     * les informations donnée par le request body
     * @param userUpdateDTO
     * @return une reponse du service si la modification est un succès avec les nouvelles informations
     */
    @PutMapping()
    public ResponseEntity<UserDTO> update(@RequestBody UserUpdateDTO userUpdateDTO){
        return  ResponseEntity.ok(service.update(userUpdateDTO));
    }

    /**
     * Controlleur qui demande au service de supprimer un utilisateur par l'id donné dans le path variable
     * @param userDeleteDTO
     * @return une réponse true si l'utilisateur à bien était suppprimé
     */
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody UserDeleteDTO userDeleteDTO){
        service.delete(userDeleteDTO);
        return ResponseEntity.ok("user is delete with success");
    }
}
