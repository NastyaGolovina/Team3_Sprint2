package UPT_PL.Team_3.service;

import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.repository.LogisticsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticsSiteService {
    private final LogisticsSiteRepository logisticsSiteRepository;

    @Autowired
    public LogisticsSiteService(LogisticsSiteRepository logisticsSiteRepository) {
        this.logisticsSiteRepository = logisticsSiteRepository;
    }

    // Method to get all logistics sites
    public List<LogisticsSite> getAllLogisticsSites() {
        return logisticsSiteRepository.findAll();
    }

    // Method to get logistics sites by country ID
    public List<LogisticsSite> getLogisticsSiteByCountryId(String countryId) {
        return logisticsSiteRepository.getLogisticsSiteByCountryId(countryId);
    }

    // Method to get a logistics site by ID
    public Optional<LogisticsSite> getLogisticsSiteById(String id) {
        return logisticsSiteRepository.findById(id);
    }

    // Method to create a new logistics site
    public LogisticsSite createLogisticsSite(LogisticsSite logisticsSite) {
        return logisticsSiteRepository.save(logisticsSite);
    }

    // Method to update an existing logistics site
    public LogisticsSite updateLogisticsSite(LogisticsSite logisticsSite) {
        // You may want to add a check to ensure the logistics site exists before updating
        return logisticsSiteRepository.save(logisticsSite);
    }

    // Method to delete a logistics site by ID
    public void deleteLogisticsSite(String id) {
        if (logisticsSiteRepository.existsById(id)) {
            logisticsSiteRepository.deleteById(id);
        } else {
            throw new RuntimeException("LogisticsSite not found with id: " + id);
        }
    }
}
