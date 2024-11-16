package UPT_PL.Team_3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UPT_PL.Team_3.model.RouteLine;

import UPT_PL.Team_3.service.RouteLineService;

@RestController
@RequestMapping("/api/routeLines")
public class RouteLineController {
	@Autowired
	private RouteLineService routeLineService;
	
	@GetMapping
	public List<RouteLine> getAllRouteLine() {
		return routeLineService.getAllRouteLine();
	}
	
	@GetMapping("/calculation/{calculationId}")
	public List<RouteLine> getRouteLinesByCalculationId(@PathVariable long calculationId) {
//		List<RouteLine> routeLine = routeLineService.getRouteLinesByCalculationId(calculationId);
		return routeLineService.getRouteLinesByCalculationId(calculationId);
	}

	@PostMapping
	public RouteLine createBook(@RequestBody RouteLine routeLine) {
		return routeLineService.createRouteLine(routeLine);
	}

	@DeleteMapping("/{calculationId}")
	public ResponseEntity<Void> deleteRouteLine(@PathVariable long calculationId) {
		routeLineService.deleteRouteLine(calculationId);
		return ResponseEntity.noContent().build();
	}
}


