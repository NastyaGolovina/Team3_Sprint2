package UPT_PL.Team_3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UPT_PL.Team_3.model.RouteLine;

@Repository
public interface RouteLineRepository extends JpaRepository<RouteLine, String> {

	@Query(value = "SELECT * FROM team_3.Route_Line where calculationId =?1", nativeQuery = true)
	List<RouteLine> getRouteLineByCalculationID(long calculationId);

	@Query(value = "DELETE FROM team_3.Route_Line WHERE calculationId = ?1", nativeQuery = true)
	void deleteRouteLineByCalculationID(long calculationId);

	@Query(value = "SELECT COUNT(*) > 0 FROM team_3.Route_Line WHERE calculationId = ?1", nativeQuery = true)
	boolean existsByCalculationId(long calculationId);

	@Query(value = "SELECT COUNT(*) > 0 FROM team_3.Route_Line WHERE countrySenderId = ?1 OR countryReceiverId = ?1", nativeQuery = true)
	boolean existsByCountryId(String countryId);

	@Query(value = "SELECT COUNT(*) > 0 FROM team_3.Route_Line WHERE transportId = ?1", nativeQuery = true)
	boolean existsByTransportId(String transportId);

	@Query(value = "SELECT COUNT(*) > 0 FROM team_3.Route_Line WHERE originSiteId = ?1 OR destinationSiteId = ?1", nativeQuery = true)
	boolean getRouteLineBySiteId(String siteId);

	@Query(value = "SELECT COUNT(*) > FROM team_3.Route_Line WHERE productId = ?1", nativeQuery = true)
	boolean getRouteLineByProductId(String productId);
}