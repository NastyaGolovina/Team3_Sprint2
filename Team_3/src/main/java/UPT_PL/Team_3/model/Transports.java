package UPT_PL.Team_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

/**
 * The Transports class represents a collection of Transport objects. It
 * provides methods to add new transports to the list and manage the collection.
 */
public class Transports {
    // List to store all Transport objects
    private ArrayList<Transport> transports;

    /**
     * Constructor to initialize the Transports object with an empty list of transports.
     */
    public Transports() {
        this.transports = new ArrayList<>();
    }

    /**
	 * @return the transports
	 */
	public ArrayList<Transport> getTransports() {
		return transports;
	}

	/**
	 * @param transports the transports to set
	 */
	public void setTransports(ArrayList<Transport> transports) {
		this.transports = transports;
	}

	/**
     * Searches for a transport by its ID.
     * 
     * @param transportId The ID of the transport to search for.
     * @return The index of the transport if found, -1 otherwise.
     */
    private int searchTransport(String transportId) {
        for (int i = 0; i < transports.size(); i++) {
            if (transports.get(i).getTransportId().equalsIgnoreCase(transportId)) {
                return i;
            }
        }
        return -1; // Return -1 if not found
    }
    
  
    /**
     * Method to add a new Transport object to the list of transports.
     */
    public void addTransport() {
        String transportId = ProjectHelper.inputStr("Input Transport ID:");

        if (transportId.isEmpty()) {
            System.out.println("The ID cannot be empty.");
            return;
            
        } else if (!transportId.matches("^[a-zA-Z0-9].*")) {
                System.out.println("The ID cannot begin with special characters.");
                return;
                
        } else if (transportId.length() > 20) {
            System.out.println("The ID cannot exceed more than 20 characters.");
            return;
        }

        int transportPos = searchTransport(transportId);

        if (transportPos != -1) {
            System.out.println("Transport already exists with ID: " + transportId);
        } else {
            String name = ProjectHelper.inputStr("Input Transport Name: ");
            double pricePerTon = ProjectHelper.inputDouble("Input pricePerTon (must be greater than 0): ");

            if (pricePerTon <= 0) {
                System.out.println("Price cannot be 0 or negative.");
            } else {
                Transport transport = new Transport(transportId, name, pricePerTon);
                transports.add(transport);
                
                DatabaseHelper DatabaseHelper = new DatabaseHelper();
                DatabaseHelper.setup();
                Session session = DatabaseHelper.getSessionFactory().openSession();
                session.beginTransaction();
                
                session.persist(transport);
                
                session.getTransaction().commit();
                session.close();
                DatabaseHelper.exit();
                
                System.out.println("Transport ID: " + transportId + ", Name: " + name + ", PricePerTon: " + pricePerTon
                        + " added to the list successfully.");
            }
        }
    }

    /**
     * Finds a transport by its ID.
     * 
     * @param transportId The ID of the transport to find.
     * @return The Transport object if found, null otherwise.
     */
    public Transport findTransportById(String transportId) {
        for (Transport transport : transports) {
            if (transport.getTransportId().equalsIgnoreCase(transportId)) {
                return transport;  // Return the transport if found
            }
        }
        return null;  // Return null if transport not found
    }
    
    /**
     * Method to display the list of Transports.
     */
    
    public void displayTransports() {
        if (transports.isEmpty()) {
            System.out.println("The transport list is empty.");
        } else {
            System.out.println("List of Transports:");
            for (Transport transport : transports) {
                System.out.println(transport);
            }
        }
    }
    /**
     * Method to return the list of transports.
     * 
     * @return The list of Transport objects.
     */
    public ArrayList<Transport> getTransportList() {
        return transports;
    }

	@Override
	public String toString() {
		return "Transports [transports=" + transports + "]";
	}
    /**
     * readAllTransportsWithJplq
     */
	protected void readAllTransportsWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<Transport> transports = session.createQuery("SELECT t FROM Transport t",Transport.class).getResultList();
    	this.transports = (ArrayList<Transport>)transports;
    	
    	session.close();
    	DatabaseHelper.exit();
    }
	
	// DELETE Transport by Id
    public void deleteTransport(String transportId) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();
        
        // Fetch the transport by its ID
        Transport transport = session.get(Transport.class, transportId);
        
        if (transport == null) {
            System.out.println("Transport with ID " + transportId + " does not exist.");
            return;
        }

        // Check if the transport is used in any LogisticsSite
        List<LogisticsSite> logisticsSites = session.createQuery("SELECT l FROM LogisticsSite l JOIN l.suppliedTransports t WHERE t = :transport", LogisticsSite.class)
                                                   .setParameter("transport", transport)
                                                   .getResultList();

        if (!logisticsSites.isEmpty()) {
            System.out.println("Cannot delete transport with ID " + transportId + " because it is supplied by one or more logistics sites.");
            return;
        }

        // Check if the transport is linked to any RouteLine
        List<RouteLine> routeLines = session.createQuery("FROM RouteLine r WHERE r.transport = :transport", RouteLine.class)
                                            .setParameter("transport", transport)
                                            .getResultList();

        if (!routeLines.isEmpty()) {
            System.out.println("Cannot delete transport with ID " + transportId + " because it is used in one or more route lines.");
            return;
        }

        // Check if the transport is used in any LogisticsSupplyChain
        List<LogisticsSupplyChain> supplyChains = session.createQuery("FROM LogisticsSupplyChain ls WHERE ls.transport = :transport", LogisticsSupplyChain.class)
                                                         .setParameter("transport", transport)
                                                         .getResultList();

        if (!supplyChains.isEmpty()) {
            System.out.println("Cannot delete transport with ID " + transportId + " because it is used in one or more logistics supply chains.");
            return;
        }

        // If no dependencies exist, delete the transport
        session.beginTransaction();
        session.delete(transport);
        session.getTransaction().commit();
        System.out.println("Transport with ID " + transportId + " has been deleted successfully.");

        session.close();
        databaseHelper.exit();
    }
}
