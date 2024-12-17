package UPT_PL.Team_3.controller;

import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.service.LogisticsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logistics-sites")
public class LogisticsSiteController {

    @Autowired
    private LogisticsSiteService logisticsSiteService;

    // Get all logistics sites
    @GetMapping
    public List<LogisticsSite> getAllLogisticsSites() {
        return logisticsSiteService.getAllLogisticsSites();
    }

    // Get logistics site by ID
    @GetMapping("/{id}")
    public ResponseEntity<LogisticsSite> getLogisticsSiteById(@PathVariable String id) {
        Optional<LogisticsSite> logisticsSite = logisticsSiteService.getLogisticsSiteById(id);
        return logisticsSite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get logistics sites by country ID
    @GetMapping("/country/{countryId}")
    public List<LogisticsSite> getLogisticsSitesByCountryId(@PathVariable String countryId) {
        return logisticsSiteService.getLogisticsSiteByCountryId(countryId);
    }

    // Create a new logistics site
    @PostMapping
    public LogisticsSite createLogisticsSite(@RequestBody LogisticsSite logisticsSite) {
        return logisticsSiteService.createLogisticsSite(logisticsSite);
    }

    // Update an existing logistics site
    @PutMapping
    public LogisticsSite updateLogisticsSite(@RequestBody LogisticsSite logisticsSite) {
        return logisticsSiteService.updateLogisticsSite(logisticsSite);
    }

    // Delete logistics site by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogisticsSite(@PathVariable String id) {
        logisticsSiteService.deleteLogisticsSite(id);
        return ResponseEntity.noContent().build();
    }
}
