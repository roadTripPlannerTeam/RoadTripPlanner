package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.user.*;
import com.mycompany.roadtripplanner.entities.Comment;
import com.mycompany.roadtripplanner.entities.Itinerary;
import com.mycompany.roadtripplanner.entities.User;
import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.UserRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class UserService {

    private ModelMapper mapper;
    private UserRepositoryImpl repository;
    private CommentRepositoryImpl commentRepository;
    private ItineraryRepositoryImpl itineraryRepository;

    /**
     * user service injection dependance
     * @param mapper
     * @param repository
     * @param commentRepository
     * @param itineraryRepository
     */
    public UserService(ModelMapper mapper, UserRepositoryImpl repository, CommentRepositoryImpl commentRepository, ItineraryRepositoryImpl itineraryRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.commentRepository = commentRepository;
        this.itineraryRepository = itineraryRepository;
    }

    /**
     * Méthode qui permet de créer un utilisateur
     * @param userSaveDto
     * elle transforme l'objet relationnel en objet java
     * elle effectue la requete de notre transformation par le repository
     * Elle retransforme notre objet recupéré du repository
     * @return un utilisateur sauvegardé
     */
    public UserGetSaveDTO save(UserSaveDTO userSaveDto) {
       return  mapper.map(repository.save(mapper.map(userSaveDto, User.class)), UserGetSaveDTO.class);
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
             List<Itinerary> itinerariesByUserId=  itineraryRepository.findItinerariesByUser_Id(user.getId());
             List<Comment> commentsByUserId=  commentRepository.findCommentsByUserId(user.getId());
            user.setComments(commentsByUserId);
            user.setItineraries(itinerariesByUserId);
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
   public Optional<UserDTO> find(String id) throws NoSuchElementException {
        Optional<User>userOptional = repository.findById(id);
        Optional<UserDTO> userDTO = null;
        if(userOptional.isPresent()){
           userDTO = Optional.of(mapper.map(userOptional.get(),UserDTO.class));
        }else{
            throw new NoSuchElementException("user is not found");
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
    public UserDTO update(UserUpdateDTO userUpdateDTO) {
        return mapper.map(repository.save(mapper.map(userUpdateDTO,User.class)),UserDTO.class);
    }

    /**
     * Méthode qui supprimera l'utilisateur
     * @param userDeleteDTO
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void delete(UserDeleteDTO userDeleteDTO) {
        repository.delete(mapper.map(userDeleteDTO, User.class));

    }
}
