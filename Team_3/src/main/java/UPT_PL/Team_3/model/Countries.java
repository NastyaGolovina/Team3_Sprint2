package UPT_PL.Team_3.model;

import java.util.ArrayList;


import java.util.List;


import org.hibernate.Session;
import org.springframework.http.ResponseEntity;

/**
 * The Countries class represents a collection of Country objects. It provides
 * methods to manage and add new countries to the list.
 */
public class Countries {
    // List to hold all Country objects
    private ArrayList<Country> countries;

    /**
     * Constructor to initialize the Countries object with an empty list of
     * countries.
     */
    public Countries() {
        countries = new ArrayList<>();
    }

    // Method to search for a country by ID
    public int searchCountry(String countryId) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryId().equalsIgnoreCase(countryId)) {
                return i; 
            }
        }
        return -1; 
    }

    /**
     * @return the list of countries
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries the list of countries to set
     */
    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    /**
     * Method to add a new country to the list of countries.
     */
//    public Country addCountry() {
//        String countryId = ProjectHelper.inputStr("Input Country ID: ");
//
//        if (countryId.isEmpty()) {  
//            System.out.println("The ID cannot be empty.");
//            return null;  
//        } else if (!countryId.matches("^[a-zA-Z0-9].*")) {
//            System.out.println("The ID cannot begin with special characters.");
//            return null;  
//        } else if (countryId.length() > 20) {
//            System.out.println("The ID cannot exceed more than 20 characters.");
//            return null; 
//        }
//
//        int countryPos = searchCountry(countryId);
//
//        if (countryPos != -1) { 
//            System.out.println("Country already exists with ID: " + countryId);
//            return null;  
//        } else {
//            String name = ProjectHelper.inputStr("Input Country Name: ");
//            int population = ProjectHelper.inputInt("Input Population (must be greater than 0): ");
//
//            // Validate Population
//            while (population <= 0) {
//                System.out.println("Population must be greater than 0. Please try again.");
//                population = ProjectHelper.inputInt("Input Population: ");
//            }
//
//            // Create and add new Country object
//            Country newCountry = new Country(countryId, name, population);
//            countries.add(newCountry);
//                        
//            System.out.println("Country added successfully: " + newCountry);
//            
//            return newCountry;  
//        }
//    }
//    
    
	public String addCountry(String countryId, String name, String population, Country newCountry) {
		String output = "";

		if (countryId != null && !countryId.isBlank()) {
			if (countryId.matches("^[a-zA-Z0-9].*")) {
				if (countryId.length() <= 20) {
					int countryPos = searchCountry(countryId);
					if (countryPos == -1) {

						if (name != null && !name.isBlank()) {
							if (population != null && !population.isBlank()) {
								if (population.matches("-?\\d+")) {
									int populationInt = Integer.parseInt(population);
									if (populationInt > 0) {
										newCountry.setCountryId(countryId);
										newCountry.setName(name);
										newCountry.setPopulation(populationInt);
										countries.add(newCountry);
									} else {
										output = "Population must be greater than 0.";

									}
								} else {
									output = "The population must be an integer";

								}
							} else {
								output = "The population cannot be empty.";
							}
						} else {
							output = "The name cannot be empty.";
						}

					} else {
						output = "Country already exists with ID: " + countryId;
					}
				} else {
					output = "The ID cannot exceed more than 20 characters.";

				}
			} else {
				output = "The ID cannot begin with special characters.";

			}
		} else {
			output = "The ID cannot be empty.";

		}

		return output;
		
	}

    @Override
    public String toString() {
        return "Countries [countries=" + countries + "]";
    }
    
    /**
	 *  Method to display the countries
	 */
    public void displayCountries() {
        if (countries.isEmpty()) {
            System.out.println("The countries list is empty.");
        } else {
            System.out.println("List of countries:");
            for (Country country : countries) {
                country.displayCountryDetails();
            }
        }
    }
    
 // DELETE Country by Id
    
//    public boolean deleteCountryById(String countryId) {
//        int countryIndex = searchCountry(countryId);
//
//        // Check if the country exists in the list
//        if (countryIndex == -1) {
//            System.out.println("Country with the specified ID not found.");
//            return false;  
//        }
//
//        Country country = countries.get(countryIndex);
//
//        // 1. Check if the country is linked to any ProductsByCountry
//        for (ProductsByCountry product : country.getProducts()) {
//            if (product.getCountry().getCountryId().equals(countryId)) {
//                System.out.println("Cannot delete country. It is linked to ProductsByCountry.");
//                return false;  
//            }
//        }
//
//        // 2. Check if the country is linked to any LogisticsSite
//        for (LogisticsSite site : country.getSites()) {
//            if (site.getCountry().getCountryId().equals(countryId)) {
//                System.out.println("Cannot delete country. It is linked to LogisticsSite.");
//                return false;
//            }
//        }
//        
//        RestAPIHelper restAPIHelper = new RestAPIHelper();
//		ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate().
//												getForEntity(restAPIHelper.getRootAPIURL() 
//												+ "routeLines/country/" 
//														+ countryId,
//														RouteLine[].class);
//							
//		if (response.getStatusCode().is2xxSuccessful()) {
//			RouteLine[] routeLineArr = response.getBody();
//			if (routeLineArr != null) {	
//				if(routeLineArr.length == 0) {
//					countries.remove(countryIndex);
//
//			        System.out.println("Country successfully deleted.");
//			        return true;
//				}
//			}  else {
//				System.out.println("Cannot delete country. It is linked to RouteLine.");
//			}
//		}
//		
//		return false;
//  
//    }

	public String deleteCountry(String countryId) {
		String output = "";

		if (countryId != null && !countryId.isBlank()) {
			int countryIndex = searchCountry(countryId);
			// Check if the country exists in the list
			if (countryIndex != -1) {

				Country country = countries.get(countryIndex);
				// 1. Check if the country is linked to any ProductsByCountry
				for (ProductsByCountry product : country.getProducts()) {
					if (product.getCountry().getCountryId().equals(countryId)) {
						return "Cannot delete country. It is linked to ProductsByCountry.";
					}
				}

				// 2. Check if the country is linked to any LogisticsSite
				for (LogisticsSite site : country.getSites()) {
					if (site.getCountry().getCountryId().equals(countryId)) {
						return "Cannot delete country. It is linked to LogisticsSite.";
					}
				}

				RestAPIHelper restAPIHelper = new RestAPIHelper();
				ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate().getForEntity(
						restAPIHelper.getRootAPIURL() + "routeLines/country/" + countryId, RouteLine[].class);

				if (response.getStatusCode().is2xxSuccessful()) {
					RouteLine[] routeLineArr = response.getBody();
					if (routeLineArr != null) {
						if (routeLineArr.length == 0) {
							countries.remove(countryIndex);
							System.out.println("Country successfully deleted.");

						}
					} else {
						output = "Cannot delete country. It is linked to RouteLine.";
					}
				} 
			} else {
				output = "Country with the specified ID not found.";
			}
		} else {
			output = "The ID cannot be empty.";
		}

		return output;
	}
	
	
	
	
	
	
	
	public String updateCountry(String countryId, String name, String population,Country country) {
		String output = "";
		
		if (countryId != null && !countryId.isBlank()) {
			int countryPos = searchCountry(countryId);
			if (countryPos != -1) {
				Country editedCountry = countries.get(countryPos);
				if (name != null && !name.isBlank()) {
					if (population != null && !population.isBlank()) {
						if (population.matches("-?\\d+")) {
							int populationInt = Integer.parseInt(population);
							if (populationInt > 0) {
								editedCountry.setName(name);
								editedCountry.setPopulation(populationInt);
								country.setName(name);
								country.setPopulation(populationInt);
							} else {
								output = "Population must be greater than 0.";

							}
						} else {
							output = "The population must be an integer";

						}
					} else {
						output = "The population cannot be empty.";
					}
				} else {
					output = "The name cannot be empty.";
				}

			} else {
				output = "Country with ID  (" + countryId + ") doesn't exist";
			}
		} else {
			output = "The ID cannot be empty.";
			
		}
		
		return output;
		
	}
	
	

// Method to delete product by country, delete product from a certain country
    
//    public String deleteProductsByCountry() {
//    	
//    	 // Prompt for the country ID to delete
//        String countryId = ProjectHelper.inputStr("Enter the country ID to delete: ");
//        int countryPos = searchCountry(countryId);
//        
//        // Check if the country exists
//        if (countryPos == -1) {
//            System.out.println("Country with the specified ID not found.");
//            return null;
//        }
//
//       Country country = countries.get(countryPos);
//       
//       // check if country has any products
//       if(country.getProducts().isEmpty()) {
//    	   System.out.println("There are no products list in this country.");
//          return null;
//       }
//       // Display the list of products in the country
//       System.out.println("List of products :");
//       for (int i = 0; i < country.getProducts().size(); i++) {
//           System.out.println("(" + (i) + ") " + country.getProducts().get(i));
//       }
//       
//      // Ask for the index/position of the products in the previous list to delete
//       
//     int indexProducts = ProjectHelper.inputInt("Enter the index of the product to delete: ");
//       
//     // validation for the index of product
//     if(indexProducts < 0 || indexProducts >= country.getProducts().size()) {
//    	 System.out.println("Invalid index of Product, try again");
//    	 return null;
//     }
//     
//     // remove the product by country from the ProductsByCountry list 
//     String productsByCountryID = country.getProducts().get(indexProducts).getProductByCountryId();
//     country.getProducts().remove(indexProducts);
//     System.out.println("Product removed successfully.");
//     return productsByCountryID;
//    
//	}
	
	// Method to delete product by country, delete product from a certain country
//	public String deleteProductsByCountry(String countryId) {
//		String output = ""; // Default to an empty string if everything is valid
//
//		if (countryId != null && !countryId.isBlank()) {
//			// Check if the country exists
//			int countryPos = searchCountry(countryId);
//			if (countryPos != -1) {
//				Country country = countries.get(countryPos);
//
//				// Check if the country has any products
//				if (!country.getProducts().isEmpty()) {
//
//					// Automatically delete the first product (index 0)
//					int indexProducts = 0;
//					if (indexProducts >= 0 && indexProducts < country.getProducts().size()) {
//						String productsByCountryID = country.getProducts().get(indexProducts).getProductByCountryId();
//						country.getProducts().remove(indexProducts);
//						System.out.println("Product removed successfully.");
//						output = "Deleted Product ID: " + productsByCountryID;
//					} else {
//						output = "Invalid index of Product, try again.";
//					}
//				} else {
//					output = "There are no products in this country.";
//				}
//			} else {
//				output = "Country with the specified ID not found.";
//			}
//		} else {
//			output = "The ID cannot be empty.";
//		}
//
//		return output;
//	}
	
	// Update ProductsByCountry
//	public String updateProductsByCountry(String countryId, String indexProduct, String productsByCountryId, String name, String production,
//			String price) {
//		String output = ""; // Default to an empty string if everything is valid
//
//		if (countryId != null && !countryId.isBlank()) {
//			// Check if the country exists by counryId
//			int countryPos = searchCountry(countryId);
//			if (countryPos != -1) {
//				Country country = countries.get(countryPos);
//
//				// Check if the country has any products
//				if (!country.getProducts().isEmpty()) { // if the products by country is not empty
//					// Validate the product index
//					if (indexProduct != null && !indexProduct.isBlank()) {
//						int productIndexInt = Integer.parseInt(indexProduct);
//						if (productIndexInt >= 0 && productIndexInt < country.getProducts().size()) {
//							ProductsByCountry productToUpdate = country.getProducts().get(productIndexInt);
//							if(productsByCountryId != null && !productsByCountryId.isBlank()) {
//							if (name != null && !name.isBlank()) {
//
//								if (production != null && !production.isBlank()
//										&& production.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
//									double productionDouble = Double.parseDouble(production);
//									if (price != null && !price.isBlank()
//											&& price.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
//										double priceDouble = Double.parseDouble(price);
//										productToUpdate.getProduct().setName(name);
//										productToUpdate.setProduction(productionDouble);
//										productToUpdate.setPrice(priceDouble);
//									} else {
//										output = "The price can not be empty and must be double  ";
//									}
//								} else {
//									output = "The production can not be empty and must be double  ";
//
//								}
//							} else {
//								output = "Product name cannot be empty.";
//
//							}
//						}  else {
//							output = "The product by country ID can not be empty";
//						}
//					}  else {
//						output = "The index of product must be an integer";
//					}
//				}  else {
//					output = "The index of product is invalid";
//				}
//			} else {
//				output = "There are no products in this country.";
//			}
//		} else {
//			output = "Country with the specified ID not found.";
//		} 
//		
//	}else {
//		output = "The Country ID cannot be empty.";
//	}
//		return output;
//	}
		
    /**
     * Deletes a logistics site from the specified country if no dependencies are found.
     * This method checks for linked route lines in the database and ensures the logistics site
     * is not part of any logistics supply chain before deletion. If dependencies exist,
     * the deletion is prevented, and an error message is displayed.
     *
     * @param chains The LogisticsSupplyChains instance, used to access the list of supply chains and check for dependencies.
     */
//    public String deleteLogisticsSite(LogisticsSupplyChains chains) {
//        // Request the country ID
//        String countryId = ProjectHelper.inputStr("Enter the country ID: ");
//        int countryIndex = searchCountry(countryId);
//
//        // Check if the country exists
//        if (countryIndex == -1) {
//            System.out.println("Country with the specified ID not found.");
//            return null;
//        }
//
//        Country country = countries.get(countryIndex);
//
//        // Check if the country has any logistics sites
//        if (country.getSites().isEmpty()) {
//            System.out.println("There are no logistics sites in this country.");
//            return null;
//        }
//
//        // Display the list of logistics sites in the country
//        System.out.println("List of logistics sites:");
//        for (int i = 0; i < country.getSites().size(); i++) {
//            System.out.println("(" + (i + 1) + ") " + country.getSites().get(i).getName());
//        }
//
//        // Request the logistics site number for deletion
//        int siteIndex = ProjectHelper.inputInt("Select the logistics site number to delete: ") - 1;
//
//        // Validate the selected index
//        if (siteIndex < 0 || siteIndex >= country.getSites().size()) {
//            System.out.println("Invalid selection. Operation canceled.");
//            return null;
//        }
//
//        // Get the selected logistics site
//        LogisticsSite selectedSite = country.getSites().get(siteIndex);
//
////        // Check if the logistics site has any associated transport
////        if (selectedSite.getSuppliedTransports() != null && !selectedSite.getSuppliedTransports().isEmpty()) {
////            System.out.println("Error. The logistics site has associated transport. Deletion is not possible.");
////            return null;
////        }
//
//        // Check if the selected site is part of any active logistics supply chain
//        boolean isPartOfChain = chains.getSupplyChains().stream()
//                .anyMatch(chain -> chain.getSender().equals(selectedSite) || chain.getReceiver().equals(selectedSite));
//
//        if (isPartOfChain) {
//            System.out.println("Error. The logistics site is part of an active supply chain. Deletion is not possible.");
//            return null;
//        }
//
//       
//      
//        // Use RestAPIHelper to check for route lines linked to the logistics site
//        RestAPIHelper restAPIHelper = new RestAPIHelper();
//        ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate()
//                .getForEntity(restAPIHelper.getRootAPIURL() 
//                              + "routeLines/site/" 
//                              + selectedSite.getSiteId(), 
//                              RouteLine[].class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            RouteLine[] routeLineArr = response.getBody();
//            if (routeLineArr != null && routeLineArr.length == 0) {
//            
//            	 String deletedSiteId = selectedSite.getSiteId();
//                 country.getSites().remove(siteIndex);
//
//                 System.out.println("Logistics site successfully deleted.");
//                 return deletedSiteId;
//            }
//        } else {
//            System.out.println("Failed to check route lines. Server returned status: " + response.getStatusCode());
//            return null;
//        }
//        return null;
//    }

}


    

