package UPT_PL.Team_3.repository;

import UPT_PL.Team_3.model.LogisticsSupplyChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsSupplyChainRepository extends JpaRepository<LogisticsSupplyChain, String> {
}
