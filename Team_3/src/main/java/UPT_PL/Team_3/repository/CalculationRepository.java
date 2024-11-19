package UPT_PL.Team_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UPT_PL.Team_3.model.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, String> {
}