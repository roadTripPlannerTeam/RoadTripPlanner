package com.mycompany.roadtripplanner.services;


import com.mycompany.roadtripplanner.dtos.commentResponse.*;
import com.mycompany.roadtripplanner.entities.CommentResponse;
import com.mycompany.roadtripplanner.repositories.CommentResponseRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentResponseService {

    private ModelMapper mapper;
    private CommentResponseRepositoryImpl repository;

    public CommentResponseService(ModelMapper mapper, CommentResponseRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Méthode qui permet de créer un commentaire
     * @param commentResponseSaveDTO
     * elle transforme l'objet relationnel en objet java
     * elle effectue la requete de notre transformation par le repository
     * Elle retransforme notre objet recupéré du repository
     * @return un commentaire sauvegardé
     */
    public CommentResponseGetSaveDTO save(CommentResponseSaveDTO commentResponseSaveDTO) {
        return mapper.map(repository.save(mapper.map(commentResponseSaveDTO, CommentResponse.class)), CommentResponseGetSaveDTO.class);

    }

    /**
     *Méthode Qui permet de récupérer une liste de commentairte
     * Elle crée une liste de commentaire
     * Elle effectue une boucle pour transformer l'ensemble des commentaires trouver dans le repository
     * tant qu'il y en à pour les inserer dans la liste.
     * @return une liste avec l'ensemble des commentaires
     */
    public List<CommentResponseDTO> findAll(){
        List<CommentResponseDTO>comments = new ArrayList<>();
        repository.findAll().forEach(comment -> {
            System.out.println(comment);
            comments.add(mapper.map(comment,CommentResponseDTO.class));
        });
        return comments;
    }

    /**
     * Méthode qui permet de récupere les informations d'un commentaire
     * @param id
     * La requête va recupérer le commentaire qui possède cette id passé en paramètre
     * On instancie le commentDTO a null puis dans notre condition Si notre optionnal n'est vide
     * nous passerons ses valeur dans le CommentResponseDto
     * @return les informations du commentaire
     */
    public Optional<CommentResponseDTO> find(String id) {
        Optional<CommentResponse> commentOptional = repository.findById(id);
        Optional<CommentResponseDTO> commentResponseDTO=null;
        if(commentOptional.isPresent()){
            commentResponseDTO= Optional.of(mapper.map(commentOptional.get(),CommentResponseDTO.class));
        }
        return commentResponseDTO;
    }

    /**
     * Méthode permettant de modifier les informations d'un commentaire
     * @param commentUpdateDTO
     * elle transformera les informations modifié qui les enverra au repository qui les sauvegardera
     * Elle transformera l'objet recupérer du repository
     * @return un commentaire avec les informations modifier
     */
    public CommentResponseDTO update(CommentResponseUpdateDTO commentUpdateDTO) {
        CommentResponse commentToSave = mapper.map(commentUpdateDTO,CommentResponse.class);
        CommentResponse commentSaving = repository.save(commentToSave);
        CommentResponseDTO commentRetour= mapper.map(commentSaving,CommentResponseDTO.class);
        return commentRetour;
    }

    /**
     * Méthode qui supprimera le commentaire
     * @param commentResponseDeleteDTO
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void delete(CommentResponseDeleteDTO commentResponseDeleteDTO) {
        repository.delete(mapper.map(commentResponseDeleteDTO,CommentResponse.class));
    }
}
