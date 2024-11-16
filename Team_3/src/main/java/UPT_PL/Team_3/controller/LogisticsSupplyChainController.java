package UPT_PL.Team_3.controller;

import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3.repository.LogisticsSupplyChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticsSupplyChainService {

    private final LogisticsSupplyChainRepository supplyChainRepository;

    @Autowired
    public LogisticsSupplyChainService(LogisticsSupplyChainRepository supplyChainRepository) {
        this.supplyChainRepository = supplyChainRepository;
    }

    public List<LogisticsSupplyChain> getAllSupplyChains() {
        return supplyChainRepository.findAll();
    }

    public Optional<LogisticsSupplyChain> getSupplyChainById(String id) {
        return supplyChainRepository.findById(id);
    }

    public LogisticsSupplyChain createSupplyChain(LogisticsSupplyChain supplyChain) {
        return supplyChainRepository.save(supplyChain);
    }

    public void deleteSupplyChain(String id) {
        if (supplyChainRepository.existsById(id)) {
            supplyChainRepository.deleteById(id);
        } else {
            throw new RuntimeException("Supply chain not found with id: " + id);
        }
    }
}