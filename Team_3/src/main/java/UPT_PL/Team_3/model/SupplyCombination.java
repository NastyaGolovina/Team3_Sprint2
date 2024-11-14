package UPT_PL.Team_3.model;

import java.util.ArrayList;

public class SupplyCombination {
	//	Instance variable
	private String supplyCombinationID;
	private ArrayList<SupplyReceiveByCountry> supplyList;
	private boolean covered;
	
	
	/**
	 *  Constructor
	 * @param supplyCombinationID
	 * @param supplyList
	 * @param covered
	 */
	public SupplyCombination(String supplyCombinationID) {
		this.supplyCombinationID = supplyCombinationID;
		this.covered = false;
		supplyList = new ArrayList<SupplyReceiveByCountry>();
	}


	/**
	 * @return the supplyCombinationID
	 */
	public String getSupplyCombinationID() {
		return supplyCombinationID;
	}


	/**
	 * @param supplyCombinationID the supplyCombinationID to set
	 */
	public void setSupplyCombinationID(String supplyCombinationID) {
		this.supplyCombinationID = supplyCombinationID;
	}


	/**
	 * @return the supplyList
	 */
	public ArrayList<SupplyReceiveByCountry> getSupplyList() {
		return supplyList;
	}


	/**
	 * @param supplyList the supplyList to set
	 */
	public void setSupplyList(ArrayList<SupplyReceiveByCountry> supplyList) {
		this.supplyList = supplyList;
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


	@Override
	public String toString() {
		return "SupplyCombination [supplyCombinationID=" + supplyCombinationID + ", covered=" + covered + "]";
	}
	/**
	 * addSupplyReceiveByCountry
	 * @param newSupplyReceiveByCountry
	 */
	public void addSupplyReceiveByCountry(SupplyReceiveByCountry newSupplyReceiveByCountry) {
		supplyList.add(newSupplyReceiveByCountry);
	}

	

}
