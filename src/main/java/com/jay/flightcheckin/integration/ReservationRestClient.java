package com.jay.flightcheckin.integration;

import com.jay.flightcheckin.integration.dto.Reservation;
import com.jay.flightcheckin.integration.dto.UpdateReservationRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(UpdateReservationRequest request);
}
