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
	 * Constructor to initialize the Transports object with an empty list of
	 * transports.
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
	public int searchTransport(String transportId) {
		for (int i = 0; i < transports.size(); i++) {
			if (transports.get(i).getTransportId().equalsIgnoreCase(transportId)) {
				return i;
			}
		}
		return -1; // Return -1 if not found
	}

	/**
	 * Method to add a new Transport object to the list of transports.
	 * 
	 * public Transport addTransport() { String transportId =
	 * ProjectHelper.inputStr("Input Transport ID:");
	 * 
	 * // Check if the ID is null or empty if (transportId == null ||
	 * transportId.isEmpty()) { System.out.println("The ID cannot be null or
	 * empty."); return null; } else if (!transportId.matches("^[a-zA-Z0-9].*")) {
	 * System.out.println("The ID cannot begin with special characters."); return
	 * null; } else if (transportId.length() > 20) { System.out.println("The ID
	 * cannot exceed more than 20 characters."); return null; }
	 * 
	 * int transportPos = searchTransport(transportId);
	 * 
	 * if (transportPos != -1) { System.out.println("Transport already exists with
	 * ID: " + transportId); return null; } else { String name =
	 * ProjectHelper.inputStr("Input Transport Name: "); double pricePerTon =
	 * ProjectHelper.inputDouble("Input pricePerTon (must be greater than 0): ");
	 * 
	 * if (pricePerTon <= 0) { System.out.println("Price cannot be 0 or negative.");
	 * return null; } else { Transport transport = new Transport(transportId, name,
	 * pricePerTon); transports.add(transport);
	 * 
	 * System.out.println("Transport ID: " + transportId + ", Name: " + name + ",
	 * PricePerTon: " + pricePerTon + " added to the list successfully.");
	 * 
	 * return transport; } } }
	 */

	public String addTransport(String transportId, String name, String pricePerTon, Transport newTransport) {
		String output = "";

		if (transportId != null && !transportId.isBlank()) {
			if (transportId.matches("^[a-zA-Z0-9].*")) {
				if (transportId.length() <= 20) {
					int transportPos = searchTransport(transportId);
					if (transportPos == -1) {
						if (name != null && !name.isBlank()) {
							if (pricePerTon != null && !pricePerTon.isBlank()) {
								if (pricePerTon.matches("\\d+(\\.\\d+)?")) {
									double pricePerTonDouble = Double.parseDouble(pricePerTon);
									if (pricePerTonDouble > 0) {
										newTransport.setTransportId(transportId);
										newTransport.setName(name);
										newTransport.setPricePerTon(pricePerTonDouble);
										transports.add(newTransport);

//										output = "Transport added successfully. ID: " + transportId + ", Name: " + name
//												+ ", PricePerTon: " + pricePerTonDouble;
									} else {
										output = "Price per ton must be greater than 0.";
									}
								} else {
									output = "The price per ton must be a valid number.";
								}
							} else {
								output = "The price per ton cannot be empty.";
							}
						} else {
							output = "The name cannot be empty.";
						}
					} else {
						output = "Transport already exists with ID: " + transportId;
					}
				} else {
					output = "The ID cannot exceed more than 20 characters.";
				}
			} else {
				output = "The ID cannot begin with special characters.";
			}
		} else {
			output = "The ID cannot be empty.";
		}

		return output;
	}
	
	@Override
	public String toString() {
	    return "Transports [transports=" + transports + "]";
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
				return transport; // Return the transport if found
			}
		}
		return null; // Return null if transport not found
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

	
	/** Delete Transport From List

	public boolean deleteTransportById(String transportId, ArrayList<Country> countries,
			LogisticsSupplyChains logisticsSupplyChains) {

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
		ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate()
				.getForEntity(restAPIHelper.getRootAPIURL() + "routeLines/transport/" + transportId, RouteLine[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			RouteLine[] routeLineArr = response.getBody();
			if (routeLineArr != null) {
				if (routeLineArr.length == 0) {
					// Proceed to delete the transport from the list
					transports.remove(transportIndex);
					System.out.println("Transport successfully deleted.");
					return true;
				}
			} else {
				System.out.println("Cannot delete transport. It is linked to RouteLine.");
			}
		}

		return false;

	}*/
	
	// Delete Transport by its id 
	
	public String deleteTransport(String transportId, ArrayList<Country> countries) {
	    String output = "";

	    if (transportId != null && !transportId.isBlank()) {
	        int transportIndex = searchTransport(transportId);

	        // Check if the transport exists in the list
	        if (transportIndex != -1) {

	            // Check if the transport is linked to any LogisticsSite
	            for (Country country : countries) {
	                for (LogisticsSite site : country.getSites()) {
	                    for (Transport transport : site.getSuppliedTransports()) {
	                        if (transport.getTransportId().equalsIgnoreCase(transportId)) {
	                            return "Cannot delete transport. It is linked to a LogisticsSite.";
	                        }
	                    }
	                }
	            }

	            // Check if the transport is linked to any RouteLine
	            RestAPIHelper restAPIHelper = new RestAPIHelper();
	            ResponseEntity<RouteLine[]> response = restAPIHelper.getRestTemplate()
	                    .getForEntity(restAPIHelper.getRootAPIURL() + "routeLines/transport/" + transportId, RouteLine[].class);

	            if (response.getStatusCode().is2xxSuccessful()) {
	                RouteLine[] routeLineArr = response.getBody();
	                if (routeLineArr != null && routeLineArr.length > 0) {
	                    output = "Cannot delete transport. It is linked to a RouteLine.";
	                } else {
	                    transports.remove(transportIndex);
	                    System.out.println("Transport successfully deleted.");
	                    output = "Transport successfully deleted.";
	                }
	            } else {
	                output = "Failed to fetch RouteLine data. Transport cannot be deleted.";
	            }
	        } else {
	            output = "Transport with the specified ID not found.";
	        }
	    } else {
	        output = "The ID cannot be empty.";
	    }

	    return output;
	}


	 // Update Transport
	public String updateTransport(String transportId, String name, String pricePerTon, Transport transport) {
	    String output = "";

	    if (transportId != null && !transportId.isBlank()) {  // Check if transportId is not null or blank
	        int transportPos = searchTransport(transportId);  // Find transport position in the list

	        if (transportPos != -1) {  // If transport is found
	            Transport editedTransport = transports.get(transportPos);  // Get the transport object to be edited

	            // Validate and set the name
	            if (name != null && !name.isBlank()) {
	                editedTransport.setName(name);
	                transport.setName(name);
	            } else {
	                output = "The name cannot be empty.";
	            }

	            // Validate and set the pricePerTon
	              if (output.isEmpty()) {  // Only proceed to price validation if name was valid
	                 if (pricePerTon != null && !pricePerTon.isBlank()) {
	                    if (pricePerTon.matches("[-+]?\\d*\\.?\\d+")) {  // Check if pricePerTon is a valid number (including decimal points)
	                        double price = Double.parseDouble(pricePerTon);  // Parse pricePerTon as a double
	                        if (price > 0) {
	                            editedTransport.setPricePerTon(price);
	                            transport.setPricePerTon(price);
	                        } else {
	                            output = "Price must be greater than 0.";
	                        }
	                    } else {
	                        output = "Price must be a valid number.";
	                    }
	                } else {
	                    output = "Price per ton cannot be empty.";
	                }
	            }
	        } else {
	            output = "Transport with ID (" + transportId + ") doesn't exist.";  // If transportId doesn't exist in the list
	        }
	    } else {
	        output = "The ID cannot be empty.";  // If transportId is null or blank
	    }

	    return output;  // Return any error messages if validation fails or an empty string if success
	}

}