package UPT_PL.Team_3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPT_PL.Team_3.model.Calculation;
import UPT_PL.Team_3.repository.CalculationRepository;

@Service
public class CalculationService {
	private final CalculationRepository calculationRepository;
	
	@Autowired
	public CalculationService(CalculationRepository calculationRepository) {
		this.calculationRepository = calculationRepository;
	}
	
	public List<Calculation> getAllCalculations() {
		return calculationRepository.findAll();
	}

	public Optional<Calculation> getCalculationById(long id) {
		return calculationRepository.findById(id);
	}

	public Calculation createCalculation(Calculation calculation) {
		return calculationRepository.save(calculation);
	}

	public void deleteCalculation(Long id) {
		if (calculationRepository.existsById(id)) {
			calculationRepository.deleteById(id);
		} else {
			throw new RuntimeException("Calculation not found with id: " + id);
		}
	}
}
