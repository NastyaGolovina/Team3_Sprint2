package UPT_PL.Team_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;

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
    public Transport addTransport() {
        String transportId = ProjectHelper.inputStr("Input Transport ID:");

        // Check if the ID is null or empty
        if (transportId == null || transportId.isEmpty()) {
            System.out.println("The ID cannot be null or empty.");
            return null;  
        } else if (!transportId.matches("^[a-zA-Z0-9].*")) {
            System.out.println("The ID cannot begin with special characters.");
            return null;
        } else if (transportId.length() > 20) {
            System.out.println("The ID cannot exceed more than 20 characters.");
            return null;
        }

        int transportPos = searchTransport(transportId);

        if (transportPos != -1) {
            System.out.println("Transport already exists with ID: " + transportId);
            return null;  
        } else {
            String name = ProjectHelper.inputStr("Input Transport Name: ");
            double pricePerTon = ProjectHelper.inputDouble("Input pricePerTon (must be greater than 0): ");

            if (pricePerTon <= 0) {
                System.out.println("Price cannot be 0 or negative.");
                return null;  
            } else {
                Transport transport = new Transport(transportId, name, pricePerTon);
                transports.add(transport);
                
                System.out.println("Transport ID: " + transportId + ", Name: " + name + ", PricePerTon: " + pricePerTon
                        + " added to the list successfully.");
                
                return transport;  
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
 	
	// Delete Transport From List
	
	public boolean deleteTransportById( String transportId, ArrayList<Country>countries, LogisticsSupplyChains logisticsSupplyChains) {
	    
	    int transportIndex = searchTransport(transportId);

	    // Check if the transport exists in the list
	    if (transportIndex == -1) {
	        System.out.println("Transport with the specified ID not found.");
	        return false;  
	    }

	    Transport transport = transports.get(transportIndex);

	    // 1. Check if the transport is linked to any LogisticsSupplyChain
	    
	    for (LogisticsSupplyChain supplyChain : logisticsSupplyChains.getSupplyChains()) {
	        if (supplyChain.getTransport().getTransportId().equals(transportId)) {
	            System.out.println("Cannot delete transport. It is linked to LogisticsSupplyChain.");
	            return false;  
	        }
	    }

	    // 2. Check if the transport is linked to any LogisticsSite
	    for (Country country : countries) {  
	        for (LogisticsSite site : country.getSites()) {
	            if (site.getSuppliedTransports().contains(transport)) {
	                System.out.println("Cannot delete transport. It is linked to a LogisticsSite.");
	                return false;
	            }
	        }
	    }

	   
	    
	    RestAPIHelper restAPIHelper = new RestAPIHelper();
		ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate().
												getForEntity(restAPIHelper.getRootAPIURL() 
												+ "routeLines/transport/" 
														+ transportId,
														RouteLine[].class);
							
		if (response.getStatusCode().is2xxSuccessful()) {
			RouteLine[] routeLineArr = response.getBody();
			if (routeLineArr != null) {	
				if(routeLineArr.length == 0) {
					// Proceed to delete the transport from the list
				    transports.remove(transportIndex);
				    System.out.println("Transport successfully deleted.");
				    return true;
				}
			}  else {
				System.out.println("Cannot delete transport. It is linked to RouteLine.");
			}
		}
	    
	    return false;

	}
}