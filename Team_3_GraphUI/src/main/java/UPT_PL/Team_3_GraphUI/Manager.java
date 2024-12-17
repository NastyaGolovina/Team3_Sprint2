
package UPT_PL.Team_3_GraphUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import UPT_PL.Team_3.model.Calculation;
import UPT_PL.Team_3.model.Countries;
import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsProcessor;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3.model.LogisticsSupplyChains;
import UPT_PL.Team_3.model.Product;
import UPT_PL.Team_3.model.ProductRequestProcessor;
import UPT_PL.Team_3.model.Products;
import UPT_PL.Team_3.model.ProductsByCountry;
import UPT_PL.Team_3.model.ProjectHelper;
import UPT_PL.Team_3.model.RouteLine;
import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3.model.Transports;

public class Manager {
	// Instance variable
	private Products products;
	private Countries countries;
	private Transports transports;
	private LogisticsSupplyChains logisticsSupplyChains;
	private ProductRequestProcessor productRequestProcessor;
	private LogisticsProcessor logisticsProcessor;
	// Class variable
	private static RestTemplate restTemplate = new RestTemplate();
	private static String rootAPIURL = "http://localhost:8080/api/";
	private static HttpHeaders headers = new HttpHeaders();

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
		readAllLogisticsSites();
		readAllProductsByCountrys();
		readAllSupplyChains();
	}

	/*
	 * readAllProducts
	 */
	private void readAllProducts() {
		ResponseEntity<Product[]> response = restTemplate.getForEntity(rootAPIURL + "products", Product[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Product[] productsArr = response.getBody();
			if (productsArr != null) {
				List<Product> productList = Arrays.asList(productsArr);
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
	 * readAllProductsByCountrys
	 */
	private void readAllProductsByCountrys() {
		for (Country c : countries.getCountries()) {
			ResponseEntity<ProductsByCountry[]> response = restTemplate.getForEntity(
					rootAPIURL + "products-by-country/country/" + c.getCountryId(), ProductsByCountry[].class);

			if (response.getStatusCode().is2xxSuccessful()) {
				ProductsByCountry[] productsByCountryArr = response.getBody();
				if (productsByCountryArr != null) {
					c.setProducts(new ArrayList<ProductsByCountry>(Arrays.asList(productsByCountryArr)));
				}
			} else {
				System.out.println("Nothing found");
			}
		}
	}

	/**
	 * 
	 */
	private void readAllLogisticsSites() {
		for (Country c : countries.getCountries()) {
			ResponseEntity<LogisticsSite[]> response = restTemplate
					.getForEntity(rootAPIURL + "logistics-sites/country/" + c.getCountryId(), LogisticsSite[].class);

			if (response.getStatusCode().is2xxSuccessful()) {
				LogisticsSite[] logisticsSiteArr = response.getBody();
				if (logisticsSiteArr != null) {
					c.setSites(new ArrayList<LogisticsSite>(Arrays.asList(logisticsSiteArr)));
				}
			} else {
				System.out.println("Nothing found");
			}
		}
	}

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
		ResponseEntity<LogisticsSupplyChain[]> response = restTemplate.getForEntity(rootAPIURL + "supply-chains",
				LogisticsSupplyChain[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			LogisticsSupplyChain[] logisticsSupplyChainArr = response.getBody();
			if (logisticsSupplyChainArr != null) {
				logisticsSupplyChains
						.setSupplyChains(new ArrayList<LogisticsSupplyChain>(Arrays.asList(logisticsSupplyChainArr)));
			}
		} else {
			System.out.println("Nothing found");
		}
	}

	/**
	 * addProduct
	 */
	public void addProduct(Product newProduct) {
		if (newProduct != null) {
			ResponseEntity<Product> response = restTemplate.postForEntity(rootAPIURL + "products", newProduct,
					Product.class);

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
	public void addCountry(Country newCountry) {
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
	public void addTransport(Transport newTransport) {
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
	 * addLogisticsSiteToCountry
	 */
	public void addLogisticsSitesToCountry(LogisticsSite newLogisticsSite) {

		if (newLogisticsSite != null) {
			ResponseEntity<LogisticsSite> response = restTemplate.postForEntity(rootAPIURL + "logistics-sites",
					newLogisticsSite, LogisticsSite.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				LogisticsSite body = response.getBody();
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
	 * addProductsToCountry
	 */
	public void addProductsByCountry(ProductsByCountry newProductsByCountry) {
		if (productRequestProcessor != null) {
			ResponseEntity<ProductsByCountry> response = restTemplate.postForEntity(rootAPIURL + "products-by-country",
					newProductsByCountry, ProductsByCountry.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				ProductsByCountry body = response.getBody();
				if (body != null) {
					System.out.println(newProductsByCountry.getProductByCountryId() + " successful save in BD");
				} else {
					System.out.println("No body");
				}
			} else {
				System.out.println("Nothing found");
			}

		}

	}

	/**
	 * addLogisticsSupplyChain
	 */
	public void addLogisticsSupplyChain(LogisticsSupplyChain newLogisticsSupplyChain) {
		if (newLogisticsSupplyChain != null) {
			ResponseEntity<LogisticsSupplyChain> response = restTemplate.postForEntity(rootAPIURL + "supply-chains",
					newLogisticsSupplyChain, LogisticsSupplyChain.class);

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
	public void calculateLogisticsRoute(String description, String calcType, String name) {

		productRequestProcessor = new ProductRequestProcessor();
		logisticsProcessor = new LogisticsProcessor();
		logisticsProcessor.setCurrentСalculation(new Calculation(description));
		calculateSupplyRequest();

		switch (calcType) {
		case "All": {
			logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains,
					LogisticsProcessor.CalcType.AllCountry, name);
			break;
		}
		case "Country": {
			logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains,
					LogisticsProcessor.CalcType.Country, name);
			break;
		}
		case "Product": {
			logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains,
					LogisticsProcessor.CalcType.Product, name);
			break;
		}
		}
	}

	/**
	 * printRouteLines
	 */
	public void printRouteLines() {
		if (!logisticsProcessor.getLogisticsRoutes().isEmpty()) {
			logisticsProcessor.printLogisticsProcessor();
		} else {
			System.out.println("Logistics processor list is empty.");
		}

	}

	/**
	 * writeLogisticsProcessorInDB
	 */
	public void writeLogisticsProcessorInDB() {
		if (!logisticsProcessor.isCurrentСalculationEmpty()) {

			ResponseEntity<Calculation> response = restTemplate.postForEntity(rootAPIURL + "calculations",
					logisticsProcessor.getCurrentСalculation(), Calculation.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				Calculation body = response.getBody();
				if (body != null) {
					for (RouteLine r : logisticsProcessor.getLogisticsRoutes()) {
						ResponseEntity<RouteLine> routeLineResponse = restTemplate
								.postForEntity(rootAPIURL + "routeLines", r, RouteLine.class);

						if (routeLineResponse.getStatusCode().is2xxSuccessful()) {
							RouteLine rBody = routeLineResponse.getBody();
							if (rBody == null) {
								System.out.println("No body");
							}
						} else {
							System.out.println("Nothing found");
						}
					}
				} else {
					System.out.println("No body");
				}
			} else {
				System.out.println("Nothing found");
			}

			productRequestProcessor = new ProductRequestProcessor();
			logisticsProcessor = new LogisticsProcessor();
		} else {
			System.out.println("Logistics processor empty.");
		}

	}

	/**
	 * readAllСalculationWithJplq and read road lines
	 */
	public ArrayList<Calculation> readCalculations() {

		ResponseEntity<Calculation[]> response = restTemplate.getForEntity(rootAPIURL + "calculations",
				Calculation[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Calculation[] calculationsArr = response.getBody();
			if (calculationsArr != null) {
				ArrayList<Calculation> calculations = new ArrayList<>(Arrays.asList(calculationsArr));
				return calculations;
			}
		} else {
			System.out.println("Nothing found");
		}
		return null;
	}

	public void readRouteLines(Calculation calculation) {
		String calculationId = calculation.getCalculationId();

		ResponseEntity<RouteLine[]> routeLineresponse = restTemplate
				.getForEntity(rootAPIURL + "routeLines/calculation/" + calculationId, RouteLine[].class);

		if (routeLineresponse.getStatusCode().is2xxSuccessful()) {
			RouteLine[] rBody = routeLineresponse.getBody();
			if (rBody != null) {
				productRequestProcessor = new ProductRequestProcessor();
				this.logisticsProcessor = new LogisticsProcessor();
				logisticsProcessor.setLogisticsRoutes(new ArrayList<RouteLine>(Arrays.asList(rBody)));
				logisticsProcessor.setCurrentСalculation(calculation);
			}
		} else {
			System.out.println("Nothing found");
		}
	}

//	public List<RouteLine> readRoute

	/**
	 * deleteCalculation
	 */
	public void deleteCalculation(String calculationId) {
		restTemplate.delete(rootAPIURL + "routeLines/calculation/" + calculationId);
		restTemplate.delete(rootAPIURL + "calculations/" + calculationId);

		if ((!logisticsProcessor.isCurrentСalculationEmpty())
				&& (logisticsProcessor.getCurrentСalculation().getCalculationId().equalsIgnoreCase(calculationId))) {
			productRequestProcessor = new ProductRequestProcessor();
			logisticsProcessor = new LogisticsProcessor();
		}
	}

	public void deleteProduct(String productID) {
		restTemplate.delete(rootAPIURL + "products/" + productID);
	}

	/**
	 * deleteCountry
	 */
	public void deleteCountry(String countryID) {
		restTemplate.delete(rootAPIURL + "countries/" + countryID);
	}

	/**
	 * deleteTransport
	 */
	public void deleteTransport(String transporttID) {

		restTemplate.delete(rootAPIURL + "transports/" + transporttID);

	}

	/**
	 * deleteLogisticsSupplyChain
	 */
	public void deleteLogisticsSupplyChain(String chainID) {

		restTemplate.delete(rootAPIURL + "supply-chains/" + chainID);

	}

	/**
	 * deleteCountry
	 */
	public void deleteLogisticsSite(String siteID) {

		restTemplate.delete(rootAPIURL + "logistics-sites/" + siteID);

	}

	/**
	 * deleteCountry
	 */
	public void deleteProductsByCountry(String productsByCountryID) {

		restTemplate.delete(rootAPIURL + "products-by-country/" + productsByCountryID);

	}

	public void updateCountry(Country country) {
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Country> requestEntity = new HttpEntity<Country>(country, headers);
		ResponseEntity<Country> response = restTemplate.exchange(rootAPIURL + "countries", HttpMethod.PUT,
				requestEntity, Country.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("Updated");
		} else {
			System.out.println("Nothing found");
		}
	}

	public void updateProduct(Product product) {
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);
		ResponseEntity<Product> response = restTemplate.exchange(rootAPIURL + "products", HttpMethod.PUT, requestEntity,
				Product.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("Updated");
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void updateTransport(Transport transport) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Transport> requestEntity = new HttpEntity<Transport>(transport, headers);
		ResponseEntity<Transport> response = restTemplate.exchange(rootAPIURL + "transports", HttpMethod.PUT, 
				requestEntity, Transport.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("Updated");
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void updateLogisticsSite(LogisticsSite logisticsSite) {
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<LogisticsSite> requestEntity = new HttpEntity<>(logisticsSite, headers);
	    ResponseEntity<LogisticsSite> response = restTemplate.exchange(
	            rootAPIURL + "logistics-sites", HttpMethod.PUT, requestEntity, LogisticsSite.class);
	    
	    if (response.getStatusCode().is2xxSuccessful()) {
	        System.out.println("Logistics site updated successfully.");
	    } else {
	        System.out.println("Nothing found.");
	    }
	}


	public void updateLogisticsSupplyChain(LogisticsSupplyChain chain) {
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<LogisticsSupplyChain> requestEntity = new HttpEntity<LogisticsSupplyChain>(chain, headers);
		ResponseEntity<LogisticsSupplyChain> response = restTemplate.exchange(rootAPIURL + "supply-chains", HttpMethod.PUT, requestEntity,
				LogisticsSupplyChain.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("Updated");
		} else {
			System.out.println("Nothing found");
		}
	}

	public void updateProductsByCountry(ProductsByCountry productsByCountry) {

		headers.setContentType(MediaType.APPLICATION_JSON);



		HttpEntity<ProductsByCountry> requestEntity = new HttpEntity<ProductsByCountry>(productsByCountry, headers);

		ResponseEntity<ProductsByCountry> response = restTemplate.exchange(rootAPIURL + "products-by-country", HttpMethod.PUT, requestEntity,

				ProductsByCountry.class);


		if (response.getStatusCode().is2xxSuccessful()) {

			System.out.println("Updated");

		} else {

			System.out.println("Nothing found");

		}

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
