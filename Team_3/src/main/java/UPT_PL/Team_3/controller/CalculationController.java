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

import UPT_PL.Team_3.model.Calculation;
import UPT_PL.Team_3.service.CalculationService;

@RestController
@RequestMapping("/api/calculations")
public class CalculationController {
	@Autowired
	private CalculationService calculationService;

	@GetMapping
	public List<Calculation> getAllBooks() {
		return calculationService.getAllCalculations();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Calculation> getCalculationById(@PathVariable Long id) {
		Optional<Calculation> book = calculationService.getCalculationById(id);
		return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Calculation createCalculation(@RequestBody Calculation calculation) {
		return calculationService.createCalculation(calculation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
		calculationService.deleteCalculation(id);
		return ResponseEntity.noContent().build();
	}
}
