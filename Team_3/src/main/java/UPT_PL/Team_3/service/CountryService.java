package UPT_PL.Team_3.service;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
	private final CountryRepository countryRepository;

	@Autowired
	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	public Optional<Country> getCountryById(String id) {
		return countryRepository.findById(id);
	}

	public Country createCountry(Country country) {
		return countryRepository.save(country);
	}

	public void deleteCountry(String id) {
		if (countryRepository.existsById(id)) {
			countryRepository.deleteById(id);
		} else {
			throw new RuntimeException("Country not found with id: " + id);
		}
	}
}
