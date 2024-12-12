package UPT_PL.Team_3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UPT_PL.Team_3.model.LogisticsSite;

@Repository
public interface LogisticsSiteRepository extends JpaRepository<LogisticsSite, String> {
	
	@Query(value = "SELECT * FROM Team_3.Logistics_Sites where country_Id =?1", nativeQuery = true)
	List<LogisticsSite> getLogisticsSiteByCountryId(String countryId);
}