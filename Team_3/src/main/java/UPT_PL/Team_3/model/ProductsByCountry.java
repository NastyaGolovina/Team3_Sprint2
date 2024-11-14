package UPT_PL.Team_3.model;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * The ProductsByCountry class represents a mapping between products, countries, 
 * and the production and pricing data for those products in each country. 
 * In essence, it captures the relationship between a product, 
 * the country where it is produced, the quantity of that production, and its price
 */

//Use the @Entity and @Table annotations before the class to map it to the table:
@Entity
@Table (name = "Products by Country")
public class ProductsByCountry {
	@Id
	@Column(name = "productByCountry_Id", length = 40, nullable = false )
	private String productByCountryId; //to ensure global uniqueness. 
	@ManyToOne
	@JoinColumn(name ="Product_ID")
	private Product product; //Associated with Product Class,represents the product being produced.
	@Column(name = "production", nullable = false )
	private double production;
	@Column(name = "price", nullable = false )
	private double price;
	@ManyToOne
	@JoinColumn(name ="Country_Id")
	private Country country; //Represents the country in which the product is produced
	
	
	public ProductsByCountry() {
	
	}
	
	
	/**
	 * @param productByCountryId
	 * @param product
	 * @param production
	 * @param price
	 * @param country
	 */
	public ProductsByCountry(Product product, double production, double price,
			Country country) {
		this.productByCountryId = UUID.randomUUID().toString(); //// Generates a new unique identifier
		this.product = product;
		this.production = production;
		this.price = price;
		this.country = country;
	}
	
	public String getProductByCountryId() {
		return productByCountryId;
	}

	public void setProductByCountryId(String productByCountryId) {
		this.productByCountryId = productByCountryId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getProduction() {
		return production;
	}

	public void setProduction(double production) {
		this.production = production;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "ProductsByCountry [productByCountryId=" + productByCountryId + ", product=" + product + ", production="
				+ production + ", price=" + price + "]";
	}

}
