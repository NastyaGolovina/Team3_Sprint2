package UPT_PL.Team_3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The LogisticsSupplyChains class manages a collection of LogisticsSupplyChain objects.
 */

public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains; // List to store all supply chains
    
    /**
     * Constructor that initializes an empty list of supply chains.
     * It does not interact with the database and simply creates an empty list.
     */
    public LogisticsSupplyChains() {
        this.supplyChains = new ArrayList<>(); // Creating an empty list for supply chains
    }

    /**
     * Adds a new supply chain to the list. This method does not return anything.
     * 
     * @param logisticsSupplyChain The supply chain to be added
     */
    public void addNewSupplyChain(LogisticsSupplyChain logisticsSupplyChain) {
        supplyChains.add(logisticsSupplyChain);  // Adding the supply chain to the array
    }
    
    
    public int searchChain(String supplyChainsId) {
    	int i = 0;
		 while (i < supplyChains.size() && !supplyChains.get(i).getChainId().equalsIgnoreCase(supplyChainsId)){
			 i++;
		 }
		 if ( i != supplyChains.size()) {
			 return i;
		 }
		 return -1;
    }

    /**
     * Adds a new supply chain after validating input data.
     * Returns the created LogisticsSupplyChain object or null if something goes wrong.
     * 
     * @param countries The countries object containing all countries and logistics sites
     * @return LogisticsSupplyChain The created supply chain or null if creation failed
     */
//    public LogisticsSupplyChain addNewSupplyChain(Countries countries) {
//        // 1. Prompt for a new supply chain ID
//        String chainId = ProjectHelper.inputStr("Enter ID for the new Supply Chain:");
//     
//        // Check for unique ID
//        for (LogisticsSupplyChain chain : supplyChains) {
//            if (chain.getChainId().equalsIgnoreCase(chainId)) {
//                System.out.println("Error: A Supply Chain with this ID already exists.");
//                return null; // Return null if the ID already exists
//            }
//        }
//     
//        // 2. Prompt for and find the sender logistics site
//        String senderSiteId = ProjectHelper.inputStr("Enter the ID of the sender logistics site:");
//        LogisticsSite sender = findLogisticsSiteById(countries, senderSiteId);
//        if (sender == null) {
//            System.out.println("Error: Logistics site with this ID was not found.");
//            return null; // Return null if the sender site is not found
//        }
//     
//        // 3. Prompt for and find the receiver logistics site
//        String receiverSiteId = ProjectHelper.inputStr("Enter the ID of the receiver logistics site:");
//        if (receiverSiteId.equals(senderSiteId)) {
//            System.out.println("Error: Sender and receiver logistics sites must be different.");
//            return null; // Return null if the sender and receiver sites are the same
//        }
//     
//        LogisticsSite receiver = findLogisticsSiteById(countries, receiverSiteId);
//        if (receiver == null) {
//            System.out.println("Error: Logistics site with this ID was not found.");
//            return null; // Return null if the receiver site is not found
//        }
//     
//        // 4. Prompt for and validate transport
//        String transportId = ProjectHelper.inputStr("Enter the transport ID:");
//        Transport selectedTransport = findTransportInSites(sender, receiver, transportId);
//        if (selectedTransport == null) {
//            System.out.println("Error: This transport is not supported by either of the selected sites.");
//            return null; // Return null if the transport is not supported by both sites
//        }
//     
//        // 5. Prompt for cost per ton
//        double costPerTon = ProjectHelper.inputDouble("Enter the cost per ton (must be > 0):");
//     
//        // 6. Prompt for duration in days
//        double durationInDays = ProjectHelper.inputDouble("Enter the duration in days (must be > 0):");
//     
//        // 7. Create a new LogisticsSupplyChain object and add it to the supplyChains list
//        LogisticsSupplyChain newChain = new LogisticsSupplyChain(chainId, sender, receiver, selectedTransport, costPerTon, durationInDays);
//        supplyChains.add(newChain);
//        
//        System.out.println("New Supply Chain successfully created: " + newChain);
//        
//        return newChain; // Return the created supply chain object
//    }
    
    
	public String updateSupplyChain(String chainId, String senderSiteId, String receiverSiteId, String transportId,
			String costPerTon, String durationInDays, LogisticsSupplyChain logisticsSupplyChain,
			Countries countries) {
		String output = "";
		LogisticsSupplyChain editedChain = null;
		// Check for unique ID
		for (LogisticsSupplyChain chain : supplyChains) {
			if (chain.getChainId().equalsIgnoreCase(chainId)) {
				editedChain = chain;
				output = "";
				break;
			} else {
				output = "Error: A Supply Chain with this ID doesn't exists.";
			}
		}
		
		if(editedChain != null) {
			LogisticsSite sender = findLogisticsSiteById(countries, senderSiteId);
			if (sender != null) {
				if (!receiverSiteId.equals(senderSiteId)) {
					LogisticsSite receiver = findLogisticsSiteById(countries, receiverSiteId);
					if (receiver != null) {
						Transport selectedTransport = findTransportInSites(sender, receiver, transportId);
						if (selectedTransport != null) {
							if (costPerTon.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
								double costPerTonDouble = Double.parseDouble(costPerTon);
								if (costPerTonDouble > 0) {
									if (durationInDays.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
										double durationInDaysDouble = Double.parseDouble(durationInDays);
										if (durationInDaysDouble > 0) {
											editedChain.setSender(sender);
											editedChain.setReceiver(receiver);
											editedChain.setTransport(selectedTransport);
											editedChain.setCostPerTon(costPerTonDouble);
											editedChain.setDurationInDays(durationInDaysDouble);
						
											logisticsSupplyChain.setSender(sender);
											logisticsSupplyChain.setReceiver(receiver);
											logisticsSupplyChain.setTransport(selectedTransport);
											logisticsSupplyChain.setCostPerTon(costPerTonDouble);
											logisticsSupplyChain.setDurationInDays(durationInDaysDouble);
										} else {
											output = "Error: durationInDays must be more then 0.";
										}
									} else {
										output = "Error: durationInDays must be a double.";
									}

								} else {
									output = "Error: costPerTonDouble must be more then 0.";
								}
							} else {
								output = "Error: costPerTonDouble must be a double.";
							}

						} else {
							output = "Error: This transport is not supported by either of the selected sites.";
						}
					} else {
						output = "Error: Logistics site with this ID was not found.";
					}
				} else {
					output = "Error: Sender and receiver logistics sites must be different.";
				}
			} else {
				output = "Error: Logistics site with this ID was not found.";
			}
		}
		

		return output;
	}
	
	
	
	
	
	
	
	
	
	
	public String addNewSupplyChain(String chainId, String senderSiteId, String receiverSiteId, String transportId,
			String costPerTon, String durationInDays, LogisticsSupplyChain newLogisticsSupplyChain,
			Countries countries) {
		String output = "";

		// Check for unique ID
		for (LogisticsSupplyChain chain : supplyChains) {
			if (chain.getChainId().equalsIgnoreCase(chainId)) {
				return "Error: A Supply Chain with this ID already exists.";
			}
		}

		LogisticsSite sender = findLogisticsSiteById(countries, senderSiteId);
		if (sender != null) {
			if (!receiverSiteId.equals(senderSiteId)) {
				LogisticsSite receiver = findLogisticsSiteById(countries, receiverSiteId);
				if (receiver != null) {
					Transport selectedTransport = findTransportInSites(sender, receiver, transportId);
					if (selectedTransport != null) {
						if (costPerTon.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
							double costPerTonDouble = Double.parseDouble(costPerTon);
							if (costPerTonDouble > 0) {
								if (durationInDays.matches("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?")) {
									double durationInDaysDouble = Double.parseDouble(durationInDays);
									if (durationInDaysDouble > 0) {
										newLogisticsSupplyChain.setChainId(chainId);
										newLogisticsSupplyChain.setSender(sender);
										newLogisticsSupplyChain.setReceiver(receiver);
										newLogisticsSupplyChain.setTransport(selectedTransport);
										newLogisticsSupplyChain.setCostPerTon(costPerTonDouble);
										newLogisticsSupplyChain.setDurationInDays(durationInDaysDouble);
										supplyChains.add(newLogisticsSupplyChain);
									} else {
										output = "Error: durationInDays must be more then 0.";
									}
								} else {
									output = "Error: durationInDays must be a double.";
								}

							} else {
								output = "Error: costPerTonDouble must be more then 0.";
							}
						} else {
							output = "Error: costPerTonDouble must be a double.";
						}

					} else {
						output = "Error: This transport is not supported by either of the selected sites.";
					}
				} else {
					output = "Error: Logistics site with this ID was not found.";
				}
			} else {
				output = "Error: Sender and receiver logistics sites must be different.";
			}
		} else {
			output = "Error: Logistics site with this ID was not found.";
		}

		return output;
	}
	
	
	
	
	
	

    // Helper method to find a logistics site by its ID in the countries object
    private LogisticsSite findLogisticsSiteById(Countries countries, String siteId) {
        for (Country country : countries.getCountries()) {
            for (LogisticsSite site : country.getSites()) {
                if (site.getSiteId().equals(siteId)) {
                    return site; 
                }
            }
        }
        return null; // Return null if no site is found with the given ID
    }

    // Helper method to find a transport that is supported by both sender and receiver sites
    private Transport findTransportInSites(LogisticsSite sender, LogisticsSite receiver, String transportId) {
        for (Transport transport : sender.getSuppliedTransports()) {
            if (transport.getTransportId().equals(transportId)) {
                for (Transport transportReceiver : receiver.getSuppliedTransports()) {
                    if (transportReceiver.getTransportId().equals(transportId)) {
                        return transport; // Return transport if it is supported by both sites
                    }
                }
            }
        }
        return null; // Return null if no matching transport is found
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
                System.out.println(chain); // Print each supply chain in the list
            }
        }
    }

    /**
     * Deletes a supply chain by its ID from the list.
     *
     * @param chainId The ID of the supply chain to delete.
     */
//    public boolean deleteSupplyChainById(String chainId) {
//        // find the supply chain by ID
//        boolean isRemoved = supplyChains.removeIf(chain -> chain.getChainId().equals(chainId));
//        
//        if (isRemoved) {
//            System.out.println("Supply chain with ID " + chainId + " successfully deleted.");
//            return true; // Return true if the chain has been deleted
//        } else {
//            System.out.println("Supply chain with ID " + chainId + " not found.");
//            return false; // Return false if the chain not founded
//        }
//    }
    
    public String deleteSupplyChainById(String chainId) {
    	String output = "";
        boolean isRemoved = supplyChains.removeIf(chain -> chain.getChainId().equals(chainId));
        if (!isRemoved) {
        	output = "Supply chain with ID " + chainId + " not found."; 
        } 
        return output;
    }

    // Getter and setter methods for supplyChains
    public ArrayList<LogisticsSupplyChain> getSupplyChains() {
        return supplyChains; // Return the list of supply chains
    }

    public void setSupplyChains(ArrayList<LogisticsSupplyChain> supplyChains) {
        this.supplyChains = supplyChains; // Set the list of supply chains
    }
}
