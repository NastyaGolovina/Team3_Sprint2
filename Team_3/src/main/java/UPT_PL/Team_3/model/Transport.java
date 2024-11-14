package UPT_PL.Team_3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The Transport class represents a transport service.
 * Each transport has an ID, name, and price per ton for its service.
 */
@Entity
@Table(name = "Transports")
public class Transport {
    @Id
    @Column(name = "Transport_Id", length = 20, nullable = false)
    private String transportId;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "Price_Per_Ton", nullable = false)
    private double pricePerTon;

    /**
     * Default constructor required by JPA.
     */
    public Transport() {
    }
    
    /**
     * Constructor to initialize the Transport object with the provided parameters.
     * 
     * @param transportId The unique ID of the transport.
     * @param name The name of the transport service or vehicle.
     * @param pricePerTon The price per ton for using this transport.
     */
    public Transport(String transportId, String name, double pricePerTon) {
        this.transportId = transportId;
        this.name = name;
        this.pricePerTon = pricePerTon;
    }

    // Getters
    public String getTransportId() {
        return transportId;
    }

    public String getName() {
        return name;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }

    // Setters
    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerTon(double pricePerTon) {
        this.pricePerTon = pricePerTon;
    }

    @Override
    public String toString() {
        return "Transport [TransportId=" + transportId + ", name=" + name + ", pricePerTon=" + pricePerTon + "]";
    }
}
