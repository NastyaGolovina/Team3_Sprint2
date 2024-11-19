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
	public List<RouteLine> getRouteLinesByCalculationId(@PathVariable String calculationId) {
		return routeLineService.getRouteLinesByCalculationId(calculationId);
	}

	@PostMapping
	public RouteLine createRouteLine(@RequestBody RouteLine routeLine) {
		return routeLineService.createRouteLine(routeLine);
	}

	@DeleteMapping("/calculation/{calculationId}")
	public ResponseEntity<Void> deleteRouteLine(@PathVariable String calculationId) {
		routeLineService.deleteRouteLine(calculationId);
		return ResponseEntity.noContent().build();
	}
	
	
    @GetMapping("/country/{countryId}")
    public List<RouteLine> getRouteLineCountryId(@PathVariable String countryId) {
    	return routeLineService.getRouteLineCountryId(countryId);
    }

    @GetMapping("/transport/{transportId}")
    public List<RouteLine> getRouteLineTransportId(@PathVariable String transportId) {
        return routeLineService.getRouteLineTransportId(transportId);
    }


    @GetMapping("/site/{siteId}")
    public List<RouteLine> getRouteLineBySiteId(@PathVariable String siteId) {
        return routeLineService.getRouteLineBySiteId(siteId);
    }

    @GetMapping("/product/{productId}")
    public List<RouteLine> getRouteLineByProductId(@PathVariable String productId) {
        return routeLineService.getRouteLineByProductId(productId);
    }
}


