package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.BudgetInitial;

@Repository
public interface BudgetInitialRepository extends CrudRepository<BudgetInitial, Integer> {

}
