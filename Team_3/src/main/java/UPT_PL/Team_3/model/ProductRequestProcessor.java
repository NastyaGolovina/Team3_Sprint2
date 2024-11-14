package UPT_PL.Team_3.model;

import java.util.ArrayList;

import java.util.UUID;
/**
 * The ProductRequestProcessor class manages supply requests by processing data for products
 * and countries, storing product requests by country and country requests by product.
 * It also calculates supply requests based on given countries and products.
 */
public class ProductRequestProcessor {

	//	Instance variable
    private ArrayList<SupplyReceiveProductByCountry> productRequestByCountry;  // List of product requests by country
    private ArrayList<SupplyReceiveCountryByProduct> countryRequestByProducts;  // List of country requests by product
    private String curCalculationId;  // ID for the current calculation
    


    /**
     * 
     */
    public ProductRequestProcessor() {
        this.productRequestByCountry = new ArrayList<>();  // Initialize product requests by country
        this.countryRequestByProducts = new ArrayList<>();  // Initialize country requests by product
        this.curCalculationId = UUID.randomUUID().toString();  // Initialize current calculation ID to 0
    }
    
  

  /**
   * SupplyReceive
   */
    enum SupplyReceive {
    	Supply,Receive;
	}
	
    /**
     * calcSuppyRequest
     * @param products
     * @param countries
     */
    public void calcSupplyRequest(Products products,Countries countries) {
    	for (Country country : countries.getCountries()) {
    		SupplyReceiveCountryByProduct supplyReceiveCountryByProduct = new SupplyReceiveCountryByProduct(country);
    		for(ProductsByCountry productByCountry : country.getProducts()) {
    			int population = country.getPopulation();
    			double production = productByCountry.getProduction();
    			double recommenedRate = productByCountry.getProduct().getRecommendedRate(); 
    			double totalRecommenedRate = recommenedRate * population;
    			double Qty = 0;
    			if((production/population) > recommenedRate ) {
    				Qty = production - totalRecommenedRate; 
    				supplyReceiveCountryByProduct.addToSupply(new SupplyReceiveByProduct(productByCountry.getProduct(),Qty));
    				addProductByCountry(country,productByCountry.getProduct(),Qty,SupplyReceive.Supply);
    			} else if((production/population) < recommenedRate ) {
    				Qty = totalRecommenedRate - production; 
    				supplyReceiveCountryByProduct.addToReceive(new SupplyReceiveByProduct(productByCountry.getProduct(),Qty));
    				addProductByCountry(country,productByCountry.getProduct(),Qty,SupplyReceive.Receive);
    			}
    		}
    		countryRequestByProducts.add(supplyReceiveCountryByProduct);
    	}
    }
    
    /**
     * addProductByCountry
     * @param country
     * @param product
     * @param Qty
     * @param supplyReceive
     */
    public void addProductByCountry(Country country, Product product,double Qty, SupplyReceive supplyReceive) {
    	int productRequestByCountryPos = searchProductById(product.getProductID());
    	SupplyReceiveByCountry supplyReceiveByCountry = new SupplyReceiveByCountry(country,Qty);
    	if (productRequestByCountryPos == -1) {
    		productRequestByCountry.add(new SupplyReceiveProductByCountry(product));
    	} 
    	SupplyReceiveProductByCountry supplyReceiveProductByCountry = productRequestByCountry.get(searchProductById(product.getProductID()));
    	
    	switch(supplyReceive) {
			case Supply :
				supplyReceiveProductByCountry.addToSupply(supplyReceiveByCountry);
				break;
			case Receive :
				supplyReceiveProductByCountry.addToReceive(supplyReceiveByCountry);
				break;
    	}
    }
 
    /**
     * searchProductById
     *
     * @param productId The ID of the product to search for.
     * @return The index of the SupplyReceiveProductByCountry in the list if found; -1 otherwise.
     */
    public int searchProductById(String productId) {
    	int i = 0;
		 while (i < productRequestByCountry.size() && !productRequestByCountry.get(i).getProduct().getProductID().equalsIgnoreCase(productId)){
			 i++;
		 }
		 if ( i != productRequestByCountry.size()) {
			 return i;
		 }
		 return -1;
    }
    
    

    // Getters and setters...
    
    /**
     * Gets the list of product requests by country.
     * 
     * @return An ArrayList of SupplyReceiveProductByCountry objects representing product requests.
     */
    public ArrayList<SupplyReceiveProductByCountry> getProductRequestByCountry() {
        return productRequestByCountry;
    }

    /**
     * Sets the list of product requests by country.
     * 
     * @param productRequestByCountry The list of SupplyReceiveProductByCountry objects to set.
     */
    public void setProductRequestByCountry(ArrayList<SupplyReceiveProductByCountry> productRequestByCountry) {
        this.productRequestByCountry = productRequestByCountry;
    }

    /**
     * Gets the list of country requests by products.
     * 
     * @return An ArrayList of SupplyReceiveCountryByProduct objects representing country requests.
     */
    public ArrayList<SupplyReceiveCountryByProduct> getCountryRequestByProducts() {
        return countryRequestByProducts;
    }

    /**
     * Sets the list of country requests by products.
     * 
     * @param countryRequestByProducts The list of SupplyReceiveCountryByProduct objects to set.
     */
    public void setCountryRequestByProducts(ArrayList<SupplyReceiveCountryByProduct> countryRequestByProducts) {
        this.countryRequestByProducts = countryRequestByProducts;
    }

    /**
     * Gets the current calculation ID.
     * 
     * @return The current calculation ID as an Integer.
     */
    public String getCurCalculationId() {
        return curCalculationId;
    }

    /**
     * Sets the current calculation ID.
     * 
     * @param curCalculationId The current calculation ID to set.
     */
    public void setCurCalculationId(String curCalculationId) {
        this.curCalculationId = curCalculationId;
    }
}
