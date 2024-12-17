package UPT_PL.Team_3.model;

import java.util.ArrayList;

import org.hibernate.Session;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * The Country class represents a country with a countryId, name, population, products, and logistics sites.
 * It provides methods to manage products and logistics sites within the country.
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
    	sites =  new ArrayList<LogisticsSite>();
    }
    
    /**
     * Constructor to initialize a Country object with the provided countryId, name, and population.
     * Initializes empty lists for products and logistics sites.
     *
     * @param countryId The unique countryId of the country.
     * @param name The name of the country.
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

  
    /**Adds a product to the country's list of products.
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

    public String addProductsByCountry(String productsByCountryID, String production, String price, Product product) {
        String output = ""; // Default output message

        if (productsByCountryID != null && !productsByCountryID.isBlank()) {
            if (productsByCountryID.matches("^[a-zA-Z0-9].*")) { // ID must not start with special characters
                if (productsByCountryID.length() <= 20) {
                    int productPos = searchProductByID(productsByCountryID); // Check if product exists in the list
                    if (productPos == -1) { // Product not found
                        if (production != null && !production.isBlank()) {
                            if (production.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
                                double productionDouble = Double.parseDouble(production);
                                if (productionDouble > 0) {
                                    if (price != null && !price.isBlank()) {
                                        if (price.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
                                            double priceDouble = Double.parseDouble(price);
                                            if (priceDouble > 0) {
                                                ProductsByCountry newProductByCountry = new ProductsByCountry(product, productionDouble, priceDouble, this);
                                                this.products.add(newProductByCountry);
                                                output = "Product '" + product.getName() + "' added successfully with Production: " + productionDouble + " and Price: " + priceDouble;
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
                        output = "A product with ID '" + productsByCountryID + "' already exists.";
                    }
                } else {
                    output = "The product ID cannot exceed 40 characters.";
                }
            } else {
                output = "The product ID cannot begin with special characters.";
            }
        } else {
            output = "The product ID cannot be empty.";
        }
        return output;
    }

 // Method to delete product by country
    public String deleteProductsByCountry() {
        String output = ""; // Default to an empty string if everything is valid

        if (!products.isEmpty()) {
            // Automatically delete the first product (index 0)
            int indexProducts = 0;
            if (indexProducts >= 0 && indexProducts < products.size()) {
                String productsByCountryID = products.get(indexProducts).getProductByCountryId();
                this.products.remove(indexProducts);
                output = "Deleted Product ID: " + productsByCountryID;
            } else {
                output = "Invalid index of Product, try again.";
            }
        } else {
            output = "There are no products in this country.";
        }
        return output;
    }
    
 // Update ProductsByCountry
    public String updateProductsByCountry(String countryId, String indexProduct, String productsByCountryId, String name, String production,
			String price) {
    String output = ""; // Default to an empty string if everything is valid

    if (!products.isEmpty()) { // If the products by country are not empty
        // Validate the product index
        if (indexProduct != null && !indexProduct.isBlank()) {
            int productIndexInt = Integer.parseInt(indexProduct);
            if (productIndexInt >= 0 && productIndexInt < products.size()) {
                ProductsByCountry productByCountryToUpdate = products.get(productIndexInt);

                if (productsByCountryId != null && !productsByCountryId.isBlank()) {
                    if (name != null && !name.isBlank()) {
                        if (production != null && !production.isBlank() && production.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
                            double productionDouble = Double.parseDouble(production);
                            if (price != null && !price.isBlank() && price.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
                                double priceDouble = Double.parseDouble(price);
                                productByCountryToUpdate.setProductByCountryId(productsByCountryId);
                                productByCountryToUpdate.getProduct().setName(name);
                                productByCountryToUpdate.setProduction(productionDouble);
                                productByCountryToUpdate.setPrice(priceDouble);
                                output = "Product updated successfully.";
                            } else {
                                output = "The price cannot be empty and must be a valid double.";
                            }
                        } else {
                            output = "The production cannot be empty and must be a valid double.";
                        }
                    } else {
                        output = "Product name cannot be empty.";
                    }
                } else {
                    output = "The product by country ID cannot be empty.";
                }
            } else {
                output = "The index of product is invalid.";
            }
        } else {
            output = "The index of product must be an integer.";
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
     * @param transports The list of available transports.
     * @param allCountries The list of all countries to check for duplicate site IDs.
     */
    public LogisticsSite addLogisticsSite(Transports transports, ArrayList<Country> allCountries) { 
        String siteId = ProjectHelper.inputStr("Enter the unique ID for the logistics site:");

        // Check if the logistics site ID already exists across all countries
        if (checkIfLogisticsSiteIdExists(siteId, allCountries)) {
            System.out.println("A logistics site with this ID already exists.");
            return null; 
        }

        String name = ProjectHelper.inputStr("Enter the name of the logistics site:");
    
        LogisticsSite newLogisticsSite = new LogisticsSite(siteId, name, this, new ArrayList<>());
        this.sites.add(newLogisticsSite);
       

        newLogisticsSite.addSuppliedTransport(transports);
        
        System.out.println("Logistics site '" + name + "' added successfully.");
        
        return newLogisticsSite;
        
        
      
        
    }

    /**
     * Checks if a logistics site with the given ID already exists across all countries.
     *
     * @param siteId The ID to check.
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
     * Method to display the details of the country, including its products and logistics sites.
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
                System.out.println(" - Product ID: " + product.getProductID() 
                    + ", Name: " + product.getName()
                    + ", Production: " + productByCountry.getProduction() 
                    + ", Price: " + productByCountry.getPrice());
            }
        }

        // Display logistics sites
        if (sites.isEmpty()) {
            System.out.println("No logistics sites available.");
        } else {
            System.out.println("Logistics Sites:");
            for (LogisticsSite site : sites) {
                System.out.println(" - Site ID: " + site.getSiteId() 
                    + ", Name: " + site.getName());
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
    
   
}
