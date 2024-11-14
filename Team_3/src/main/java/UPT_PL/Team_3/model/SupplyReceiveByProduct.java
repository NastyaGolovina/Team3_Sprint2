package UPT_PL.Team_3.model;

/** This SupplyReceiveByProduct class represents supply or receive record for a specific product
 *  It contains information about the product and the quantity of that product
 */
public class SupplyReceiveByProduct {
	// variables
	private Product product; // Associated with Product class and is the product is being supplied or received 
	private double quantity; // The quantity of the product is being supplied or received 
	
	/** Constructor to initialize the SupplyReceiveByProduct object with provided 
	 * 
	 * @param product
	 * @param quantity
	 */
	public SupplyReceiveByProduct(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
		
	}

	/** Get the Product which being supplied or received 
	 * 
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	
	/** Set the Product which being supplied or received
	 * 
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/** Get the amount of product which being supplied or received
	 * 
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/** Set  the amount of product which being supplied or received
	 * 
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "SupplyReceiveByProduct [product=" + product + ", quantity=" + quantity + "]";
	}
	
}

