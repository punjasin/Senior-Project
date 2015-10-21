package tabs.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tabs.entity.BidData;
import tabs.service.bidding.BidDataService;


@Controller
public class WebController {
	
	@Autowired
	BidDataService bidser;
	
	// Match everything without a suffix (so not a static resource)
		@RequestMapping(value = "/{[path:[^\\.]*}")
		public String redirect() {
			// Forward to home page so that route is preserved.
			return "forward:/index.html";
		}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	@RequestMapping(value = "/bidding/*")
	public String redirectBidding() {
		// Forward to home page so that route is preserved.
		return "forward:/index.html";
	}
	
	@RequestMapping(value = "/activity/*")
	public String redirectActivity() {
		// Forward to home page so that route is preserved.
		return "forward:/index.html";
	}
	
	
	@RequestMapping(value = "/bidding/downloadPDF/{bidding_id}",method = RequestMethod.GET)
	public ModelAndView downloadpdf(@PathVariable("bidding_id")Long bidding_id){
		Collection<BidData> winList = bidser.getWinBidDataList(bidding_id);
		return new ModelAndView("pdfView","winList",winList);
	 }
}
