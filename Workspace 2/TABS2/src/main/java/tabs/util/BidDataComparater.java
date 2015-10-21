package tabs.util;

import java.util.Comparator;

import tabs.entity.BidData;

public class BidDataComparater implements Comparator<BidData>{

	@Override
	public int compare(BidData o1, BidData o2) {
		int token1 = o1.getToken();
		int token2 = o2.getToken();
		
		if (token1 == token2)
			return 0;
		else if (token1 > token2)
			return 1;
		else
			return -1;	

	}
}
