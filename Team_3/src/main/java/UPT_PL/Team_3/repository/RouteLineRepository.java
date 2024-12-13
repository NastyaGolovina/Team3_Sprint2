package UPT_PL.Team_3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UPT_PL.Team_3.model.RouteLine;
import jakarta.transaction.Transactional;

@Repository
public interface RouteLineRepository extends JpaRepository<RouteLine, String> {

	@Query(value = "SELECT * FROM team_3.Route_Line where calculation_id =?1", nativeQuery = true)
	List<RouteLine> getRouteLineByCalculationID(String calculationId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM team_3.Route_Line WHERE calculation_id = ?1", nativeQuery = true)
	void deleteRouteLineByCalculationID(String calculationId);

//	@Query(value = "SELECT COUNT(*) > 0 FROM team_3.Route_Line WHERE calculation_id = ?1", nativeQuery = true)
//	boolean existsByCalculationId(long calculationId);

	@Query(value = "SELECT * FROM team_3.Route_Line WHERE Country_Sender_Id = ?1 OR Country_Receiver_Id = ?1", nativeQuery = true)
	List<RouteLine> existsByCountryId(String countryId);

	@Query(value = "SELECT * FROM team_3.Route_Line WHERE Transport_Id = ?1", nativeQuery = true)
	List<RouteLine> existsByTransportId(String transportId);

	@Query(value = "SELECT * FROM team_3.Route_Line WHERE Logistics_Site_Sender_Id = ?1 OR Logistics_Site_Receiver_Id = ?1", nativeQuery = true)
	List<RouteLine> getRouteLineBySiteId(String siteId);

	@Query(value = "SELECT * FROM team_3.Route_Line WHERE Product_Id = ?1", nativeQuery = true)
	List<RouteLine> getRouteLineByProductId(String productId);
}