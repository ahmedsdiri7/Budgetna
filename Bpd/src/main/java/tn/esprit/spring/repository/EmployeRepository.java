package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Direction;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;

@Repository
public interface EmployeRepository extends CrudRepository<Employe, Integer>  {
	
	@Query("SELECT e FROM Employe e WHERE e.email=:email and e.password=:password")
	public Employe getEmployeByEmailAndPassword(@Param("email")String login, @Param("password")String password);
	
	@Query("SELECT count(*) FROM Employe")
    public int countemp();
	
    @Query("SELECT nom FROM Employe")
    public List<String> employeNames();

    @Query("SELECT e FROM Employe e WHERE e.direction=:direction")
    public List<Employe> getEmployeByDirection(@Param("direction") Direction direction);
    
    @Query("Select "
			+ "DISTINCT emp from Employe emp "
			+ "join emp.direction dps  "
			+ "where dps.entreprise=:entreprise")
    public List<Employe> getAllEmployeByEntreprisec(@Param("entreprise") Entreprise entreprise);
    
    @Modifying
    @Transactional
    @Query("UPDATE Employe e SET e.email=:email1 where e.id=:employeId")
    public void mettreAjourEmailByEmployeIdJPQL(@Param("email1")String email, @Param("employeId")int employeId);

    
    @Modifying
    @Transactional
    @Query("DELETE from Contrat")
    public void deleteAllContratJPQL();
    
    @Query("select c.salaire from Contrat c join c.employe e where e.id=:employeId")
    public float getSalaireByEmployeIdJPQL(@Param("employeId")int employeId);
    
    
    @Query("Select "
			+ "DISTINCT AVG(cont.salaire) from Contrat cont "
			+ "join cont.employe emp "
			+ "join emp.direction deps "
			+ "where deps.id=:depId")
    public Double getSalaireMoyenByDirectionId(@Param("depId")int directionId);


    Optional<Employe> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("Select u from Employe u where u.email=:email")
    public Employe getEmployeByEmail(@Param("email") String email);


}
