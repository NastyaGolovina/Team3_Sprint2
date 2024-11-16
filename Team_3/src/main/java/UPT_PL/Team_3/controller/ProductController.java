package UPT_PL.Team_3.controller;
import  UPT_PL.Team_3.model.Product;

import UPT_PL.Team_3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController

@RequestMapping("/api/products")
public class ProductController {
@Autowired
private ProductService productService;
@GetMapping
public List<Product> getAllProducts() {
return productService.getAllProducts();
}
@GetMapping("/{productID}")

public ResponseEntity<Product>
getProductById(@PathVariable String productID) {
Optional<Product> product = productService.getProductById(productID);

return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
@PostMapping
public Product createBook(@RequestBody Product product) {
return productService.createProduct(product);
}
@DeleteMapping("/{productID}")
public ResponseEntity<Void>
deleteProduct(@PathVariable String productID) {
	productService.deleteProduct(productID);
return ResponseEntity.noContent().build();
}
}

