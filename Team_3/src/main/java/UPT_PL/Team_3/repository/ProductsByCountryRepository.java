package UPT_PL.Team_3.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UPT_PL.Team_3.model.ProductsByCountry;

@Repository
public interface ProductsByCountryRepository extends JpaRepository<ProductsByCountry, String>{
	@Query(value = "SELECT * FROM team_3.Products_by_Country where Country_Id =?1", nativeQuery = true)
	List<ProductsByCountry> getProductsByCountryByCountryID(String countryId);

}




