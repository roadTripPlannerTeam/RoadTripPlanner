package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.comment.CommentDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentSaveDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentUpdateDTO;
import com.mycompany.roadtripplanner.entities.Comment;
import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private ModelMapper mapper;
    private CommentRepositoryImpl repository;

    /**
     * Constructeur pour le modèle mapper et  l'interface repository
     * @param mapper
     * @param repository
     */
    public CommentService(ModelMapper mapper, CommentRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Méthode qui permet de créer un commentaire
     * @param comment
     * elle transforme l'objet relationnel en objet java
     * elle effectue la requete de notre transformation par le repository
     * Elle retransforme notre objet recupéré du repository
     * @return un commentaire sauvegardé
     */
    public CommentDTO save(CommentSaveDTO obj) {
        Comment commentToSave = mapper.map(obj, Comment.class);
        Comment comment = repository.save(commentToSave);
        CommentDTO commentSaved = mapper.map(comment, CommentDTO.class);
        return commentSaved;
    }

    /**
     *Méthode Qui permet de récupérer une liste de commentairte
     * Elle crée une liste de commentaire
     * Elle effectue une boucle pour transforme l'ensemble des commentaires trouver dans le repository
     * tant qu'il y en à pour les inserer dans la liste.
     * @return une liste avec l'ensemble des commentaires
     */
    public List<CommentDTO> findAll(){
        List<CommentDTO>comments = new ArrayList<>();
        System.out.println("hello");
        repository.findAll().forEach(comment -> {
            System.out.println(comment);
            comments.add(mapper.map(comment,CommentDTO.class));
        });
        return comments;
    }

    /**
     * Méthode qui permet de récupere les informations d'un commentaire
     * @param id
     * La requête va recupérer le commentaire qui possède cette id passé en paramètre
     * On instancie le commentDTO a null puis dans notre condition Si notre optionnal n'est vide
     * nous passerons ses valeur dans le CommentDto
     * @return les informations du commentaire
     */
    public Comment find(String id) {
        Optional<Comment> commentOptional = repository.findById(id);
        Comment commentDTO=null;
        if(commentOptional.isPresent()){
            commentDTO= commentOptional.get();
        }
        return commentDTO;
    }

    /**
     * Méthode permettant de modifier les informations d'un commentaire
     * @param commentUpdateDTO
     * elle transformera les informations modifié qui les enverra au repository qui les sauvegardera
     * Elle transformera l'objet recupérer du repository
     * @return un commentaire avec les informations modifier
     */
    public Object update(CommentUpdateDTO commentUpdateDTO) {
        Comment commentToSave = mapper.map(commentUpdateDTO,Comment.class);
        Comment commentSaving = repository.save(commentToSave);
        CommentDTO commentRetour= mapper.map(commentSaving,CommentDTO.class);
        return commentRetour;
    }

    /**
     * Méthode qui supprimera le commentaire
     * @param id
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void deleteById(String id) {
        repository.deleteById(id);

    }
}
