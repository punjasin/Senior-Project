package tabs.controller;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tabs.entity.Bidding;
import tabs.service.bidding.BiddingService;

@Transactional
@RestController
public class BiddingController {

	@Autowired
	BiddingService biddingService;

	
	@RequestMapping(value = "/getBiddingList", method = RequestMethod.GET)
	public ResponseEntity<Collection<Bidding>> getBiddingList() {
		Collection<Bidding> biddingList = biddingService.getBiddingList();
		return new ResponseEntity<Collection<Bidding>>(biddingList,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/createBiddingRest", method = RequestMethod.POST)
	public ResponseEntity<Bidding> createBidding(@RequestBody Bidding bidding) {
		Bidding createdBidding = biddingService.create(bidding);
		return new ResponseEntity<Bidding>(createdBidding, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/getBidding", method = RequestMethod.GET)
	public ResponseEntity<Bidding> getBidding(@RequestParam("id") Long id) {
		Bidding bidding = biddingService.getBidding(id);
		return new ResponseEntity<Bidding>(bidding, HttpStatus.OK);
	}

	

	@RequestMapping(value = "/updateBiddingRest")
	public ResponseEntity<Bidding> editBidding(@RequestBody Bidding bidding) {
		Bidding updatedBidding = biddingService.update(bidding);
		return new ResponseEntity<Bidding>(updatedBidding, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteBidding/{id}/{aId}", method = RequestMethod.DELETE)
	public void deleteBidding(@PathVariable("id") Long id,
			@PathVariable("aId") Long aId) {
		biddingService.deleteBidding(id, aId);
	}

}
