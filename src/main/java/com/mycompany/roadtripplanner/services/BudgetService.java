package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetSaveDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetUpdateDTO;
import com.mycompany.roadtripplanner.entities.Budget;
import com.mycompany.roadtripplanner.repositories.BudgetRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BudgetService {

    BudgetRepositoryImpl repository;
    ModelMapper mapper;

    public BudgetService(ModelMapper mapper, BudgetRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Constructeur de BudgetService
     * Injection de dépendance
     * @param repository
     * @param mapper
     */
    public BudgetService(BudgetRepositoryImpl repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Va retourner une liste de tous les budgets
     * @return liste budgetDTOs
     */
    public List<BudgetDTO> findAll(){
        List<BudgetDTO> budgetDTOS = new ArrayList<>();
        this.repository.findAll().forEach(budget -> {
            budgetDTOS.add(mapper.map(budget, BudgetDTO.class));
        });
        return budgetDTOS;
    }

    /**
     * Va retourner un budget grâce à son id
     * @param id
     * @return l'objet budgetDTO
     */
    public Optional<BudgetDTO> find(String id) throws NoSuchElementException {
        Optional<Budget> budgetOptional = repository.findById(id);
        Optional<BudgetDTO> budgetDTO = null;
        if(budgetOptional.isPresent()){
            budgetDTO = Optional.of(mapper.map(budgetOptional.get(), BudgetDTO.class));
        } else {
            throw new NoSuchElementException("Ce budget n'existe pas");
        }
        return  budgetDTO;
    }

    /**
     * Va créer un nouvel objet budget
     * @param obj
     * @return l'objet budget créé
     */
    public BudgetDTO save(BudgetSaveDTO obj){
        Budget budgetToSave = mapper.map(obj, Budget.class);
        Budget budget = repository.save(budgetToSave);
        BudgetDTO budgetSaved = mapper.map(budget, BudgetDTO.class);
        return budgetSaved;
    }

    /**
     * Va modifier l'objet budget
     * @param obj
     * @return l'objet budget modifié
     */
    public BudgetDTO update(BudgetUpdateDTO obj){
        Budget bugetToUpdate = mapper.map(obj, Budget.class);
        Budget budget = repository.save(bugetToUpdate);
        BudgetDTO budgetSaved = mapper.map(budget, BudgetDTO.class);
        return budgetSaved;
    }

    /**
     * Va supprimer un objet budget grâce à son Id
     * @param id
     */
    public void delete(String id){
        repository.deleteById(id);
    }

}
