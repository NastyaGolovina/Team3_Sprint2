package UPT_PL.Team_3.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The LogisticsSite class represents a logistics site in a specific country,
 * which manages and supplies various types of transport.
 */
@Entity
@Table(name = "LogisticsSites")
public class LogisticsSite {
	@Id                                                    
	@Column(name = "Site_Id",length = 20, nullable = false)
    private String siteId;
	@Column(name = "Name",length = 20, nullable = false)
    private String name;
	@ManyToOne
	@JoinColumn(name = "Country_Id")
    private Country country;
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "SuppliedTransport",
        joinColumns = @JoinColumn(name = "Site_Id"), 
        inverseJoinColumns = @JoinColumn(name = "Transport_Id") 
    )
    private List<Transport>suppliedTransports; 
	
	
    public LogisticsSite() {
    	suppliedTransports = new ArrayList<Transport>();
	}
    /**
     * Constructor to initialize the LogisticsSite object with the provided
     * parameters.
     * 
     * @param siteId             The ID of the logistic site.
     * @param name               The name of the logistics site.
     * @param country            The country where the logistics site is located.
     * @param suppliedTransports The list of transports supplied by the logistics site.
     */
    public LogisticsSite(String siteId, String name, Country country, ArrayList<Transport> suppliedTransports) {
        this.siteId = siteId;
        this.name = name;
        this.country = country;
        this.suppliedTransports = suppliedTransports != null ? suppliedTransports : new ArrayList<>();
    }

    // Getters
    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public List<Transport> getSuppliedTransports() {
        return new ArrayList<>(suppliedTransports);
    }

    // Setters
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setSuppliedTransports(ArrayList<Transport> suppliedTransports) {
        this.suppliedTransports = suppliedTransports != null ? suppliedTransports : new ArrayList<>();
    }

    /**
     * Adds a new transport to the list of supplied transports for the logistics site.
     *
     * @param transports The list of available transports to choose from.
     */
    public void addSuppliedTransport(Transports transports) {
       ArrayList<Transport> allTransports = transports.getTransportList();  // Updated to fetch the list, not display

        System.out.println("Available Transports:");
        for (int i = 0; i < allTransports.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + allTransports.get(i).toString());
        }

        int userChoice = ProjectHelper.inputInt("Select the transport number to add (or choose 0 to exit): ");

        while (userChoice != 0) {
            if (userChoice < 1 || userChoice > allTransports.size()) {
                System.out.println("Invalid choice. Please choose a valid transport number or 0 to exit.");
            } else {
                Transport selectedTransport = allTransports.get(userChoice - 1);

                if (suppliedTransports.contains(selectedTransport)) {
                    System.out.println("Transport is already supplied by this logistics site.");
                } else {
                    suppliedTransports.add(selectedTransport);
                    System.out.println("Transport " + selectedTransport.getName() + " added successfully.");
                }
            }

            userChoice = ProjectHelper.inputInt("Select the transport number to add (or choose 0 to exit): ");
        }

        System.out.println("Transport adding process finished.");
    }

    @Override
    public String toString() {
        return "LogisticsSite [siteId=" + siteId + ", name=" + name + ", country=" + country.getName() + "]";
    }
 
}
