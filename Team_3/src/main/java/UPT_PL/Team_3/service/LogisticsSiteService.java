package UPT_PL.Team_3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.repository.LogisticsSiteRepository;

@Service
public class LogisticsSiteService {
	private final LogisticsSiteRepository logisticsSiteRepository;
	
	@Autowired
	public LogisticsSiteService(LogisticsSiteRepository logisticsSiteRepository) {
		this.logisticsSiteRepository = logisticsSiteRepository;
	}

	public List<LogisticsSite> getAllLogisticsSites() {
		return logisticsSiteRepository.findAll();
	}

	public List<LogisticsSite> getLogisticsSiteByCountryId(String countryId) {
        return logisticsSiteRepository.getLogisticsSiteByCountryId(countryId);
    }
	
	public Optional<LogisticsSite> gettLogisticsSiteById(String id) {
		return logisticsSiteRepository.findById(id);
	}

	public LogisticsSite createLogisticsSite(LogisticsSite logisticsSite) {
		return logisticsSiteRepository.save(logisticsSite);
	}

	public void deleteLogisticsSite(String id) {
		if (logisticsSiteRepository.existsById(id)) {
			logisticsSiteRepository.deleteById(id);
		} else {
			throw new RuntimeException("LogisticsSite not found with id: " + id);
		}
	}
	
}
