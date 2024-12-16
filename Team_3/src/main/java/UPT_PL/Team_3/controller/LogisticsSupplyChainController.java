package UPT_PL.Team_3.controller;

import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3.service.LogisticsSupplyChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supply-chains")
public class LogisticsSupplyChainController {

    private final LogisticsSupplyChainService supplyChainService;

    @Autowired
    public LogisticsSupplyChainController(LogisticsSupplyChainService supplyChainService) {
        this.supplyChainService = supplyChainService;
    }

    @GetMapping
    public List<LogisticsSupplyChain> getAllSupplyChains() {
        return supplyChainService.getAllSupplyChains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticsSupplyChain> getSupplyChainById(@PathVariable String id) {
        Optional<LogisticsSupplyChain> supplyChain = supplyChainService.getSupplyChainById(id);
        return supplyChain.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LogisticsSupplyChain createSupplyChain(@RequestBody LogisticsSupplyChain supplyChain) {
        return supplyChainService.createSupplyChain(supplyChain);
    }
    
    @PutMapping
    public LogisticsSupplyChain updateSupplyChain(@RequestBody LogisticsSupplyChain supplyChain) {
        return supplyChainService.updateSupplyChain(supplyChain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplyChain(@PathVariable String id) {
        supplyChainService.deleteSupplyChain(id);
        return ResponseEntity.noContent().build();
    }
}