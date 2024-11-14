package UPT_PL.Team_3.model;

import java.util.Comparator;

public class QtyComparator implements Comparator<SupplyReceiveByCountry> {	
	@Override
	public int compare(SupplyReceiveByCountry o1, SupplyReceiveByCountry o2) {
		if(o1.getQuantity() > o2.getQuantity())
			return 1;
		if(o1.getQuantity() < o2.getQuantity())
			return -1;
		return 0;
	}

}
