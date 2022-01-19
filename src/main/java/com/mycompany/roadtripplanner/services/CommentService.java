package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.comment.CommentDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentGetSaveDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentDeleteDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentSaveDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentUpdateDTO;
import com.mycompany.roadtripplanner.entities.Comment;
import com.mycompany.roadtripplanner.entities.CommentResponse;
import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.CommentResponseRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {

    private ModelMapper mapper;
    private CommentRepositoryImpl repository;
    private CommentResponseRepositoryImpl commentResponseRepository;


    public CommentService(ModelMapper mapper, CommentRepositoryImpl repository, CommentResponseRepositoryImpl commentResponseRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.commentResponseRepository = commentResponseRepository;
    }

    /**
     * Méthode qui permet de créer un commentaire
     * elle transforme l'objet relationnel en objet java
     * elle effectue la requete de notre transformation par le repository
     * Elle retransforme notre objet recupéré du repository
     * @param commentSaveDTO
     * @return un commentaire sauvegardé
     */
    public CommentGetSaveDTO save(CommentSaveDTO commentSaveDTO) {
        return mapper.map(repository.save(mapper.map(commentSaveDTO, Comment.class)), CommentGetSaveDTO.class);

    }

    /**
     *Méthode Qui permet de récupérer une liste de commentairte
     * Elle crée une liste de commentaire
     * Elle effectue une boucle pour transformer l'ensemble des commentaires trouver dans le repository
     * tant qu'il y en à pour les inserer dans la liste.
     * @return une liste avec l'ensemble des commentaires
     */
    public List<CommentDTO> findAll(){
        List<CommentDTO>comments = new ArrayList<>();
        repository.findAll().forEach(comment -> {
           List<CommentResponse> commentResponses = commentResponseRepository.findCommentResponsesByComment_Id(comment.getId());
            comment.setCommentsResponse(commentResponses);
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
    public Optional<CommentDTO> find(String id)throws NoSuchElementException {
        Optional<Comment> commentOptional = repository.findById(id);
        Optional<CommentDTO>commentDTO = null;

        if(commentOptional.isPresent()){
            commentDTO =Optional.of(mapper.map(commentOptional.get(),CommentDTO.class));
        }else {
            throw new NoSuchElementException("comment is not found");
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
    public CommentDTO update(CommentUpdateDTO commentUpdateDTO) {
        Comment commentToSave = mapper.map(commentUpdateDTO,Comment.class);
        Comment commentSaving = repository.save(commentToSave);
        CommentDTO commentRetour= mapper.map(commentSaving,CommentDTO.class);
        return commentRetour;
    }

    /**
     * Méthode qui supprimera le commentaire
     * @param commentDeleteDTO
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void delete(CommentDeleteDTO commentDeleteDTO) {
        repository.delete(mapper.map(commentDeleteDTO,Comment.class));
    }
}
