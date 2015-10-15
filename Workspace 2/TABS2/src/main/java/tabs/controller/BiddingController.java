package tabs.controller;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tabs.entity.BidData;
import tabs.entity.Bidding;
import tabs.service.bidding.BidDataService;
import tabs.service.bidding.BiddingService;

@Transactional
@RestController
public class BiddingController {
	
	@Autowired
	BiddingService biddingService;
	
	@Autowired
	BidDataService bidDataService;
	
	@RequestMapping(value="/getBiddingList", method = RequestMethod.GET)
	public ResponseEntity<Collection<Bidding>> getBiddingList(){		
		Collection<Bidding> biddingList = biddingService.getBiddingList();
		return new ResponseEntity<Collection<Bidding>>(biddingList,HttpStatus.OK);
	}

	
	@RequestMapping(value="/getBidData", method = RequestMethod.GET)
	public ResponseEntity<Collection<BidData>> getBidDataList(@RequestParam("bId")Long bId){		
		Collection<BidData> bidDataList = bidDataService.getBidDataListByBiddingId(bId);
		return new ResponseEntity<Collection<BidData>>(bidDataList,HttpStatus.OK);
	}
	
	
}
