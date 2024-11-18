package UPT_PL.Team_3.controller;

import UPT_PL.Team_3.model.ProductsByCountry;

import UPT_PL.Team_3.service.ProductsByCountryService;

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

@RestController
@RequestMapping("/api/products-by-country")
public class ProductsByCountryController {
		@Autowired
		private ProductsByCountryService productsByCountryService;

		@GetMapping
		public List<ProductsByCountry> getAllProductsByCountry() {
	        return productsByCountryService.getAllProductsByCountry();
	    }

		@GetMapping("/{id}")
		public ResponseEntity<ProductsByCountry> getProductByCountryId(@PathVariable String  productByCountryId) {
			Optional<ProductsByCountry> productsByCountry = productsByCountryService.getProductByCountryId( productByCountryId);
			return productsByCountry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}

		@GetMapping("/country/{countryId}")
		public List<ProductsByCountry> getProductsByCountryByCountryId(@PathVariable String countryId) {
			return productsByCountryService.getProductsByCountryByCountryId(countryId);
		}
		
		@PostMapping
		public ProductsByCountry createProductsByCountry(@RequestBody ProductsByCountry productsByCountry) {
			return productsByCountryService.createProductsByCountry(productsByCountry);
		}

		@DeleteMapping("/{productByCountryId}")
		public ResponseEntity<Void> deleteProductsByCountry(@PathVariable String productByCountryId) {
			productsByCountryService.deleteProductsByCountry(productByCountryId);
			return ResponseEntity.noContent().build();
		}
	}


