package UPT_PL.Team_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

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
    public void addCountry() {
        String countryId = ProjectHelper.inputStr("Input Country ID: ");

        if (countryId.isEmpty()) {  
            System.out.println("The ID cannot be empty.");
            return;  
            
          }  else if (!countryId.matches("^[a-zA-Z0-9].*")) {
                System.out.println("The ID cannot begin with special characters.");
                return;
                
        } else if (countryId.length() > 20) {
            System.out.println("The ID cannot exceed more than 20 characters.");
            return; 
        }

        int countryPos = searchCountry(countryId);

        if (countryPos != -1) { 
            System.out.println("Country already exists with ID: " + countryId);
        } else {
            String name = ProjectHelper.inputStr("Input Country Name: ");
            int population = ProjectHelper.inputInt("Input Population (must be greater than 0): ");

            // Validate Population
            while (population <= 0) {
                System.out.println("Population must be greater than 0. Please try again.");
                population = ProjectHelper.inputInt("Input Population: ");
            }

            // Create and add new Country object
            Country newCountry = new Country(countryId, name, population);
            countries.add(newCountry);
            
            DatabaseHelper DatabaseHelper = new DatabaseHelper();
			DatabaseHelper.setup();
			Session session = DatabaseHelper.getSessionFactory().openSession();
			session.beginTransaction();

			session.persist(newCountry);

			session.getTransaction().commit();
			session.close();
			DatabaseHelper.exit();
			
            System.out.println("Country added successfully: " + newCountry);
        }
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
    
    /**
     * Method to read all countries from the database and update the countries list.
     * @return A list of Country objects read from the database.
     */
    protected void readAllCountriesWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<Country> countries = session.createQuery("SELECT c FROM Country c",Country.class).getResultList();
    	
    	this.countries = (ArrayList<Country>)countries;
    	
    	session.close();
    	DatabaseHelper.exit();
    }
    /**
     * readAllLogisticsSitesWithJplq
     */
    protected void readAllLogisticsSitesWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<LogisticsSite> sites = session.createQuery("SELECT l FROM LogisticsSite l",LogisticsSite.class).getResultList();
    	

        for (LogisticsSite l : sites) {
            Hibernate.initialize(l.getSuppliedTransports());
        }
    	
		for(Country c : this.countries) {
			for(LogisticsSite l : sites) {
				if(l.getCountry().getCountryId().equalsIgnoreCase(c.getCountryId())) {
					c.addLogisticsSite(l);
				}
			}
    	}
    	
    	session.close();
    	DatabaseHelper.exit();
    }
    
    /**
     * readAllLogisticsSitesWithJplq
     */
    protected void readAllProductsByCountrysWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<ProductsByCountry> products = session.createQuery("SELECT P FROM ProductsByCountry P",ProductsByCountry.class).getResultList();
    	

    	
		for(Country c : this.countries) {
			for(ProductsByCountry p : products) {
				if(p.getCountry().getCountryId().equalsIgnoreCase(c.getCountryId())) {
					c.addProductsByCountry(p);
				}
			}
    	}
    	
    	session.close();
    	DatabaseHelper.exit();
    }
  
     // DELETE Country by Id
    
    public void deleteCountryById() {
        // Prompt for the country ID to delete
        String countryId = ProjectHelper.inputStr("Enter the country ID to delete: ");
        int countryIndex = searchCountry(countryId);

        // Check if the country exists
        if (countryIndex == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

        // Retrieve the selected country object
        Country country = countries.get(countryIndex);

        // Set up database session for dependency checks
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        // 1. Check if the country is linked to any ProductsByCountry
        List<ProductsByCountry> productsByCountryList = session.createQuery(
                "FROM ProductsByCountry pcb WHERE pcb.country.id = :countryId", ProductsByCountry.class)
                .setParameter("countryId", countryId)
                .getResultList();

        if (!productsByCountryList.isEmpty()) {
            System.out.println("Cannot delete country. It is linked to ProductsByCountry.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // 2. Check if the country is linked to any SupplyReceiveByCountry
        List<SupplyReceiveCountryByProduct> supplyReceiveByCountryList = session.createQuery(
                "FROM SupplyReceiveCountryByProduct srcbp WHERE srcbp.country.id = :countryId", SupplyReceiveCountryByProduct.class)
                .setParameter("countryId", countryId)
                .getResultList();

        if (!supplyReceiveByCountryList.isEmpty()) {
            System.out.println("Cannot delete country. It is linked to SupplyReceiveByCountry.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // 3. Check if the country is linked to any RouteLine (using query)
        List<RouteLine> routeLines = session.createQuery(
                "FROM RouteLine rl WHERE rl.countrySender.id = :countryId OR rl.countryReceiver.id = :countryId", RouteLine.class)
                .setParameter("countryId", countryId)
                .getResultList();

        if (!routeLines.isEmpty()) {
            System.out.println("Cannot delete country. It is linked to RouteLine.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // Proceed to delete the country from the list and the database
        countries.remove(countryIndex);

        session.beginTransaction();
        session.remove(country); // Delete the country from the database
        session.getTransaction().commit();

        session.close();
        databaseHelper.exit();

        System.out.println("Country successfully deleted.");
    }

// Method to delete product by country, delete product from a certain country
    
    public void deleteProductsByCountry() {
    	
    	 // Prompt for the country ID to delete
        String countryId = ProjectHelper.inputStr("Enter the country ID to delete: ");
        int countryPos = searchCountry(countryId);
        
        // Check if the country exists
        if (countryPos == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

       Country country = countries.get(countryPos);
       
       // check if country has any products
       if(country.getProducts().isEmpty()) {
    	   System.out.println("There are no products list in this country.");
          return;
       }
       // Display the list of products in the country
       System.out.println("List of products :");
       for (int i = 0; i < country.getProducts().size(); i++) {
           System.out.println("(" + (i) + ") " + country.getProducts().get(i));
       }
       
      // Ask for the index/position of the products in the previous list to delete
       
     int indexProducts = ProjectHelper.inputInt("Enter the index of the product to delete: ");
       
     // validation for the index of product
     if(indexProducts < 0 || indexProducts >= country.getProducts().size()) {
    	 System.out.println("Invalid index of Product, try again");
    	 return;
     }
     
   //Get/Retrieve the selected ProductByCountry
     ProductsByCountry selectedProductsByCountry = country.getProducts().get(indexProducts);
     
     // remove the product by country from the ProductsByCountry list 
     country.getProducts().remove(indexProducts);
     System.out.println("Product removed successfully.");
     
     // Display remaining products
     System.out.println("Updated list of products:");
     for (int i = 0; i < country.getProducts().size(); i++) {
         System.out.println("(" + i + ") " + country.getProducts().get(i));
     }
     
     // Proceed to delete the country from the database
     // Set up database session for dependency checks
     DatabaseHelper databaseHelper = new DatabaseHelper();
     databaseHelper.setup();
     Session session = databaseHelper.getSessionFactory().openSession();

    
     // Delete the country from the database
     session.beginTransaction();
     session.remove(selectedProductsByCountry); // Delete the country from the database
     session.getTransaction().commit();

     session.close();
     databaseHelper.exit();

     System.out.println("Product successfully deleted.");
	}
    

    /**
     * Deletes a logistics site from the specified country if no dependencies are found.
     * This method checks for linked route lines in the database and ensures the logistics site
     * is not part of any logistics supply chain before deletion. If dependencies exist,
     * the deletion is prevented, and an error message is displayed.
     *
     * @param chains The LogisticsSupplyChains instance, used to access the list of supply chains and check for dependencies.
     */
    public void deleteLogisticsSite(LogisticsSupplyChains chains) {
        // Request the country ID
        String countryId = ProjectHelper.inputStr("Enter the country ID: ");
        int countryIndex = searchCountry(countryId);

        // Verify if the country exists
        if (countryIndex == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

        Country country = countries.get(countryIndex);

        // Check if the country has any logistics sites
        if (country.getSites().isEmpty()) {
            System.out.println("There are no logistics sites in this country.");
            return;
        }

        // Display the list of logistics sites in the country
        System.out.println("List of logistics sites:");
        for (int i = 0; i < country.getSites().size(); i++) {
            System.out.println("(" + (i + 1) + ") " + country.getSites().get(i).getName());
        }

        // Request the logistics site number for deletion
        int siteIndex = ProjectHelper.inputInt("Select the logistics site number to delete: ") - 1;

        // Validate the selected index
        if (siteIndex < 0 || siteIndex >= country.getSites().size()) {
            System.out.println("Invalid selection. Operation canceled.");
            return;
        }

        // Get the selected logistics site
        LogisticsSite selectedSite = country.getSites().get(siteIndex);

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        // Check if the selected site is part of any supply chain (sender or receiver)
        boolean isPartOfChain = chains.getSupplyChains().stream()
                .anyMatch(chain -> chain.getSender().equals(selectedSite) || chain.getReceiver().equals(selectedSite));

        // If the site is part of a supply chain, prevent deletion
        if (isPartOfChain) {
            System.out.println("Error. The logistics site is part of an active supply chain. Deletion is not possible.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // Query route lines that are linked to the selected site as either origin or destination
        List<RouteLine> routeLines = session.createQuery(
                "FROM RouteLine rl WHERE rl.originSite.id = :siteId OR rl.destinationSite.id = :siteId", RouteLine.class)
                .setParameter("siteId", selectedSite.getSiteId())
                .getResultList();

        // If there are route lines linked to the site, prevent deletion
        if (!routeLines.isEmpty()) {
            System.out.println("Error. You need to delete all the route lines associated with this logistics site before deleting it.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // Remove the logistics site from the country's list
        country.getSites().remove(siteIndex);

        // Begin a database transaction for deletion
        session.beginTransaction();
        session.remove(selectedSite); // Remove the site from the database
        session.getTransaction().commit(); // Commit the transaction to confirm deletion

        session.close();
        databaseHelper.exit();

        System.out.println("Logistics site successfully deleted.");
    }
}


    

