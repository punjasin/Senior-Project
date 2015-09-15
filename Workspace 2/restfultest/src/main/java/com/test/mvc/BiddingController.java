package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.test.model.Bidding;
import com.test.service.BiddingService;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
public class BiddingController {

	@Autowired
	BiddingService biddingService;
	
	@RequestMapping(value = "/testAng")
	public String testAng(){
		return "views/AngB";
	}

	@RequestMapping(value = "/")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("txt", "TEST HOME MODEL");
		return model;
	}

	@RequestMapping(value = "/bidding", method = RequestMethod.GET)
	public ModelAndView biddingList() {
		ModelAndView model = new ModelAndView("views/list");
				
		model.addObject("list", biddingService.findAll());

		return model;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		
		model.addAttribute("bidding", new Bidding());
			
		return "views/form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute Bidding bidding, Model model, HttpServletRequest request,
			HttpSession session,
			@RequestHeader(value = "User-Agent") String userAgent) {
		String userIpAddress = request.getRemoteAddr();
		UserAgent agent = UserAgent.parseUserAgentString(userAgent);
		String browser = agent.getBrowser().toString();
		String version = agent.getBrowserVersion().toString();
		String os = agent.getOperatingSystem().toString();
		String sid = session.getId();
		
		bidding.setIp_address(userIpAddress);
		bidding.setBrowser(browser);
		bidding.setOs(os);
		bidding.setVersion(version);
		bidding.setSession_id(sid);
		
		biddingService.create(bidding);		
		return "index";
	}

}
