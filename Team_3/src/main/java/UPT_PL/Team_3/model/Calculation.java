package UPT_PL.Team_3.model;

import java.time.LocalDate;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long calculationId;	
	@Column(name = "calculationDate", nullable = false)
	private LocalDate calculationDate;
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
		this.calculationDate = LocalDate.now() ;
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
	public long getCalculationId() {
		return calculationId;
	}
	/**
	 * @param calculationId the calculationId to set
	 */
	public void setCalculationId(long calculationId) {
		this.calculationId = calculationId;
	}
	/**
	 * @return the calculationDate
	 */
	public LocalDate getCalculationDate() {
		return calculationDate;
	}
	/**
	 * @param calculationDate the calculationDate to set
	 */
	public void setCalculationDate(LocalDate calculationDate) {
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
