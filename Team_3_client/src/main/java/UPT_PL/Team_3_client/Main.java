package UPT_PL.Team_3_client;

import UPT_PL.Team_3.model.ProjectHelper;

public class Main {

	public static void main(String[] args) {
		/** Interface **/
		ProjectHelper.showMenuInfo();
		
		/** Create Manager **/
		Manager manager = new Manager();
		manager.readFromDB();		
		/** Input command **/
		int command = ProjectHelper.inputInt("Input command : ");
		
		/** Functional **/
        while ( command != 0) {
            switch(command) {
            case 0:
                break;
            case 1:
            	/**  Add product **/
            	manager.addProduct();
                break;
            case 2:
            	/** Add country **/
            	manager.addCountry();
                break;
            case 3:
            	/** Add transport **/
            	manager.addTransport();
                break;
            case 4:
            	/** Add logistics site to country **/  
            	manager.addLogisticsSitesToCountry();
                break;  
            case 5:
            	/** Add product to country **/
            	manager.addProductsToCountry();
                break;
            case 6:
            	/** Add logistics supply chain **/
            	manager.addLogisticsSupplyChain();
                break;
            case 7:
            	/** New calculation for logistics route **/
            	manager.calculateLogisticsRoute();
                break;
            case 8:
            	/**  Save logistics routes in DB **/
//            	manager.writeLogisticsProcessorInDB();
                break;
            case 9:
            	/** Print Products **/
            	manager.printRouteLines();;
                break;
            case 10:
            	/** Print Products **/
            	manager.printProducts();
                break;
            case 11:
            	/**Print Logistics Supply Chains **/
            	manager.printLogisticsSupplyChain();
                break;
            case 12:
            	/**Print Countries **/
            	manager.printCountries();
                break;
            case 13:
            	/**Print Transports **/
            	manager.printTransports();
                break;
            case 14:
            	/**Delete **/
            	ProjectHelper.showDeleteMenuInfo();
            	int deleteCommand = ProjectHelper.inputInt("Input delete option");
            	switch (deleteCommand) {
				case 1: {
					/** Delete calculation **/
					manager.deleteCalculation();
					break;
				}
				case 2: {
					/** Delete logistics supply chain **/
					manager.deleteLogisticsSupplyChain();
					break;
				}
				case 3: {
					/** Delete product **/
					manager.deleteProduct();
					break;
				}
				case 4: {
					/** Delete country **/
					manager.deleteCountry();
					break;
				}
				case 5: {
					/** Delete products by country **/
					
					break;
				}
				case 6: {
					/** Delete logistics site **/
					manager.deleteLogisticsSite();
					break;
				}
				case 7: {
					/** Delete transport **/
					manager.deleteTransport();
					break;
				}
				default:
					System.out.println("Invalid request");
				}
                break;
            default:
                System.out.println("Invalid request");
            } 
            ProjectHelper.showMenuInfo();
            command = ProjectHelper.inputInt("Input command : ");
        }

	}

}
