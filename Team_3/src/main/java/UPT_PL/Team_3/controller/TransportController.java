package UPT_PL.Team_3.controller;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transports")
public class TransportController {

	@Autowired
	private TransportService transportService;

	@GetMapping
	public List<Transport> getAllTransports() {
		return transportService.getAllTransports();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transport> getTransportById(@PathVariable String id) {
		Optional<Transport> transport = transportService.getTransportById(id);
		return transport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping
	public Transport updateTransport(@RequestBody Transport transport) {
		return transportService.updateTransport(transport);
	}


	@PostMapping
	public Transport createTransport(@RequestBody Transport transport) {
		return transportService.createTransport(transport);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransport(@PathVariable String id) {
		transportService.deleteTransport(id);
		return ResponseEntity.noContent().build();
	}

}
