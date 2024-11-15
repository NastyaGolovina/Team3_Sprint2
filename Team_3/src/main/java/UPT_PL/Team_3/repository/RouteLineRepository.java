package UPT_PL.Team_3.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UPT_PL.Team_3.model.RouteLine;

@Repository
public interface RouteLineRepository extends JpaRepository<RouteLine, Long> {
	
	@Query(value = "SELECT * FROM team_3.routeline where calculationId =?1", nativeQuery = true)
	List<RouteLine> getRouteLineByCalculationID(long calculationId);
}