package UPT_PL.Team_3.service;

import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {

	private final TransportRepository transportRepository;

	@Autowired
	public TransportService(TransportRepository transportRepository) {
		this.transportRepository = transportRepository;
	}

	public List<Transport> getAllTransports() {
		return transportRepository.findAll();
	}

	public Optional<Transport> getTransportById(String id) {
		return transportRepository.findById(id);
	}

	public Transport createTransport(Transport transport) {
		return transportRepository.save(transport);
	}

	public void deleteTransport(String id) {
		if (transportRepository.existsById(id)) {
			transportRepository.deleteById(id);
		} else {
			throw new RuntimeException("Transport not found with id: " + id);
		}
	}
}
