package com.jay.flightcheckin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jay.flightcheckin.integration.ReservationRestClient;
import com.jay.flightcheckin.integration.dto.Reservation;
import com.jay.flightcheckin.integration.dto.UpdateReservationRequest;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClient reservationRestClient;

	@RequestMapping("/showStartCheckin")
	public String showCheckIn() {
		System.out.println("Inside show check in method**********");
		return "startCheckIn";
	}

	@RequestMapping(value = "/startCheckIn", method = RequestMethod.POST)
	public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {

		System.out.println("inside check in method");

		Reservation reservation = reservationRestClient.findReservation(reservationId);
		System.out.println("***********************************");
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}

	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
			@RequestParam("numberOfBags") int numberOfBags, ModelMap modelmap) {
		UpdateReservationRequest reservationUpdateRequest = new UpdateReservationRequest();
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		reservationRestClient.updateReservation(reservationUpdateRequest);
		modelmap.addAttribute("msg", "Check in completed successfully");
		return "checkInConfirmation";
	}
}
