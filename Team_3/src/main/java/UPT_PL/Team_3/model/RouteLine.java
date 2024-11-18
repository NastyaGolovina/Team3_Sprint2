
package UPT_PL.Team_3.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
//import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Route_Line")
public class RouteLine {
	//	Instance variable
	@Id                                                    
	@Column(name = "RouteLineID",length = 40, nullable = false)
	private String  routeLineID;
	@Column(name = "version",length = 40, nullable = false)
	private String version;
	@ManyToOne
	@JoinColumn(name = "calculationId")
	private Calculation currentCalculation;
	@ManyToOne
	@JoinColumn(name = "Country_Sender_Id")
	private Country countrySender;
	@ManyToOne
	@JoinColumn(name = "Logistics_Site_Sender_Id")
	private LogisticsSite logisticsSiteSender;
	@ManyToOne
	@JoinColumn(name = "Country_Receiver_Id")
	private Country countryReceiver;
	@ManyToOne
	@JoinColumn(name = "Logistics_Site_Receiver_Id")
	private LogisticsSite logisticsSiteReceiver;
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	private Product product;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Transport_Id")
	private Transport transport;
	@Column(name = "quantity", nullable = false)
	private double quantity;
	@Column(name = "requestedQuantity", nullable = false)
	private double requestedQuantity;
	@Column(name = "amountProduct", nullable = false)
	private double amountProduct;
	@Column(name = "amountTransport", nullable = false)
	private double amountTransport;
	@Column(name = "totalAmount", nullable = false)
	private double totalAmount;
	@Column(name = "durationInDays", nullable = false)
	private double durationInDays;
	@Column(name = "covered", nullable = false)
	private boolean covered;
	@Column(name = "isOptimalChain", nullable = false)
	private boolean isOptimalChain;
	
	
	
	
	/**
	 * Constructor
	 */
	public RouteLine() {
	}


	/**
	 * Constructor
	 * @param routeLineID
	 * @param version
	 * @param countrySender
	 * @param logisticsSiteSender
	 * @param countryReceiver
	 * @param logisticsSiteReceiver
	 * @param product
	 * @param transport
	 * @param quantity
	 * @param requestedQuantity
	 * @param amountProduct
	 * @param amountTransport
	 * @param totalAmount
	 * @param durationInDays
	 * @param covered
	 * @param isOptimalChain
	 */
	public RouteLine(String version, Country countrySender, LogisticsSite logisticsSiteSender,
			Country countryReceiver, LogisticsSite logisticsSiteReceiver, Product product, Transport transport,
			double quantity, double requestedQuantity, double amountProduct, double amountTransport, double totalAmount,
			double durationInDays, boolean covered, Calculation currentСalculation) {
		this.routeLineID = UUID.randomUUID().toString();
		this.version = version;
		this.countrySender = countrySender;
		this.logisticsSiteSender = logisticsSiteSender;
		this.countryReceiver = countryReceiver;
		this.logisticsSiteReceiver = logisticsSiteReceiver;
		this.product = product;
		this.transport = transport;
		this.quantity = quantity;
		this.requestedQuantity = requestedQuantity;
		this.amountProduct = amountProduct;
		this.amountTransport = amountTransport;
		this.totalAmount = totalAmount;
		this.durationInDays = durationInDays;
		this.covered = covered;
		this.isOptimalChain = false;
		this.currentCalculation = currentСalculation;
	}
	
		
	/**
	 * @return the routeLineID
	 */
	public String getRouteLineID() {
		return routeLineID;
	}


	/**
	 * @param routeLineID the routeLineID to set
	 */
	public void setRouteLineID(String routeLineID) {
		this.routeLineID = routeLineID;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}


	/**
	 * @return the countrySender
	 */
	public Country getCountrySender() {
		return countrySender;
	}


	/**
	 * @param countrySender the countrySender to set
	 */
	public void setCountrySender(Country countrySender) {
		this.countrySender = countrySender;
	}


	/**
	 * @return the logisticsSiteSender
	 */
	public LogisticsSite getLogisticsSiteSender() {
		return logisticsSiteSender;
	}


	/**
	 * @param logisticsSiteSender the logisticsSiteSender to set
	 */
	public void setLogisticsSiteSender(LogisticsSite logisticsSiteSender) {
		this.logisticsSiteSender = logisticsSiteSender;
	}


	/**
	 * @return the countryReceiver
	 */
	public Country getCountryReceiver() {
		return countryReceiver;
	}


	/**
	 * @param countryReceiver the countryReceiver to set
	 */
	public void setCountryReceiver(Country countryReceiver) {
		this.countryReceiver = countryReceiver;
	}


	/**
	 * @return the logisticsSiteReceiver
	 */
	public LogisticsSite getLogisticsSiteReceiver() {
		return logisticsSiteReceiver;
	}


	/**
	 * @param logisticsSiteReceiver the logisticsSiteReceiver to set
	 */
	public void setLogisticsSiteReceiver(LogisticsSite logisticsSiteReceiver) {
		this.logisticsSiteReceiver = logisticsSiteReceiver;
	}


	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}


	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}


	/**
	 * @return the transport
	 */
	public Transport getTransport() {
		return transport;
	}


	/**
	 * @param transport the transport to set
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}


	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	/**
	 * @return the requestedQuantity
	 */
	public double getRequestedQuantity() {
		return requestedQuantity;
	}


	/**
	 * @param requestedQuantity the requestedQuantity to set
	 */
	public void setRequestedQuantity(double requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}


	/**
	 * @return the amountProduct
	 */
	public double getAmountProduct() {
		return amountProduct;
	}


	/**
	 * @param amountProduct the amountProduct to set
	 */
	public void setAmountProduct(double amountProduct) {
		this.amountProduct = amountProduct;
	}


	/**
	 * @return the amountTransport
	 */
	public double getAmountTransport() {
		return amountTransport;
	}


	/**
	 * @param amountTransport the amountTransport to set
	 */
	public void setAmountTransport(double amountTransport) {
		this.amountTransport = amountTransport;
	}


	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}


	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	/**
	 * @return the durationInDays
	 */
	public double getDurationInDays() {
		return durationInDays;
	}


	/**
	 * @param durationInDays the durationInDays to set
	 */
	public void setDurationInDays(double durationInDays) {
		this.durationInDays = durationInDays;
	}


	/**
	 * @return the covered
	 */
	public boolean isCovered() {
		return covered;
	}


	/**
	 * @param covered the covered to set
	 */
	public void setCovered(boolean covered) {
		this.covered = covered;
	}


	/**
	 * @return the isOptimalChain
	 */
	public boolean isOptimalChain() {
		return isOptimalChain;
	}


	/**
	 * @param isOptimalChain the isOptimalChain to set
	 */
	public void setOptimalChain(boolean isOptimalChain) {
		this.isOptimalChain = isOptimalChain;
	}

	
	/**
	 * @return the currentСalculation
	 */
	public Calculation getCurrentСalculation() {
		return currentCalculation;
	}


	/**
	 * @param currentСalculation the currentСalculation to set
	 */
	public void setCurrentСalculation(Calculation currentСalculation) {
		this.currentCalculation = currentСalculation;
	}

	@Override
	public String toString() {
		return "RouteLine [routeLineID=" + routeLineID + ", version=" + version + ", countrySender=" + countrySender
				+ ", currentСalculation=" + currentCalculation + ", logisticsSiteSender=" + logisticsSiteSender
				+ ", countryReceiver=" + countryReceiver + ", logisticsSiteReceiver=" + logisticsSiteReceiver
				+ ", product=" + product + ", transport=" + transport + ", quantity=" + quantity
				+ ", requestedQuantity=" + requestedQuantity + ", amountProduct=" + amountProduct + ", amountTransport="
				+ amountTransport + ", totalAmount=" + totalAmount + ", durationInDays=" + durationInDays + ", covered="
				+ covered + ", isOptimalChain=" + isOptimalChain + "]";
	}


	/**
	 * toCSV
	 * @return CSV string
	 */
	public String toCSV() {
		return   "\"" + routeLineID + "\",\"" + version +"\",\""+ currentCalculation +"\",\"" + countrySender + "\",\"" 
	            + logisticsSiteSender + "\",\"" + countryReceiver + "\",\"" 
	            + logisticsSiteReceiver + "\",\"" + product + "\",\"" 
	            + transport + "\"," + quantity + "," + requestedQuantity + "," + amountProduct + "," 
	            + amountTransport + "," + totalAmount + "," + durationInDays + ",\"" 
	            + covered + "\",\"" + isOptimalChain + "\"";
	}
	/**
	 * CSVHeder
	 * @return CSVHeder
	 */
	public static String CSVHeder() {
		return "routeLineID,version,currentСalculation,countrySender,logisticsSiteSender,countryReceiver,"
	            + "logisticsSiteReceiver,product,transport,quantity,requestedQuantity,amountProduct,"
	            + "amountTransport,totalAmount,durationInDays,covered,isOptimalChain";
	}
	
	
}
