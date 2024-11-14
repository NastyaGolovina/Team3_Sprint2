package UPT_PL.Team_3.model;

import java.util.ArrayList;

public class SupplyCombinations {
	//	Instance variable
	private String supplyCombinationsID;
	private Country country;
	private Product product;
	private ArrayList<SupplyCombination> supplyCombinations;
	
	
	
	/**
	 * @param supplyCombinationsID
	 * @param country
	 * @param product
	 * @param supplyCombinations
	 */
	public SupplyCombinations(String supplyCombinationsID, Country country, Product product) {
		this.supplyCombinationsID = supplyCombinationsID;
		this.country = country;
		this.product = product;
		supplyCombinations = new ArrayList<SupplyCombination>();
	}



	/**
	 * @return the supplyCombinationsID
	 */
	public String getSupplyCombinationsID() {
		return supplyCombinationsID;
	}



	/**
	 * @param supplyCombinationsID the supplyCombinationsID to set
	 */
	public void setSupplyCombinationsID(String supplyCombinationsID) {
		this.supplyCombinationsID = supplyCombinationsID;
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



	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}



	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}



	/**
	 * @return the supplyCombinations
	 */
	public ArrayList<SupplyCombination> getSupplyCombinations() {
		return supplyCombinations;
	}



	/**
	 * @param supplyCombinations the supplyCombinations to set
	 */
	public void setSupplyCombinations(ArrayList<SupplyCombination> supplyCombinations) {
		this.supplyCombinations = supplyCombinations;
	}



	@Override
	public String toString() {
		return "SupplyCombinations [supplyCombinationsID=" + supplyCombinationsID + ", country=" + country
				+ ", product=" + product + ", supplyCombinations=" + supplyCombinations + "]";
	}


	/**
	 * addSupplyCombination
	 * @param newSupplyCombination
	 */
	public void addSupplyCombination(SupplyCombination newSupplyCombination) {
		supplyCombinations.add(newSupplyCombination);
	}
	
}
