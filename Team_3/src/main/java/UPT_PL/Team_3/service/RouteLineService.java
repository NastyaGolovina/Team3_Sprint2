package UPT_PL.Team_3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPT_PL.Team_3.model.RouteLine;
import UPT_PL.Team_3.repository.RouteLineRepository;

@Service
public class RouteLineService {
	private final  RouteLineRepository  routeLineRepository;
	@Autowired
	public RouteLineService(RouteLineRepository routeLineRepository) {
		this.routeLineRepository = routeLineRepository;
	}
	
	public List<RouteLine> getAllRouteLine() {
		return routeLineRepository.findAll();
	}
	
	public List<RouteLine> getRouteLinesByCalculationId(String calculationId) {
        return routeLineRepository.getRouteLineByCalculationID(calculationId);
    }

	public RouteLine createRouteLine(RouteLine routeLine) {
		return routeLineRepository.save(routeLine);
	}
	
	public void deleteRouteLine(String calculationId) {
		routeLineRepository.deleteRouteLineByCalculationID(calculationId);
	}
	
	public List<RouteLine> getRouteLineCountryId(String countryId) {
		return routeLineRepository.existsByCountryId(countryId);
	}

	public List<RouteLine> getRouteLineTransportId(String transportId) {
		return routeLineRepository.existsByTransportId(transportId);
	}

	public List<RouteLine> getRouteLineBySiteId(String siteId) {
		return routeLineRepository.getRouteLineBySiteId(siteId);
	}

	public List<RouteLine> getRouteLineByProductId(String productId) {
		return routeLineRepository.getRouteLineByProductId(productId);
	}
}




