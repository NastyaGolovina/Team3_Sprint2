package UPT_PL.Team_3.model;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.http.ResponseEntity;


/**
 * The Products class represents a collection/list of Product objects. It
 * provides methods to add new product to the list , to search and manage the
 * collection and also a method to read all the object in database by using Hibernate 
 */

public class Products {
	// variable
	private ArrayList<Product> ProductList; // list name ProductList to store all of the products from Product class

	/**
	 * Constructor to initialize the Products object with an empty list of products
	 * 
	 */
	public Products() {
		ProductList = new ArrayList<Product>();
	}

	/**
	 * Get the list of the product
	 * 
	 * @return the ProductList
	 */
	public ArrayList<Product> getProductList() {
		return ProductList;
	}

	/**
	 * Set the list of the product
	 * 
	 * @param ProductList the new ProductList to set
	 */
	public void setProductList(ArrayList<Product> ProductList) {
		this.ProductList = ProductList;

	}

	/**
	 * Method to search for a product in the list by its unique productID This
	 * method searches through the list to find a product by its productID
	 * 
	 * @param productID The unique ID of the product
	 * @return the index/position of the product if it is found
	 * @return -1 if it is not found
	 */
	public int searchProduct(String productID) {
		// Initialize the counter to track the index of product in the list
		// set the i = 0 which is the starting point of the array list
		int i = 0;
		while (i < ProductList.size() && !ProductList.get(i).getProductID().equalsIgnoreCase(productID)) {
			i++; // increment i to move to the next element of the list to keep searching
		}
		if (i != ProductList.size()) {
			return i; // if the product is found
		}
		return -1; // return -1 if it is not found
	}

	/**
	 * Method to check if a Product ID already exists (case-insensitive) using
	 * boolean type equalsIgnoreCase method checks if two strings are equal,
	 * ignoring whether the characters are uppercase or lowercase.
	 * 
	 * @param productID
	 * @return
	 */

	public boolean isProductIDUnique(String productID) {
		for (Product product : ProductList) {
			if (product.getProductID().equalsIgnoreCase(productID)) {
				return false; // ID is not unique
			}
		}
		return true; // ID is unique
	}

	/**
	 * Method to add new product to the ProductList
	 * 
	 * @param idPoroduct
	 * @param name
	 * @param experiationIndays
	 * @param healthyRate
	 * @return 
	 */

//	public Product addProduct() {
//		String productId = ProjectHelper.inputStr("Input product ID : ");
//
//		if (productId.isEmpty()) {
//			System.out.println("The product ID can not be empty! ");
//			return null; // Indicate that no product was added
//		}
//
//		int productPos = searchProduct(productId); // Product position in the array list
//		// searchProduct by the productId
//		if (productPos != -1) { // in case the product is not found in the list by checking its ID then print
//								// existed
//			// then move to the add product starting by name
//
//			System.out.println("Product already existed");
//			return null;
//		}
//
//		  // Starting to add new product follow by those variables
//			String name = ProjectHelper.inputStr("Input product name: ");
//			if (name.isEmpty()) {
//				System.out.println("The product name can not be empty, please insert the name! ");
//				return null; // Indicate that no product was added
//			}
//			Integer expirationInDays = ProjectHelper.inputInt("Input the number of days before expiration date (positive number): ");
//			if (expirationInDays <= 0) {
//				System.out.println("The number of days before expiration date can't be negative, please insert a valid number!  ");
//				return null;
//			}
//			double recommenedRate = ProjectHelper.inputDouble("Input the recommended rate per year of that product : ");
//			if (recommenedRate <= 0) {
//				System.out.println("The recommended rate can't be negative, please insert a valid number!  ");
//				return null;
//			} 
//
//				// after validation, then add the new product to the list,// create object and
//				// put to array list
//				Product product = new Product(productId, name, expirationInDays, recommenedRate);
//				ProductList.add(product);
//
//				System.out.println("ProductID: " + productId + ",Name:" + name + ",Expiration date" + expirationInDays
//						+ "Recommened rate " + recommenedRate + " is successfully added!");
//				return product;
//			
//		}
		    
	
	// ADJUST THE ADD PRODUCT METHOD so that a product can only be added if the all the conditions are met
			//If any of these conditions fail, an appropriate error message is returned for each condition.
			//If all conditions pass, the product is added, and an empty string is returned.
			
			//The method doesn’t create a new Product from scratch. 
		    //Instead, it will just fill in the details (like productID, name,expirationInDays,recommendedRate) of the existing Product object here is newProduct .

			public String addProduct(String productID, String name, String expirationInDays, String recommendedRate, // convert integer and double to string
					Product newProduct) {
				String output = "";

				if (productID != null && !productID.isBlank()) {
					if (productID.matches("^[a-zA-Z0-9].*")) { // condition if productID begins with any special characters
																// (i.e., non-alphanumeric characters),
						if (productID.length() <= 20) {
							int productPos = searchProduct(productID); // Product position in the array list
							if (productPos == -1) { // if it is not found

								// then validate the parameters
								  if (name != null && !name.isBlank()) {
									if (expirationInDays != null && !expirationInDays.isBlank()) {
										if (expirationInDays.matches("-?\\d+")) {
											int expirationInDaysInt = Integer.parseInt(expirationInDays);
											if (expirationInDaysInt > 0) {
												if (recommendedRate != null && !recommendedRate.isBlank()) {
													if (recommendedRate.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
														double recommendedRateDouble = Double.parseDouble(recommendedRate);
														if (recommendedRateDouble > 0) {

															newProduct.setProductID(productID);
															newProduct.setName(name);
															newProduct.setExpirationInDays(expirationInDaysInt);
															newProduct.setRecommendedRate(recommendedRateDouble);
															ProductList.add(newProduct);

														} else {
															output = "The recommended rate must be greater than 0.";
														}

													} else {
														output = "The recommended rate must double.";
													}
												} else {
													output = "The recommended rate cannot be empty.";
												}

											} else {
												output = "The amount of days till expiration must be greater than 0)";

											}
										} else {
											output = "The amount of days till expiration must be an integer";

										}

									} else {
										output = "The amount of till expiration cannot be empty.";
									}
								} else {
									output = "The name of the product cannot be empty.";
								}

							} else {
								output = "Product already exists with ID: " + productID;
							}
						} else {
							output = "The ID cannot exceed more than 20 characters.";
						}
					} else {
						output = "The ID of the product cannot begin with special characters.";
					}
				} else {
					output = "The ID of the product cannot be empty.";
				}

				return output;
			}					
		

	/**
	 * This method builds a string that represents all the Product objects in the
	 * ProductList call the toString method in Products class
	 */

	@Override
	public String toString() {
		String st = "Product List: \n";
		for (Product product : ProductList) {
			st += "" + product + "\n";

		}
		return st; // Return the complete string after the loop

	}

	/**
	 * Method to display the ProductList
	 * 
	 */
	public void displayProducts() {
		if (ProductList.isEmpty()) {
			System.out.println("The product list is empty.");
		} else {
			System.out.println("List of Products:");
			for (Product product : ProductList) {
				System.out.println(product);
			}
		}
	}

	
/**
// * Delete Product by productId in array list
// * 
// * Remove the product with the same productID from the ArrayList named
// * ProductList.
// */
	

public String deleteProduct(String productID, ArrayList<Country> countries) {
	String output = "";

	if (productID != null && !productID.isBlank()) {
		// Check if product exists in the database or ProductList before proceeding
		int productPos = searchProduct(productID);
		if (productPos != -1) { // if the product is found
			// Check if the product is being used in ProductsByCountry
			for (Country country : countries) {
				for (ProductsByCountry productByCountry : country.getProducts()) {
					if (productByCountry.getProduct().getProductID().equalsIgnoreCase(productID)) {
						return "Cannot delete product. It is linked to ProductsByCountry.";
					}
				}
			}
			RestAPIHelper restAPIHelper = new RestAPIHelper();
			ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate()
					.getForEntity(restAPIHelper.getRootAPIURL() + "routeLines/product/" + productID, RouteLine[].class);

			if (response.getStatusCode().is2xxSuccessful()) {
				RouteLine[] routeLineArr = response.getBody();
				if (routeLineArr != null) {
					if (routeLineArr.length == 0) {
						// Delete from the list
						ProductList.remove(productPos);
						System.out.println("Product with ID " + productID + " has been successfully deleted.");
					}
				} else {
					output = "Cannot delete product. It is linked to RouteLine.";
				}
			}

		} else {
			output = "Product with the specified ID not found.";
		}

	} else {
		output = "The ID of the product cannot be empty.";
	}
	return output;
}
	

// UPDATE PRODUCT

public String updateProduct(String productID, String name, String expirationInDays, String recommendedRate,Product newProduct) {
	String output = "";
	if (productID != null && !productID.isBlank()) {
		int productPos = searchProduct(productID); // Product position in the array list
		if (productPos != -1) { // if it is not found
			Product editedProduct = ProductList.get(productPos);
			if (name != null && !name.isBlank()) {
				if (expirationInDays != null && !expirationInDays.isBlank()) {
					if (expirationInDays.matches("-?\\d+")) {
						int expirationInDaysInt = Integer.parseInt(expirationInDays);
						if (expirationInDaysInt > 0) {
							if (recommendedRate != null && !recommendedRate.isBlank()) {
								if (recommendedRate.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
									double recommendedRateDouble = Double.parseDouble(recommendedRate);
									if (recommendedRateDouble > 0) {
										editedProduct.setName(name);
										editedProduct.setExpirationInDays(expirationInDaysInt);
										editedProduct.setRecommendedRate(recommendedRateDouble);
										newProduct.setName(name);
										newProduct.setExpirationInDays(expirationInDaysInt);
										newProduct.setRecommendedRate(recommendedRateDouble);
										
									} else {
										output = "The recommended rate must be greater than 0  ";
									}
							} else {
								output = "The recommended rate must be double  ";
							}
						} else {
							output = "The recommended rate cannot be empty.";
						}
					} else {
						output = "The amount of till expiration must be greater than 0";
					}
					
				} else {
					output = "The amount of till expiration must be integer.";
				}
			}
				else {
					output = "The amount of till expiration cannot be empty.";
				}
			
			
		} else {
			output = "Product's name can not be empty ";
		}

		
	} else {
		output = "Product already exists with ID: " + productID;
	}


}  else {
	output = "The ID of the product cannot be empty.";
}
	return output;
}
}


