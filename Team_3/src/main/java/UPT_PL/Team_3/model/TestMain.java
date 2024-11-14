package UPT_PL.Team_3.model;

import java.util.ArrayList;

public class TestMain {

	public static void main(String[] args) {
		
		Products thisProducts = new Products();
		
		ArrayList<Product> thisProductsList = new ArrayList<Product>();
		Product productGrain = new Product( "GRN", "Grain" ,365,1);
		thisProductsList.add(productGrain);
		Product productMeat = new Product( "MT", "Meat" ,60, 0.073);
		thisProductsList.add(productMeat);
		thisProducts.setProductList(thisProductsList);
		
		Transports thisTransports = new Transports();
		ArrayList<Transport> thisTransportList = new ArrayList<Transport>();
		Transport transportAIR = new Transport("AIR", "Air transport", 1020.50);
		thisTransportList.add(transportAIR);
		Transport transportSEA = new Transport("SEA", "Sea transport", 45.55);
		thisTransportList.add(transportSEA);
		thisTransports.setTransports(thisTransportList);
		
		Countries thisCountries = new Countries();
		
		ArrayList<Country> thisCountryList = new ArrayList<Country>();
		//Ukraine
		Country countryUKR = new Country("UKR", "Ukraine", 38000000);
		//country sites
		ArrayList<Transport> siteKyivTransport = new ArrayList<Transport>();
		siteKyivTransport.add(transportAIR);
		LogisticsSite siteKyiv = new LogisticsSite("Kyiv", "Kyiv", countryUKR, siteKyivTransport);
		ArrayList<Transport> siteOdessaTransport = new ArrayList<Transport>();
		siteOdessaTransport.add(transportSEA);
		LogisticsSite siteOdessa = new LogisticsSite("Odesa", "Odesa", countryUKR, siteOdessaTransport);
		ArrayList<LogisticsSite> sitesUKR = new ArrayList<LogisticsSite>();
		sitesUKR.add(siteKyiv);
		sitesUKR.add(siteOdessa);
		countryUKR.setSites(sitesUKR);
		//country products
		ArrayList<ProductsByCountry> productsByCountryUKRList = new ArrayList<ProductsByCountry>();
		ProductsByCountry productByCountryUKR = new ProductsByCountry(productGrain, 500000000.00, 218.00,countryUKR);
		productsByCountryUKRList.add(productByCountryUKR);
		countryUKR.setProducts(productsByCountryUKRList);
		thisCountryList.add(countryUKR);
		
		//Portugal
		Country countryPT = new Country("PT", "Portugal", 9000000);
		//country sites
		ArrayList<Transport> sitePortoTransport = new ArrayList<Transport>();
		sitePortoTransport.add(transportAIR);
		sitePortoTransport.add(transportSEA);
		LogisticsSite sitePorto = new LogisticsSite("Porto", "Porto", countryPT, sitePortoTransport);
		ArrayList<Transport> siteLisboaTransport = new ArrayList<Transport>();
		siteLisboaTransport.add(transportSEA);
		siteLisboaTransport.add(transportAIR);
		LogisticsSite siteLisboa = new LogisticsSite("Lisboa", "Lisboa", countryPT, siteLisboaTransport);
		ArrayList<LogisticsSite> sitesPT = new ArrayList<LogisticsSite>();
		sitesPT.add(sitePorto);
		sitesPT.add(siteLisboa);
		countryPT.setSites(sitesPT);
		//country products
		ArrayList<ProductsByCountry> productsByCountryPTList = new ArrayList<ProductsByCountry>();
		ProductsByCountry productByCountryPT = new ProductsByCountry(productGrain, 10000000, 5.00,countryPT);
		productsByCountryPTList.add(productByCountryPT);
		productByCountryPT = new ProductsByCountry(productMeat, 2000000, 5000,countryPT);
		productsByCountryPTList.add(productByCountryPT);
		countryPT.setProducts(productsByCountryPTList);
		thisCountryList.add(countryPT);
		
		
		// Algeria
		Country countryDZ = new Country("DZ", "Algeria", 44000000);
		// country sites
		ArrayList<Transport> siteAlgiersTransport = new ArrayList<Transport>();
		siteAlgiersTransport.add(transportAIR);
		LogisticsSite siteAlgiers = new LogisticsSite("Algiers", "Algiers", countryDZ, siteAlgiersTransport);
		ArrayList<Transport> siteOranTransport = new ArrayList<Transport>();
		siteOranTransport.add(transportSEA);
		LogisticsSite siteOran = new LogisticsSite("Oran", "Oran", countryDZ, siteOranTransport);
		ArrayList<LogisticsSite> sitesDZ = new ArrayList<LogisticsSite>();
		sitesDZ.add(siteAlgiers);
		sitesDZ.add(siteOran);
		countryDZ.setSites(sitesDZ);
		// country products
		ArrayList<ProductsByCountry> productsByCountryDZList = new ArrayList<ProductsByCountry>();
		ProductsByCountry productByCountryDZ = new ProductsByCountry(productGrain, 10000000, 200,countryDZ);
		productsByCountryDZList.add(productByCountryDZ);
		productByCountryDZ = new ProductsByCountry(productMeat, 500000, 3700,countryDZ);
		productsByCountryDZList.add(productByCountryDZ);
		countryDZ.setProducts(productsByCountryDZList);
		thisCountryList.add(countryDZ);

		// Vietnam
		Country countryVN = new Country("VN", "Vietnam", 97000000);
		// country sites
		ArrayList<Transport> siteHanoiTransport = new ArrayList<Transport>();
		siteHanoiTransport.add(transportAIR);
		LogisticsSite siteHanoi = new LogisticsSite("Hanoi", "Hanoi", countryVN, siteHanoiTransport);
		ArrayList<Transport> siteHoChiMinhCityTransport = new ArrayList<Transport>();
		siteHoChiMinhCityTransport.add(transportSEA);
		LogisticsSite siteHoChiMinhCity = new LogisticsSite("HCMC", "Ho Chi Minh City", countryVN, siteHoChiMinhCityTransport);
		ArrayList<LogisticsSite> sitesVN = new ArrayList<LogisticsSite>();
		sitesVN.add(siteHanoi);
		sitesVN.add(siteHoChiMinhCity);
		countryVN.setSites(sitesVN);
		// country products
		ArrayList<ProductsByCountry> productsByCountryVNList = new ArrayList<ProductsByCountry>();
		ProductsByCountry productByCountryVN = new ProductsByCountry(productGrain, 120000000, 280.00,countryVN);
		productsByCountryVNList.add(productByCountryVN);
		productByCountryVN = new ProductsByCountry(productMeat, 10000000, 4500,countryVN);
		productsByCountryVNList.add(productByCountryVN);
		countryVN.setProducts(productsByCountryVNList);
		thisCountryList.add(countryVN);
		thisCountries.setCountries(thisCountryList);

		//LogisticsSupplyChain
		LogisticsSupplyChains thisChains = new LogisticsSupplyChains();

		LogisticsSupplyChain KyivAlgiersAIR = new LogisticsSupplyChain("KyivAlgiersAIR", siteKyiv, siteAlgiers, transportAIR, 2530, 1);
		thisChains.addNewSupplyChain(KyivAlgiersAIR);
		LogisticsSupplyChain OdesaOranSEA = new LogisticsSupplyChain("OdesaOranSEA", siteOdessa, siteOran, transportSEA, 25, 60);
		thisChains.addNewSupplyChain(OdesaOranSEA);
		LogisticsSupplyChain PortoAlgiersAIR = new LogisticsSupplyChain("PortoAlgiersAIR", sitePorto, siteAlgiers, transportAIR, 1875, 1);
		thisChains.addNewSupplyChain(PortoAlgiersAIR);
		LogisticsSupplyChain PortoOranSEA = new LogisticsSupplyChain("PortoOranSEA", sitePorto, siteOran, transportSEA, 18, 30);
		thisChains.addNewSupplyChain(PortoOranSEA);
		LogisticsSupplyChain LisboaAlgiersAIR = new LogisticsSupplyChain("LisboaAlgiersAIR", siteLisboa, siteAlgiers, transportAIR, 1875, 1);
		thisChains.addNewSupplyChain(LisboaAlgiersAIR);
		LogisticsSupplyChain LisboaOranSEA = new LogisticsSupplyChain("LisboaOranSEA", siteLisboa, siteOran, transportSEA, 18, 30);
		thisChains.addNewSupplyChain(LisboaOranSEA);
		LogisticsSupplyChain HanoiAlgiersAIR = new LogisticsSupplyChain("HanoiAlgiersAIR", siteHanoi, siteAlgiers, transportAIR, 3752, 2);
		thisChains.addNewSupplyChain(HanoiAlgiersAIR);
		LogisticsSupplyChain HCMCOranSEA = new LogisticsSupplyChain("HCMCOranSEA", siteHoChiMinhCity, siteOran, transportSEA, 34, 90);
		thisChains.addNewSupplyChain(HCMCOranSEA);

		
		
		ProductRequestProcessor productRequestProcessor = new ProductRequestProcessor();
		productRequestProcessor.calcSupplyRequest(thisProducts, thisCountries);
		
		LogisticsProcessor logisticsProcessor = new LogisticsProcessor();
		logisticsProcessor.setCurrentСalculation(new Calculation(""));
		logisticsProcessor.calcLogisticsRoute(productRequestProcessor, thisChains, LogisticsProcessor.CalcType.AllCountry, "");
		logisticsProcessor.printLogisticsProcessorCSV();
//		thisProducts.displayProducts();
	}

}
