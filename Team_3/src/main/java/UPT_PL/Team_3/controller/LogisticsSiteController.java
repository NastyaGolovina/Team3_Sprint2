package UPT_PL.Team_3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.service.LogisticsSiteService;

@RestController
@RequestMapping("/api/logistics-sites")
public class LogisticsSiteController {
	@Autowired
	private LogisticsSiteService logisticsSiteService;

	@GetMapping
	public List<LogisticsSite> getAllLogisticsSites() {
		return logisticsSiteService.getAllLogisticsSites();
	}

	@GetMapping("/{id}")
	public ResponseEntity<LogisticsSite> getLogisticsSiteById(@PathVariable String id) {
		Optional<LogisticsSite> book = logisticsSiteService.gettLogisticsSiteById(id);
		return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/country/{countryId}")
	public List<LogisticsSite> getRouteLinesByCalculationId(@PathVariable String countryId) {
		return logisticsSiteService.getLogisticsSiteByCountryId(countryId);
	}
	
	@PostMapping
	public LogisticsSite createLogisticsSite(@RequestBody LogisticsSite logisticsSite) {
		return logisticsSiteService.createLogisticsSite(logisticsSite);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLogisticsSite(@PathVariable String id) {
		logisticsSiteService.deleteLogisticsSite(id);
		return ResponseEntity.noContent().build();
	}
}
