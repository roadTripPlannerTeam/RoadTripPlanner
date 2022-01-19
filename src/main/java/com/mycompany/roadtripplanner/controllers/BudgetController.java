package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetDeleteDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetSaveDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetUpdateDTO;
import com.mycompany.roadtripplanner.services.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    BudgetService service;
    ModelMapper mapper;

    /**
     * Constructeur de BudgetController
     * Injection de dépendance
     * @param service
     * @param mapper
     */
    public BudgetController(BudgetService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Créé la route pour recevoir tous les budgets
     * @return liste de budget
     */
    @GetMapping
    public List<BudgetDTO> findAll(){
        return service.findAll();
    }

    /**
     * Créé la route pour recevoir un budget selon son Id
     * @param id
     * @return un statut Http 200 ok
     */
    @GetMapping("{id}")
    public ResponseEntity<BudgetDTO> find(@PathVariable String id){
        try{
            return ResponseEntity.ok(service.find(id).get());
        } catch(NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Créé la route pour créer un objet budget
     * @param budgetSaveDTO
     * @return un statut Http 201 Created
     */
    @PostMapping
    public ResponseEntity<BudgetDTO> save(@RequestBody BudgetSaveDTO budgetSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(budgetSaveDTO));
    }

    /**
     * Créé la route pour modifier un objet budget
     * @param budgetUpdateDTO
     * @return un statut Http 200 ok
     */
    @PutMapping
    public ResponseEntity<BudgetDTO> update(@RequestBody BudgetUpdateDTO budgetUpdateDTO){
        return ResponseEntity.ok(service.update(budgetUpdateDTO));
    }

    /**
     * Créé la route pour supprimer un objet budget via son objet
     * @param budgetDeleteDTO
     * @return un statut Http 200 ok true
     */
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody BudgetDeleteDTO budgetDeleteDTO){
        service.delete(budgetDeleteDTO);
        return ResponseEntity.ok("budget bien supprimé ");
    }
}