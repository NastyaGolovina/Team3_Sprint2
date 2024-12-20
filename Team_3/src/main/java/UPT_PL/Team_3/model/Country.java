package UPT_PL.Team_3.model;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.net.httpserver.Filter.Chain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * The Country class represents a country with a countryId, name, population,
 * products, and logistics sites. It provides methods to manage products and
 * logistics sites within the country.
 */
@Entity
@Table(name = "Countries")
public class Country {
	@Id
	@Column(name = "Country_Id", length = 20, nullable = false)
	private String countryId;
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	@Column(name = "population", nullable = false)
	private int population;

	@Transient
	@JsonIgnore
	private ArrayList<ProductsByCountry> products;
	@Transient
	@JsonIgnore
	private ArrayList<LogisticsSite> sites;

	/**
	 * Default constructor required by JPA.
	 */
	public Country() {
		products = new ArrayList<ProductsByCountry>();
		sites = new ArrayList<LogisticsSite>();
	}

	/**
	 * Constructor to initialize a Country object with the provided countryId, name,
	 * and population. Initializes empty lists for products and logistics sites.
	 *
	 * @param countryId  The unique countryId of the country.
	 * @param name       The name of the country.
	 * @param population The population of the country.
	 */
	public Country(String countryId, String name, int population) {
		this.countryId = countryId;
		this.name = name;
		this.population = population;
		this.products = new ArrayList<>();
		this.sites = new ArrayList<>();
	}

	// Getters
	public String getCountryId() {
		return countryId;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public ArrayList<ProductsByCountry> getProducts() {
		return products;
	}

	public ArrayList<LogisticsSite> getSites() {
		return sites;
	}

	// Setters
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setProducts(ArrayList<ProductsByCountry> products) {
		this.products = products;
	}

	public void setSites(ArrayList<LogisticsSite> sites) {
		this.sites = sites;
	}

	/**
	 * Searches for a product by its ID in the products list.
	 *
	 * @param productId The ID of the product to search for.
	 * @return The index of the product in the products list, or -1 if not found.
	 */
	public int searchProductByID(String productId) {
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i).getProduct();
			if (product.getProductID().equalsIgnoreCase(productId)) {
				return i;
			}
		}
		return -1;
	}
	
	public int searchProductByCountryID(String productsByCountryID) {
        for (int i = 0; i < products.size(); i++) {
            ProductsByCountry productByCountry = products.get(i);
            if (productByCountry.getProductByCountryId().equalsIgnoreCase(productsByCountryID)) {
                return i;  
            }
        }
        return -1;  
    }

	/**
	 * Adds a product to the country's list of products.
	 * 
	 * @param products
	 * @return
	 */
//    public ArrayList<ProductsByCountry> addProductByCountry(Products products) {
//    	ArrayList<ProductsByCountry> successfullyAddedProducts = new ArrayList<ProductsByCountry>();
//        ArrayList<Product> allProducts = products.getProductList();
//
//        System.out.println("Available Products:");
//        for (int i = 0; i < allProducts.size(); i++) {
//            System.out.println("(" + (i + 1) + ") " + allProducts.get(i).toString());
//        }
//
//        ProductsByCountry newProductByCountry = null; // Declare variable outside loop
//
//        int inputUser = ProjectHelper.inputInt("Select the product number to add (or choose 0 to exit): ");
//
//        while (inputUser != 0) {
//            if (inputUser < 1 || inputUser > allProducts.size()) {
//                System.out.println("Invalid choice. Please choose a valid product number or 0 to exit.");
//            } else {
//                Product product = allProducts.get(inputUser - 1);
//                double production = ProjectHelper.inputDouble("Enter the production quantity: ");
//                double price = ProjectHelper.inputDouble("Enter the price of the product: ");
//
//                // Check if a product with the same name already exists in ProductsByCountry
//                boolean exists = false;
//                for (ProductsByCountry existingProduct : this.products) {
//                    if (existingProduct.getProduct().getName().equals(product.getName())) {
//                        exists = true;
//                        break;
//                    }
//                }
//
//                if (exists) {
//                    System.out.println("The product '" + product.getName() + "' already exists in the country.");
//                } else {
//                    // Create a new ProductsByCountry object
//                    newProductByCountry = new ProductsByCountry(product, production, price, this);
//                    this.products.add(newProductByCountry);
//                    successfullyAddedProducts.add(newProductByCountry);
//                    System.out.println("Product " + product.getName() + " added successfully.");
//                }
//            }
//
//            inputUser = ProjectHelper.inputInt("Select the product number to add (or choose 0 to exit): ");
//        }
//
//        if (newProductByCountry == null) {
//            System.out.println("No products were added.");
//        } else {
//            System.out.println("Product Addition Process Completed.");
//        }
//
//        return successfullyAddedProducts; // Return the last added product or null if no product was added
//    }

	public String addProductsByCountry(String production, String price, Product product,
			ProductsByCountry newProductByCountry) {
		String output = ""; // Default output message
		if (product != null) {
			int productPos = searchProductByID(product.getProductID()); // Check if product exists in the list
			if (productPos == -1) { // Product not found
				if (production != null && !production.isBlank()) {
					if (production.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
						double productionDouble = Double.parseDouble(production);
						if (productionDouble > 0) {
							if (price != null && !price.isBlank()) {
								if (price.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
									double priceDouble = Double.parseDouble(price);
									if (priceDouble > 0) {
										newProductByCountry.setProduct(product);
										newProductByCountry.setProduction(productionDouble);
										newProductByCountry.setPrice(priceDouble);
										newProductByCountry.setCountry(this);
										products.add(newProductByCountry);
									} else {
										output = "The price must be greater than 0.";
									}
								} else {
									output = "The price must be a valid double.";
								}
							} else {
								output = "The price cannot be empty.";
							}
						} else {
							output = "The production quantity must be greater than 0.";
						}
					} else {
						output = "The production quantity must be a valid double.";
					}
				} else {
					output = "The production quantity cannot be empty.";
				}
			} else {
				output = "A product with ID '" + product.getProductID() + "' already exists.";
			}

		}

		return output;
	}

	// Method to delete product by country
		public String deleteProductsByCountry(String productsByCountryID) {
			String output = ""; // Default to an empty string if everything is valid

			if (productsByCountryID != null && !productsByCountryID.isBlank()) {
				// Check if the product by country exists

				// Automatically delete the first product (index 0)
				int indexProducts = searchProductByCountryID(productsByCountryID);
				;
				if (indexProducts >= 0 && indexProducts < products.size()) {
					products.remove(indexProducts);

				} else {
					output = "Invalid index of Product, try again.";
				}
			} else {
				output = "The product by country ID can not be empty";
			}
			return output;
		}

		// Update ProductsByCountry
		public String updateProductsByCountry(String productsByCountryId, String production, String price,
				ProductsByCountry newProductByCountry) {
			String output = ""; // Default to an empty string if everything is vali
			if (!products.isEmpty()) { // If the products by country are not empty
				// Validate the product index
				if (productsByCountryId != null && !productsByCountryId.isBlank()) {
					int indexProducts = searchProductByCountryID(productsByCountryId);
					if (indexProducts >= 0 && indexProducts < products.size()) {
						ProductsByCountry productByCountryToUpdate = products.get(indexProducts);

						if (production != null && !production.isBlank()
								&& production.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
							double productionDouble = Double.parseDouble(production);
							if (price != null && !price.isBlank() && price.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
								double priceDouble = Double.parseDouble(price);

								productByCountryToUpdate.setProduction(productionDouble);
								productByCountryToUpdate.setPrice(priceDouble);
								newProductByCountry.setProductByCountryId(productsByCountryId);
								newProductByCountry.setPrice(priceDouble);
								newProductByCountry.setProduction(productionDouble);
								newProductByCountry.setProduct(productByCountryToUpdate.getProduct());
								newProductByCountry.setCountry(this);
								
							} else {
								output = "The price cannot be empty and must be a valid double.";
							}
						} else {
							output = "The production cannot be empty and must be a valid double.";
						}

					} else {
						output = "The index of product is invalid.";
					}
				} else {
					output = "The product by country ID can not be empty";
				}
			} else {
				output = "There are no products in this country.";
			}
			return output;
		}

	/**
	 * addProductsByCountry
	 */

	public void addProductsByCountry(ProductsByCountry newProductByCountry) {
		products.add(newProductByCountry);
	}

	/**
	 * addLogisticsSite
	 */
	public void addLogisticsSite(LogisticsSite newLogisticsSite) {
		sites.add(newLogisticsSite);
	}

	/**
	 * Adds a logistics site to the country.
	 *
	 * @param transports   The list of available transports.
	 * @param allCountries The list of all countries to check for duplicate site
	 *                     IDs.
	 */
//	public LogisticsSite addLogisticsSite(Transports transports, ArrayList<Country> allCountries) {
//		String siteId = ProjectHelper.inputStr("Enter the unique ID for the logistics site:");
//
//		// Check if the logistics site ID already exists across all countries
//		if (checkIfLogisticsSiteIdExists(siteId, allCountries)) {
//			System.out.println("A logistics site with this ID already exists.");
//			return null;
//		}
//
//		String name = ProjectHelper.inputStr("Enter the name of the logistics site:");
//
//		LogisticsSite newLogisticsSite = new LogisticsSite(siteId, name, this, new ArrayList<>());
//		this.sites.add(newLogisticsSite);
//
//		newLogisticsSite.addSuppliedTransport(transports);
//
//		System.out.println("Logistics site '" + name + "' added successfully.");
//
//		return newLogisticsSite;
//
//	}

	/**
	 * Checks if a logistics site with the given ID already exists across all
	 * countries.
	 *
	 * @param siteId       The ID to check.
	 * @param allCountries The list of all countries.
	 * @return true if the ID exists, false otherwise.
	 */
	public boolean checkIfLogisticsSiteIdExists(String siteId, ArrayList<Country> allCountries) {
		for (Country country : allCountries) {
			for (LogisticsSite site : country.getSites()) {
				if (site.getSiteId().equals(siteId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method to display the details of the country, including its products and
	 * logistics sites.
	 */
	public void displayCountryDetails() {
		System.out.println("Country ID: " + countryId);
		System.out.println("Name: " + name);
		System.out.println("Population: " + population);

		// Display products.
		if (products.isEmpty()) {
			System.out.println("No products available.");
		} else {
			System.out.println("Products:");
			for (ProductsByCountry productByCountry : products) {
				Product product = productByCountry.getProduct();
				System.out.println(
						" - Product ID: " + product.getProductID() + ", Name: " + product.getName() + ", Production: "
								+ productByCountry.getProduction() + ", Price: " + productByCountry.getPrice());
			}
		}

		// Display logistics sites
		if (sites.isEmpty()) {
			System.out.println("No logistics sites available.");
		} else {
			System.out.println("Logistics Sites:");
			for (LogisticsSite site : sites) {
				System.out.println(" - Site ID: " + site.getSiteId() + ", Name: " + site.getName());
				if (site.getSuppliedTransports().isEmpty()) {
					System.out.println("No supplied transports available.");
				} else {
					System.out.println("  - Supplied Transports:");
					for (Transport transport : site.getSuppliedTransports()) {
						System.out.println("   >" + transport);
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", name=" + name + ", population=" + population + "]";
	}

	public LogisticsSite searchSite(String siteId, ArrayList<Country> allCountries) {
		for (Country country : allCountries) {
			for (LogisticsSite site : country.getSites()) {
				if (site.getSiteId().equalsIgnoreCase(siteId)) {
					return site;
				}
			}
		}
		return null;
	}

	public String addLogisticsSite(ArrayList<Country> allCountries, ArrayList<Transport> transports, String siteId,
			String name, LogisticsSite newSite) {
		String output = "";

		if (siteId != null && !siteId.isBlank()) {
			if (siteId.matches("^[a-zA-Z0-9].*")) {
				if (siteId.length() <= 20) {
					if (!checkIfLogisticsSiteIdExists(siteId, allCountries)) {
						newSite.setSiteId(siteId);
						newSite.setName(name);
						newSite.setCountry(this);
						newSite.setSuppliedTransports(transports);
						sites.add(newSite);
					} else {
						output = "Logistics site already exists with ID: " + siteId;
					}
				} else {
					output = "The site ID cannot exceed more than 20 characters.";
				}
			} else {
				output = "The site ID cannot begin with special characters.";
			}
		} else {
			output = "The site ID cannot be empty.";
		}

		return output;
	}

	// Method to delete product by country, delete product from a certain country
	public String deleteLogisticsSite(String siteId, LogisticsSupplyChains chains, ArrayList<Country> allCountries) {
		String output = "";

		// Check if the country has any products
		if (!this.getSites().isEmpty()) {
			if (checkIfLogisticsSiteIdExists(siteId, allCountries)) {
				
//				boolean isPartOfChain = chains.getSupplyChains().stream()
//						.anyMatch(chain -> chain.getSender().equals(searchSite(siteId, allCountries))
//								|| chain.getReceiver().equals(searchSite(siteId, allCountries)));
//				
				boolean isPartOfChain = false; // Initially false

				for (LogisticsSupplyChain chain : chains.getSupplyChains()) {     
					if (chain.getSender().getSiteId().equals(siteId) || chain.getReceiver().getSiteId().equals(siteId)) {
				        isPartOfChain = true;
				        break;
				    }
				}
				if (!isPartOfChain) {

					RestAPIHelper restAPIHelper = new RestAPIHelper();
					ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate().getForEntity(
							restAPIHelper.getRootAPIURL() + "routeLines/site/" + siteId, RouteLine[].class);

					if (response.getStatusCode().is2xxSuccessful()) {
						RouteLine[] routeLineArr = response.getBody();
						if (routeLineArr != null && routeLineArr.length == 0) {

							
							sites.remove(searchSite(siteId, allCountries));

							System.out.println("Logistics site successfully deleted.");

						} else {
							output = "Failed to check route lines. Server returned status";
						}
					} else {
						output = "Failed to check route lines. Server returned status";
					}
				} else {
					output = "The logistics site is part of an active supply chain. Deletion is not possible";
				}

			} else {
				output = "There are no LogisticsSites  with this id.";
			}
		} else {
			output = "There are no LogisticsSites in this country.";
		}

		return output;
	}

	public String updateLogisticsSite(ArrayList<Country> allCountries, String siteId, String name,
			LogisticsSite updatedSite) {
		String output = "";

		if (siteId != null && !siteId.isBlank()) {
			LogisticsSite site = searchSite(siteId, allCountries);

			if (site != null) {

				if (name != null && !name.isBlank()) {
					site.setName(name);
					updatedSite.setSiteId(site.getSiteId());
					updatedSite.setName(name);
					updatedSite.setCountry(this);
					updatedSite.setSuppliedTransports(new ArrayList<Transport>(site.getSuppliedTransports()));

				} else {
					output = "Logistics site name cannot be empty.";
				}
			} else {
				output = "Logistics site with ID (" + siteId + ") doesn't exist.";
			}
		} else {
			output = "The site ID cannot be empty.";
		}

		return output;
	}

}
