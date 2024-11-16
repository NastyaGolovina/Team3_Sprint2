package UPT_PL.Team_3_client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import UPT_PL.Team_3.model.Calculation;
import UPT_PL.Team_3.model.Countries;
import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.DatabaseHelper;
import UPT_PL.Team_3.model.LogisticsProcessor;
import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3.model.LogisticsSupplyChains;
import UPT_PL.Team_3.model.Product;
import UPT_PL.Team_3.model.ProductRequestProcessor;
import UPT_PL.Team_3.model.Products;
import UPT_PL.Team_3.model.ProjectHelper;
import UPT_PL.Team_3.model.RouteLine;
import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3.model.Transports;

public class Manager {
	//	Instance variable
	private Products products;
	private Countries countries;
	private Transports transports;
	private LogisticsSupplyChains logisticsSupplyChains;
	private ProductRequestProcessor productRequestProcessor;
	private LogisticsProcessor logisticsProcessor;
	// Class variable
	private RestTemplate restTemplate = new RestTemplate();
	private String rootAPIURL = "http://localhost:8080/api/";
	
	/**
	 * Constructor
	 */
	public Manager() {
		this.products = new Products();
		this.countries = new Countries();
		this.transports = new Transports();
		this.logisticsSupplyChains = new LogisticsSupplyChains();
		this.productRequestProcessor = new ProductRequestProcessor();
		this.logisticsProcessor = new LogisticsProcessor();
	}

	/**
	 * @return the products
	 */
	public Products getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Products products) {
		this.products = products;
	}

	/**
	 * @return the countries
	 */
	public Countries getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	/**
	 * @return the transports
	 */
	public Transports getTransports() {
		return transports;
	}

	/**
	 * @param transports the transports to set
	 */
	public void setTransports(Transports transports) {
		this.transports = transports;
	}

	/**
	 * @return the logisticsSupplyChains
	 */
	public LogisticsSupplyChains getLogisticsSupplyChains() {
		return logisticsSupplyChains;
	}

	/**
	 * @param logisticsSupplyChains the logisticsSupplyChains to set
	 */
	public void setLogisticsSupplyChains(LogisticsSupplyChains logisticsSupplyChains) {
		this.logisticsSupplyChains = logisticsSupplyChains;
	}

	/**
	 * @return the productRequestProcessor
	 */
	public ProductRequestProcessor getProductRequestProcessor() {
		return productRequestProcessor;
	}

	/**
	 * @param productRequestProcessor the productRequestProcessor to set
	 */
	public void setProductRequestProcessor(ProductRequestProcessor productRequestProcessor) {
		this.productRequestProcessor = productRequestProcessor;
	}

	/**
	 * @return the logisticsProcessor
	 */
	public LogisticsProcessor getLogisticsProcessor() {
		return logisticsProcessor;
	}

	/**
	 * @param logisticsProcessor the logisticsProcessor to set
	 */
	public void setLogisticsProcessor(LogisticsProcessor logisticsProcessor) {
		this.logisticsProcessor = logisticsProcessor;
	}
	
	/**
	 * read all date from DB
	 */
	public void readFromDB() {
		readAllProducts();
		readAllCountries();
		readAllTransports();
//		countries.readAllLogisticsSitesWithJplq();
//		countries.readAllProductsByCountrysWithJplq();
		readAllSupplyChains();
//		readAllСalculationWithJplq();
	}
	/*
	 * readAllProducts
	 */
	private void readAllProducts() {
		ResponseEntity<Product[]> response = restTemplate.getForEntity(rootAPIURL + "products", Product[].class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Product[] productsArr = response.getBody();
			if (productsArr != null) {		
				List<Product> productList =  Arrays.asList(productsArr);
				products.setProductList(new ArrayList<Product>(productList));
			} 
		} else {
			System.out.println("Nothing found");
		}
	}
	
	/**
	 * readAllCountries
	 */
	private void readAllCountries() {
		ResponseEntity<Country[]> response = restTemplate.getForEntity(rootAPIURL + "countries", Country[].class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Country[] countryArr = response.getBody();
			if (countryArr != null) {	
				countries.setCountries(new ArrayList<Country>(Arrays.asList(countryArr)));
			} 
		} else {
			System.out.println("Nothing found");
		}
	}
	/**
	 * readAllTransports
	 */
	private void readAllTransports() {
		ResponseEntity<Transport[]> response = restTemplate.getForEntity(rootAPIURL + "transports", Transport[].class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Transport[] transportArr = response.getBody();
			if (transportArr != null) {		
				transports.setTransports(new ArrayList<Transport>(Arrays.asList(transportArr)));
			} 
		} else {
			System.out.println("Nothing found");
		}
	}
	/**
	 * readAllSupplyChains
	 */
	private void readAllSupplyChains() {
		ResponseEntity<LogisticsSupplyChain[]> response = restTemplate.getForEntity(rootAPIURL + "supply-chains", LogisticsSupplyChain[].class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			LogisticsSupplyChain[] logisticsSupplyChainArr = response.getBody();
			if (logisticsSupplyChainArr != null) {	
				logisticsSupplyChains.setSupplyChains(new ArrayList<LogisticsSupplyChain>(Arrays.asList(logisticsSupplyChainArr)));
			} 
		} else {
			System.out.println("Nothing found");
		}
	}
	
	/**
	 * addProduct
	 */
	public void addProduct() {
		Product newProduct = products.addProduct();
		if(newProduct != null) {
			ResponseEntity<Product> response = restTemplate.postForEntity(rootAPIURL + "products", newProduct, Product.class);
			
			if (response.getStatusCode().is2xxSuccessful()) {
				Product body = response.getBody();
				if (body != null) {		
						System.out.println("Successful save in BD");
				} else {
					System.out.println("No body");
				}
			} else {
				System.out.println("Nothing found");
			}
		}
	}
	
	/**
	 * addCountry
	 */
	public void addCountry() {
		Country newCountry = countries.addCountry();

		if (newCountry != null) {
			ResponseEntity<Country> response = restTemplate.postForEntity(rootAPIURL + "countries", newCountry,
					Country.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				Country body = response.getBody();
				if (body != null) {
					System.out.println("Successful save in BD");
				} else {
					System.out.println("No body");
				}
			} else {
				System.out.println("Nothing found");
			}
		}
	}
	/*
	 * addTransport
	 */
	public void addTransport() {
		Transport newTransport =transports.addTransport();
		
		if (newTransport != null) {
			ResponseEntity<Transport> response = restTemplate.postForEntity(rootAPIURL + "transports", newTransport,
					Transport.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				Transport body = response.getBody();
				if (body != null) {
					System.out.println("Successful save in BD");
				} else {
					System.out.println("No body");
				}
			} else {
				System.out.println("Nothing found");
			}
		}
	}
	/**
	 * addLogisticsSiteToCountry old
	 */
	public void addLogisticsSitesToCountry() {
		if(!countries.getCountries().isEmpty()) {
			int CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			
			while(CountryPos == -1) {
				System.out.println("Country doesn't exist");
				CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			}
			
			countries.getCountries().get(CountryPos).addLogisticsSite(transports, countries.getCountries());
		} else {
			System.out.println("The countries list is empty.");
		}
	}
	
	/**
	 * old
	 */
	public void addProductsToCountry() {
		if(!countries.getCountries().isEmpty()) {
			int CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			
			while(CountryPos == -1) {
				System.out.println("Country doesn't exist");
				CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			}
			
			countries.getCountries().get(CountryPos).addProductByCountry(products);
		} else {
			System.out.println("The countries list is empty.");
		}
	}
	
	
	/**
	 * addLogisticsSupplyChain
	 */	
	public void addLogisticsSupplyChain() {
		LogisticsSupplyChain newLogisticsSupplyChain = logisticsSupplyChains.addNewSupplyChain(countries);

		if (newLogisticsSupplyChain != null) {
			ResponseEntity<LogisticsSupplyChain> response = restTemplate.postForEntity(rootAPIURL + "supply-chains", newLogisticsSupplyChain,
					LogisticsSupplyChain.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				LogisticsSupplyChain body = response.getBody();
				if (body != null) {
					System.out.println("Successful save in BD");
				} else {
					System.out.println("No body");
				}
			} else {
				System.out.println("Nothing found");
			}
		}
	}
	/**
	 * calculateSuppyRequest
	 */
	private void calculateSupplyRequest() {
		productRequestProcessor.calcSupplyRequest(products, countries);
	}
	/**
	 * calculateLogisticsRoute
	 */
	public void calculateLogisticsRoute() {
		int isContinue = 1;
		if(!logisticsProcessor.isCurrentСalculationEmpty()) {
			isContinue = ProjectHelper.inputInt("The previous calculation will be deleted before adding to"
					+ " the database do you want to continue (yes-(1), no-(0)) :");
			while(isContinue != 0 && isContinue != 1) {
				isContinue = ProjectHelper.inputInt("The previous calculation will be deleted before adding to"
						+ " the database do you want to continue (yes-(1), no-(0)) :");
			}
		}
		if(isContinue == 1) {
			productRequestProcessor = new ProductRequestProcessor();
			logisticsProcessor = new LogisticsProcessor();
			logisticsProcessor.setCurrentСalculation(new Calculation(ProjectHelper.inputStr("Input current calculation description :"))); 
			calculateSupplyRequest();
			
			ProjectHelper.printTypes();
			String name;
			int calcType = ProjectHelper.inputInt("Input calculated type : ");
			while (calcType < 1 || calcType > 3) {
				System.out.println("Wrong type");
				calcType = ProjectHelper.inputInt("Input calculated type : ");
			}
			
			switch (calcType) {
			case 1: {
				name = "";
				logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains, LogisticsProcessor.CalcType.AllCountry, name);
				break;
			}
			case 2: {
				name = ProjectHelper.inputStr("Input country name : ");
				
				logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains, LogisticsProcessor.CalcType.Country, name);
				break;
				}
			case 3: {
				name = ProjectHelper.inputStr("Input country product : ");
				logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains, LogisticsProcessor.CalcType.Product, name);
				break;
				}
			}
			
			printRouteLines();
		}
	}	
	
	/**
	 * printRouteLines
	 */
	public void printRouteLines() {
		if(!logisticsProcessor.getLogisticsRoutes().isEmpty()) {
			logisticsProcessor.printLogisticsProcessor();
		} else {
			System.out.println("Logistics processor list is empty.");
		}
		
	}
	
//	/**
//	 * writeLogisticsProcessorInDB
//	 */
//	public void writeLogisticsProcessorInDB() {
//		if(!logisticsProcessor.isCurrentСalculationEmpty()) {
//			logisticsProcessor.writeCurrentCalculationInDB();
//			productRequestProcessor = new ProductRequestProcessor();
//			logisticsProcessor = new LogisticsProcessor();
//		} else {
//			System.out.println("Logistics processor empty.");
//		}
//		
//	}
//	/**
//	 * readAllСalculationWithJplq and read road lines
//	 */
//	protected void readAllСalculationWithJplq() {
//		DatabaseHelper DatabaseHelper = new DatabaseHelper();
//		DatabaseHelper.setup();
//		Session session = DatabaseHelper.getSessionFactory().openSession();
//
//		List<Calculation> calculations = session.createQuery("SELECT C FROM Calculation C", Calculation.class)
//				.getResultList();
//
//		int i = 1;
//		for (Calculation c : calculations) {
//			System.out.println("(" + i + ")" + c);
//			i++;
//		}
//		System.out.println("Select the account you want to download or 0 if you don't want to download");
//		int calculationNum = ProjectHelper.inputInt("Input:");
//		while (calculationNum < 0 || calculationNum > calculations.size()) {
//			calculationNum = ProjectHelper.inputInt("Input:");
//		}
//		if (calculationNum != 0) {
//			long calculationId = calculations.get(calculationNum - 1).getCalculationId();
//
//			Query<RouteLine> query = session
//					.createQuery("FROM RouteLine rl WHERE rl.currentCalculation.id = :calculationId", RouteLine.class);
//			query.setParameter("calculationId", calculationId);
//
//			List<RouteLine> routeLines = query.getResultList();
//			this.logisticsProcessor = new LogisticsProcessor();
//			logisticsProcessor.setLogisticsRoutes((ArrayList<RouteLine>) routeLines);
//			logisticsProcessor.setCurrentСalculation(calculations.get(calculationNum - 1));
//		}
//		session.close();
//		DatabaseHelper.exit();
//	}
//	/**
//	 * 
//	 */
//	protected void deleteCalculation() {
//		DatabaseHelper DatabaseHelper = new DatabaseHelper();
//		DatabaseHelper.setup();
//		Session session = DatabaseHelper.getSessionFactory().openSession();
//		session.beginTransaction();
//
//		List<Calculation> calculations = session.createQuery("SELECT C FROM Calculation C", Calculation.class)
//				.getResultList();
//
//		int i = 1;
//		for (Calculation c : calculations) {
//			System.out.println("(" + i + ")" + c);
//			i++;
//		}
//		System.out.println("Select the account you want to download or 0 if you don't want to download");
//		int calculationNum = ProjectHelper.inputInt("Input:");
//		while (calculationNum < 0 || calculationNum > calculations.size()) {
//			calculationNum = ProjectHelper.inputInt("Input:");
//		}
//		if (calculationNum != 0) {
//			long calculationId = calculations.get(calculationNum - 1).getCalculationId();
//
////			Query<RouteLine> deleteItemsQuery = session
////					.createQuery("DELETE FROM RouteLine rl WHERE rl.currentСalculation.id = :calculationId", RouteLine.class);
////			deleteItemsQuery.setParameter("calculationId", calculationId);
////		    deleteItemsQuery.executeUpdate();
////		    
////			Query<RouteLine> deletedRouteLines = session.createQuery("DELETE FROM RouteLine rl WHERE rl.currentСalculation.id = :calculationId",RouteLine.class);
////			
////			deletedRouteLines.setParameter("calculationId", calculationId)
////            				 .executeUpdate();
////		    
//			int deletedRouteLines = session.createQuery("DELETE FROM RouteLine rl WHERE rl.currentCalculation.id = :calculationId")
//									.setParameter("calculationId", calculationId)
//									.executeUpdate();
//			
////		    Calculation calculation = new Calculation();
////		    calculation.setCalculationId(calculationId);
////		    session.beginTransaction();
////		    session.remove(calculation);
////			session.getTransaction().commit();
////		   
//		    Calculation calculation = session.get(Calculation.class, calculationId);
//            if (calculation != null) {
//                session.remove(calculation);
//                System.out.println("Deleted Calculation object with ID:" + calculationId);
//            }
//		    
//            
//            if((!logisticsProcessor.isCurrentСalculationEmpty()) && (logisticsProcessor.getCurrentСalculation().getCalculationId() == calculationId)) {
//            	productRequestProcessor = new ProductRequestProcessor();
//    			logisticsProcessor = new LogisticsProcessor();
//            }
//			
//		}
//		session.getTransaction().commit();
//		session.close();
//		DatabaseHelper.exit();
//	}
	/**
	 * 
	 */
	public void deleteLogisticsSupplyChain() {
		logisticsSupplyChains.deleteSupplyChainById(ProjectHelper.inputStr("Inpit Logistics Supply Chain ID :"));
	}
	
	/**
	 * printProducts
	 */
	public void printProducts() {
		products.displayProducts();
	}
	/**
	 * printLogisticsSupplyChain
	 */
	public void printLogisticsSupplyChain() {
		logisticsSupplyChains.displayAll();
	}
	/**
	 * printCountries
	 */
	public void printCountries() {
		countries.displayCountries();
	}
	/**
	 * printTransports
	 */
	public void printTransports() {
		transports.displayTransports();
	}
}
