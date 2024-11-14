package UPT_PL.Team_3.model;

/*
 * The SupplyReceiveByCountry class represents a supply received by a specific country.
 * It contains a reference to a country and the quantity of the supply received.
 * The class provides constructors for initialization and methods for validation and access.
 */
public class SupplyReceiveByCountry {
    private Country country;  // Reference to a Country object
    private double quantity;  // Quantity of the received supply
   

    /*
     * Default constructor that initializes with default values.
     */
    public SupplyReceiveByCountry() {
      
    }

    /*
     * Constructor that initializes the country and quantity with provided values.
     * Validates the inputs using setter methods.
     * 
     * @param country  The Country object representing the country receiving the supply.
     * @param quantity The quantity of the supply received. Must be greater than 0.
     */
    public SupplyReceiveByCountry(Country country, double quantity) {
        setCountry(country);  // Use setter for validation
        setQuantity(quantity);  // Use setter for validation
    }

    /*
     * Gets the country receiving the supply.
     * 
     * @return The Country object representing the country.
     */
    public Country getCountry() {
        return country;
    }

    /*
     * Sets the country receiving the supply.
     * Ensures the country is not null.
     * 
     * @param country The Country object to set.
     * @throws IllegalArgumentException if the country is null.
     */
    public void setCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country cannot be null.");
        }
        this.country = country;
    }

    /*
     * Gets the quantity of the supply received.
     * 
     * @return The quantity of the supply.
     */
    public double getQuantity() {
        return quantity;
    }

    /*
     * Sets the quantity of the supply received.
     * Ensures the quantity is positive.
     * 
     * @param quantity The quantity of the supply.
     * @throws IllegalArgumentException if the quantity is less than or equal to 0.
     */
    public void setQuantity(double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.quantity = quantity;
    }
}
