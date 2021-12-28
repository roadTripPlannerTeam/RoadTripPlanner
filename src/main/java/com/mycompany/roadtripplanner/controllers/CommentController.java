package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.comment.CommentDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentDeleteDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentGetSaveDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentSaveDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentUpdateDTO;
import com.mycompany.roadtripplanner.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService service;

    public CommentController(CommentService service){
        this.service = service;
    }

    /**
     * Controlleur qui demande au service de créer un Commentaire
     * avec les données du Request body transmit
     * @param comment
     * @return l'utilisateur sauvegardé
     */
    @PostMapping()
    public CommentGetSaveDTO save(@RequestBody CommentSaveDTO comment){
        return service.save(comment);
    }

    /**
     * Controlleur qui demande au service une list de commentaire
     * @return une liste de commentaire
     */
    @GetMapping()
    public List<CommentDTO> findAll(){
        return service.findAll();
    }

    /**
     * Constructeur Qui demande au service de trouver un commentaire par l'id donnée en path variable
     * @param id qui doit être récupere par le path varible
     * @return un message d'erreur si l'id n'existe pas
     * @return le commentaire avec c'est information si le commentaire existe
     */
    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> find(@PathVariable String id ){
        try{
            return ResponseEntity.ok(service.find(id).get());
        }catch(NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Controlleur qui demande au service de modifier un commentaire avec
     * les informations donnée par le request body
     * @param commentUpdateDTO
     * @return une reponse du service si la modification est un succès avec les nouvelles informations
     */
    @PutMapping()
    public ResponseEntity<CommentDTO> update(@RequestBody CommentUpdateDTO commentUpdateDTO){
        return  ResponseEntity.ok(service.update(commentUpdateDTO));
    }

    /**
     * Controlleur qui demande au service de supprimer un commentaire par l'id donné dans le path variable
     * @param commentDeleteDTO
     * @return une réponse true si le commentaire à bien était suppprimé
     */

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody CommentDeleteDTO commentDeleteDTO){
        service.delete(commentDeleteDTO);
        return ResponseEntity.ok("Comment is delete with success");
    }
}
