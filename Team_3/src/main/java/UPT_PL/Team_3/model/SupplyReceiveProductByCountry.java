package UPT_PL.Team_3.model;

import java.util.ArrayList;

/**
 * The SupplyReceiveProductByCountry class manages supply and receive records of a product
 * across different countries. It keeps track of which countries are suppliers and which
 * are receivers based on the product's recommended rate and the amount supplied or received.
 */
public class SupplyReceiveProductByCountry {
    private Product product;  // The product associated with supply and receive records
    private ArrayList<SupplyReceiveByCountry> supplyByCountry;  // List of supply records by country
    private ArrayList<SupplyReceiveByCountry> receiveByCountry;  // List of receive records by country

    /**
     * Default constructor that initializes the supply and receive lists as empty.
     */
//    public SupplyReceiveProductByCountry() {
//        this.supplyByCountry = new ArrayList<>();
//        this.receiveByCountry = new ArrayList<>();
//    }
    
  

   

    /**
	 * @param product
	 */
	public SupplyReceiveProductByCountry(Product product) {
		this.product = product;
		this.supplyByCountry = new ArrayList<SupplyReceiveByCountry>();
        this.receiveByCountry = new ArrayList<SupplyReceiveByCountry>();
	}



	/**
     * Returns the product associated with this instance.
     * 
     * @return The product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product for this instance.
     * 
     * @param product The product to associate.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the list of supply records by country.
     * 
     * @return A list of SupplyReceiveByCountry representing supply records.
     */
    public ArrayList<SupplyReceiveByCountry> getSupplyByCountry() {
        return supplyByCountry;
    }

    /**
     * Sets the list of supply records by country.
     * 
     * @param supplyByCountry The list of supply records to set.
     */
    public void setSupplyByCountry(ArrayList<SupplyReceiveByCountry> supplyByCountry) {
        this.supplyByCountry = supplyByCountry;
    }

    /**
     * Returns the list of receive records by country.
     * 
     * @return A list of SupplyReceiveByCountry representing receive records.
     */
    public ArrayList<SupplyReceiveByCountry> getReceiveByCountry() {
        return receiveByCountry;
    }

    /**
     * Sets the list of receive records by country.
     * 
     * @param receiveByCountry The list of receive records to set.
     */
    public void setReceiveByCountry(ArrayList<SupplyReceiveByCountry> receiveByCountry) {
        this.receiveByCountry = receiveByCountry;
    }

    /**
     * Adds a supply record for a specific country to the supply list.
     * 
     * @param element The supply record to add.
     * @throws IllegalArgumentException if the element is null.
     */
    public void addToSupply(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Supply record cannot be null.");
        }
        supplyByCountry.add(element);
    }

    /**
     * Adds a receive record for a specific country to the receive list.
     * 
     * @param element The receive record to add.
     * @throws IllegalArgumentException if the element is null.
     */
    public void addToReceive(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Receive record cannot be null.");
        }
        receiveByCountry.add(element);
    }

    /**
     * Searches for a supply record by country.
     * 
     * @param country The country to search for.
     * @return The supply record for the specified country, or null if not found.
     */
    public SupplyReceiveByCountry searchByCountry(Country country) {
        for (SupplyReceiveByCountry supply : supplyByCountry) {
            if (supply.getCountry().equals(country)) {
                return supply;
            }
        }
        return null;
    }
}
