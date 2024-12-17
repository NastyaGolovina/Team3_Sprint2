package UPT_PL.Team_3.service;


import UPT_PL.Team_3.model.Product;



import UPT_PL.Team_3.model.ProductsByCountry;

import UPT_PL.Team_3.repository.ProductsByCountryRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;



@Service

public class ProductsByCountryService {

	private final ProductsByCountryRepository productsByCountryRepository;



	@Autowired

	public ProductsByCountryService(ProductsByCountryRepository productsByCountryRepository) {

		this.productsByCountryRepository = productsByCountryRepository;

	}



	public List<ProductsByCountry> getAllProductsByCountry() {

		return productsByCountryRepository.findAll();

	}

	

	public List<ProductsByCountry> getProductsByCountryByCountryId(String countryId) {

		return productsByCountryRepository.getProductsByCountryByCountryID(countryId);

	}



	public Optional<ProductsByCountry> getProductByCountryId(String productByCountryId) {

		return  productsByCountryRepository.findById(productByCountryId);

	}



	public ProductsByCountry createProductsByCountry(ProductsByCountry productsByCountry) {

		return productsByCountryRepository.save( productsByCountry);

	}

	public ProductsByCountry updateProductsByCountry(ProductsByCountry productsByCountry) {

		return productsByCountryRepository.save( productsByCountry);

	}

	public void deleteProductsByCountry(String productByCountryId) {

		if (productsByCountryRepository.existsById(productByCountryId)) {

			productsByCountryRepository.deleteById(productByCountryId);

		} else {

			throw new RuntimeException("Product by Country was not found with the ProductsByCountry ID: " + productByCountryId);

		}

	}

}