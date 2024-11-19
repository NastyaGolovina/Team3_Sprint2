package UPT_PL.Team_3.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Calculation")
public class Calculation {
	//	Instance variable
	@Id                                                    
	@Column(name = "Calculation_Id", nullable = false)
	private String calculationId;	
	@Column(name = "calculationDate", nullable = false)
	private String calculationDate;
	@Column(name = "calculationDescription",length = 255, nullable = false)
	private String calculationDescription;
	@Column(name = "sortBy",length = 50, nullable = false)
	private String sortBy;
	
	
	
	/**
	 * 
	 */
	public Calculation() {
	}



	/**
	 * Constructor
	 * @param calculationDescription
	 */
	public Calculation(String calculationDescription) {
		this.calculationId = UUID.randomUUID().toString();
		this.calculationDate = LocalDate.now().toString() ;
		this.calculationDescription = calculationDescription;
	}
	


	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}



	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}



	/**
	 * @return the calculationId
	 */
	public String getCalculationId() {
		return calculationId;
	}
	/**
	 * @param calculationId the calculationId to set
	 */
	public void setCalculationId(String calculationId) {
		this.calculationId = calculationId;
	}
	/**
	 * @return the calculationDate
	 */
	public String getCalculationDate() {
		return calculationDate;
	}
	/**
	 * @param calculationDate the calculationDate to set
	 */
	public void setCalculationDate(String calculationDate) {
		this.calculationDate = calculationDate;
	}
	/**
	 * @return the calculationDescription
	 */
	public String getCalculationDescription() {
		return calculationDescription;
	}
	/**
	 * @param calculationDescription the calculationDescription to set
	 */
	public void setCalculationDescription(String calculationDescription) {
		this.calculationDescription = calculationDescription;
	}
	@Override
	public String toString() {
		return "Сalculation [calculationId=" + calculationId + ", calculationDate=" + calculationDate
				+ ", calculationDescription=" + calculationDescription + ", sortBy=" + sortBy + "]";
	}
	
	
	
}
