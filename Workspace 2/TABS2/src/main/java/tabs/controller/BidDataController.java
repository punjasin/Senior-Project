package tabs.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.bitwalker.useragentutils.UserAgent;
import tabs.entity.BidData;
import tabs.service.bidding.BidDataService;

@Transactional
@RestController
public class BidDataController {
	@Autowired
	BidDataService bidDataService;
	
	
	
	
	@RequestMapping(value = "/getBidData", method = RequestMethod.GET)
	public ResponseEntity<Collection<BidData>> getBidDataList(
			@RequestParam("bId") Long bId) {
		Collection<BidData> bidDataList = bidDataService
				.getBidDataListByBiddingId(bId);
		return new ResponseEntity<Collection<BidData>>(bidDataList,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserBidData/{biddingID}/{student_id}")
	public ResponseEntity<BidData> getUserBidData(@PathVariable("biddingID") Long biddingID,
			@PathVariable("student_id") int student_id){
		BidData bidData = bidDataService.getUserBidData(biddingID, student_id);
		return new ResponseEntity<BidData>(bidData,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/placeBid", method = RequestMethod.POST)
	public ResponseEntity<BidData> placeBid(@RequestBody BidData bidData,
			HttpServletRequest request, HttpSession session,
			@RequestHeader(value = "User-Agent") String userAgent) {
		bidData.setIpAddress(request.getRemoteAddr());
		UserAgent agent = UserAgent.parseUserAgentString(userAgent);
		bidData.setBrowser(agent.getBrowser().toString() + "_"
				+ agent.getBrowserVersion().toString());
		bidData.setOs(agent.getOperatingSystem().toString());
		bidData.setSid(session.getId());		
		BidData savedBidData = bidDataService.saveBidData(bidData);
		return new ResponseEntity<BidData>(savedBidData, HttpStatus.OK);
	}
}
