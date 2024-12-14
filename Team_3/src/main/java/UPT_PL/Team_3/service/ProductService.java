package UPT_PL.Team_3.service;


import UPT_PL.Team_3.model.Product;

import UPT_PL.Team_3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(String productID) {
		return productRepository.findById(productID);
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(String productID) {
		if (productRepository.existsById(productID)) {
			productRepository.deleteById(productID);
		} else {
			throw new RuntimeException("Product was not found with the Product ID: " + productID);
		}
	}
}
