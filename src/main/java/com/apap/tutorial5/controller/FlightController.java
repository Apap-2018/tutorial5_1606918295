package com.apap.tutorial5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightServiceImpl;
import com.apap.tutorial5.service.PilotService;
import org.springframework.validation.BindingResult;

@Controller
public class FlightController {
	@Autowired
	private FlightServiceImpl flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value="/flight/view")
	private String viewAllPilot(@RequestParam(value = "id") Long id, Model model) {
		FlightModel flight = flightService.getFlight(id);
		model.addAttribute("flight", flight);
		model.addAttribute("pilotName", flight.getPilot().getName());
		model.addAttribute("pilotLicenseNumber", flight.getPilot().getLicenseNumber());
		model.addAttribute("pilotFlyHour", flight.getPilot().getFlyHour());
		return "view-flight";
	} 
	
	@RequestMapping(value="/flight/update", method=RequestMethod.GET)
	private String update(@RequestParam(value="id") Long id, Model model) {
		FlightModel archive = flightService.getFlight(id);
		model.addAttribute("flightId", archive.getId());
		model.addAttribute("flightFlightNumber", archive.getFlightNumber());
		model.addAttribute("flightOrigin", archive.getOrigin());
		model.addAttribute("flightDestination", archive.getDestination());
		model.addAttribute("flightTime", archive.getTime());
		model.addAttribute("flight", archive);
		return "update-flight";
	}	
	
	@RequestMapping(value="/flight/update", method=RequestMethod.POST)
	private String updateFlight(@ModelAttribute FlightModel flight) {
		flightService.updateFlight(flight, flight.getId());
		return "update";
	}	
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight);
		}
		
		return "delete";
	}
	
//	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"addRow"})
//	public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
//		if (pilot.getPilotFlight() == null) {
//			pilot.setPilotFlight(new List<FlightModel>());
//		}
//        
//		pilot.getPilotFlight().add(new FlightModel());
//		
//		model.addAttribute("pilot", pilot);
//		return "addFlight";
//	}
}

