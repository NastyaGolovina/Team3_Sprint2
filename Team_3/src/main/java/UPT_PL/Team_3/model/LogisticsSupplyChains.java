package UPT_PL.Team_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * The LogisticsSupplyChains class manages a collection of LogisticsSupplyChain objects.
 */
public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains; // List to store all supply chains
    
    /**
     * Constructor that initializes an empty list of supply chains and accepts logistics sites and transports.
     */
    public LogisticsSupplyChains() {
        this.supplyChains = new ArrayList<>(); // Create a new empty list for supply chains
    }
    
//    public LogisticsSupplyChains() {
//       
//    }
    
    /**
     * addNewSupplyChain overloading
     * @param logisticsSupplyChain
     */
    public void addNewSupplyChain(LogisticsSupplyChain logisticsSupplyChain) {
    	supplyChains.add(logisticsSupplyChain);
    }
    /**
     * addNewSupplyChain overloading
     * @param countries
     */
    public void addNewSupplyChain(Countries countries) {
        // 1. Enter the ID for the new Supply Chain
        // Using String type because we compare it with getChainId, which returns a String
        String chainId = ProjectHelper.inputStr("Enter ID for the new Supply Chain:");
     
        // Check for ID uniqueness
        for (LogisticsSupplyChain chain : supplyChains) {
            if (chain.getChainId().equalsIgnoreCase(chainId)) {
                System.out.println("Error: A Supply Chain with this ID already exists.");
                return; // Exit the method if the ID already exists
            }
        }
     
        // 2. Enter and find the first logistics site (Sender)
        String senderSiteId = ProjectHelper.inputStr("Enter the ID of the sender logistics site:");
        LogisticsSite sender = findLogisticsSiteById(countries, senderSiteId);
        if (sender == null) {
            System.out.println("Error: Logistics site with this ID was not found.");
            return; // Exit the method if the site is not found
        }
     
        // 3. Enter and find the second logistics site (Receiver)
        String receiverSiteId = ProjectHelper.inputStr("Enter the ID of the receiver logistics site:");
        // Ensure the sender and receiver IDs are different
        if (receiverSiteId.equals(senderSiteId)) {
            System.out.println("Error: Sender and receiver logistics sites must be different.");
            return; // Exit the method if the sites are the same
        }
     
        // Find the receiver logistics site
        LogisticsSite receiver = findLogisticsSiteById(countries, receiverSiteId);
        if (receiver == null) {
            System.out.println("Error: Logistics site with this ID was not found.");
            return; // Exit the method if the site is not found
        }
     
        // 4. Enter and validate transport
        String transportId = ProjectHelper.inputStr("Enter the transport ID:");
        // Find the transport supported by both logistics sites
        Transport selectedTransport = findTransportInSites(sender, receiver, transportId);
        if (selectedTransport == null) {
            System.out.println("Error: This transport is not supported by either of the selected sites.");
            return; // Exit the method if the transport is not supported
        }
     
        // 5. Enter the cost per ton
        double costPerTon = ProjectHelper.inputDouble("Enter the cost per ton (must be > 0):");
     
        // 6. Enter the duration in days
        double durationInDays = ProjectHelper.inputDouble("Enter the duration in days (must be > 0):");
     
        // 7. Create a new LogisticsSupplyChain object and add it to the supplyChains list
        LogisticsSupplyChain newChain = new LogisticsSupplyChain(chainId, sender, receiver, selectedTransport, costPerTon, durationInDays);
        supplyChains.add(newChain);
        
        // Output the newly created object
        System.out.println("New Supply Chain successfully created: " + newChain);
        
        
      //ADD TO DB!!!!!!(Temp in test by daria hukallo)
        addNewSupplyChainToDB(newChain);
        //!!!!!!!!!!!!!!!!
    }
     
    // Helper method to find a logistics site by its ID
    private LogisticsSite findLogisticsSiteById(Countries countries, String siteId) {
        // Loop through all countries
        for (Country country : countries.getCountries()) {
            // Loop through all logistics sites in the country
            for (LogisticsSite site : country.getSites()) {
                if (site.getSiteId().equals(siteId)) {
                    return site; // Return the found site
                }
            }
        }
        return null; // Return null if the site is not found
    }
     
    // Helper method to find transport that is supported by both logistics sites
    private Transport findTransportInSites(LogisticsSite sender, LogisticsSite receiver, String transportId) {
        // Check the transport at the sender site
        for (Transport transport : sender.getSuppliedTransports()) {
            if (transport.getTransportId().equals((transportId))) {
                // Check the transport at the receiver site
                for (Transport transportReceiver : receiver.getSuppliedTransports()) {
                    if (transportReceiver.getTransportId().equals((transportId))) {
                        return transport; // Return the found transport
                    }
                }
            }
        }
        return null; // Return null if the transport is not found
    }

	/**
	 * @return the supplyChains
	 */
	public ArrayList<LogisticsSupplyChain> getSupplyChains() {
		return supplyChains;
	}

	/**
	 * @param supplyChains the supplyChains to set
	 */
	public void setSupplyChains(ArrayList<LogisticsSupplyChain> supplyChains) {
		this.supplyChains = supplyChains;
	}
	
	 /**
     * Displays all logistics supply chains.
     * If the list of supply chains is empty, it will display a message indicating
     * no supply chains are available.
     */
    public void displayAll() {
        if (supplyChains.isEmpty()) {
            System.out.println("No supply chains available.");
        } else {
            for (LogisticsSupplyChain chain : supplyChains) {
                System.out.println(chain);
            }
        }
    }
    
    //METHODS FOR DB!!!!!!!!!!!!!!!!!!!!!!!!!
    
    /**
     * Method to add a new supply chain to the database and update the local list.
     * 
     * @param logisticsSupplyChain The LogisticsSupplyChain object to be added.
     */
    public void addNewSupplyChainToDB(LogisticsSupplyChain logisticsSupplyChain) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Session session = null; // Session for Hibernate operations

        try {
            databaseHelper.setup();
            session = databaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            // Check if the supply chain ID is unique before proceeding
            if (isChainIdUnique(logisticsSupplyChain.getChainId(), session)) {
                session.persist(logisticsSupplyChain); // Save the supply chain object to the database
                session.getTransaction().commit();
//                supplyChains.add(logisticsSupplyChain); // Add the new supply chain to the local list
                System.out.println("New Supply Chain successfully added: " + logisticsSupplyChain);
            } else {
                System.out.println("Error: A Supply Chain with this ID already exists.");
            }
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error occurred while adding supply chain: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
            databaseHelper.exit();
        }
    }

    /**
     * Method to read all supply chains from the database and update the local supplyChains list.
     */
    /**
     * Method to read all supply chains from the database and update the local supplyChains list.
     */
    public void readAllSupplyChainsFromDB() {
        // Create an instance of DatabaseHelper for managing database connections
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Session session = null; // Declare a session for Hibernate operations

        try {
            // Set up the database connection
            databaseHelper.setup();
            // Open a new session from the session factory
            session = databaseHelper.getSessionFactory().openSession();
            // Begin a new transaction
            session.beginTransaction();

            // Execute a query to read all supply chains from the database
            List<LogisticsSupplyChain> chainsFromDb = session.createQuery("FROM LogisticsSupplyChain", LogisticsSupplyChain.class).getResultList();
            
            // Update the local list of supply chains with the result from the database
            this.supplyChains = new ArrayList<>(chainsFromDb); 
            System.out.println("Supply Chains loaded successfully.");
            
            // Commit the transaction to save changes (not necessary here as it's a read operation, but good practice)
            session.getTransaction().commit();
        } catch (Exception e) {
            // Roll back the transaction if an error occurs and it is active
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            // Print an error message to the console
            System.out.println("Error occurred while reading supply chains: " + e.getMessage());
        } finally {
            // Close the session to release database resources
            if (session != null) {
                session.close();
            }
            // Exit from the database helper, cleaning up resources
            databaseHelper.exit();
        }
    }
    /**
     * Checks if the given chain ID is unique in the database.
     * 
     * @param chainId The ID of the supply chain to check.
     * @param session The current Hibernate session used for the database query.
     * @return true if the chain ID is unique, false otherwise.
     */
    private boolean isChainIdUnique(String chainId, Session session) {
        Long count = (Long) session.createQuery("SELECT COUNT(c) FROM LogisticsSupplyChain c WHERE c.chainId = :chainId")
            .setParameter("chainId", chainId)
            .uniqueResult(); // Execute the query and get the single result
        return count == 0; // Return true if the count is zero (ID is unique)
    }
    
    /**
     * Deletes a supply chain by ID from both the array list and the database.
     *
     * @param chainId The ID of the supply chain to delete.
     */
    //TEST
    public void deleteSupplyChainById(String chainId) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Session session = null;

        try {
            // Initialize the database connection using the helper
            databaseHelper.setup();
            session = databaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            // Query the database for the supply chain with the specified ID
            LogisticsSupplyChain chainToDelete = session.createQuery(
                    "FROM LogisticsSupplyChain WHERE chainId = :chainId", LogisticsSupplyChain.class)
                    .setParameter("chainId", chainId)
                    .uniqueResult(); // Retrieve the single result matching the chain ID

            // Check if the supply chain exists in the database
            if (chainToDelete != null) {
                // Remove the supply chain from the database
                session.remove(chainToDelete);
                session.getTransaction().commit(); // Commit the transaction to confirm deletion

                // Remove the supply chain from the local ArrayList
                supplyChains.removeIf(chain -> chain.getChainId().equals(chainId));

                System.out.println("Supply chain with ID " + chainId + " successfully deleted.");
            } else {
                System.out.println("Supply chain with ID " + chainId + " not found.");
            }
        } catch (Exception e) {
            // If an error occurs, roll back the transaction to avoid data inconsistencies
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error occurred while deleting supply chain: " + e.getMessage());
        } finally {
            // Close the session to release database resources
            if (session != null) {
                session.close();
            }
            // Clean up database resources through the helper
            databaseHelper.exit();
        }
    }


  
}
