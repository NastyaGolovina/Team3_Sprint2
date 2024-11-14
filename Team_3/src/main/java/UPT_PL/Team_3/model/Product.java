package UPT_PL.Team_3.model;

/**
 * The Product class represents products
 * Each product contains information such as 
 * unique productID,name, expirationInDays and recommenedRate
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Use the @Entity and @Table annotations before the class to map it to the table:
@Entity
@Table(name = "Products")
public class Product {
	@Id                                                   
	@Column(name = "Product_ID",length = 20, nullable = false)
	private String productID;
	@Column(name = "name",length = 20, nullable = false)
	private String name;
	@Column(name = "ExpirationInDays", nullable = false)
	private Integer expirationInDays;
	@Column(name = "RecommendedRate", nullable = false)
	private double recommendedRate; 



	public Product() {

	}

	/**
	 * Constructor to initialize the Product object with the provided variables
	 * 
	 * @param productID         The unique ID of the product
	 * @param name              The name of the product
	 * @param expiriationIndays The number of days before the product expires
	 * @param recommendedRate   The recommended rate of the product per year
	 */

	public Product(String productID, String name, Integer expirationInDays, double recommendedRate) {
		this.productID = productID;
		this.name = name;
		this.expirationInDays = expirationInDays;
		this.recommendedRate = recommendedRate;
	}

	
	/**
	 * Get the unique ID of the product
	 * 
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * Set the unique ID of the product
	 * 
	 * @param productID the new productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	 * Get the name of the product
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the product
	 * 
	 * @param name the new name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the amount of days before the product gets expired
	 * 
	 * @return the expirationInDays
	 */
	public Integer getExpirationInDays() {
		return expirationInDays;
	}

	/**
	 * Set the amount of days before the product gets expired
	 * 
	 * @param expirationInDays the new expirationInDays to set
	 */
	public void setExpirationInDays(Integer expirationInDays) {
		this.expirationInDays = expirationInDays;
	}

	/**
	 * Get the Recommend rate of that product per year
	 * 
	 * @return the recommenedRate
	 */
	public double getRecommendedRate() {
		return recommendedRate;
	}

	/**
	 * Set the Recommend rate of that product per year
	 * 
	 * @param recommendedRate the new recommenedRate to set
	 */
	public void setRecommendedRate(double recommendedRate) {
		this.recommendedRate = recommendedRate;
	}

	@Override
	public String toString() {
		return "Product [ProductID=" + productID + ", Name=" + name + ", Expiration In Days=" + expirationInDays
				+ ", Recommended Rate=" + recommendedRate + "]";
	}

}
