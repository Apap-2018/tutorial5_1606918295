package com.apap.tutorial5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public FlightModel getFlight(Long id) {
		return flightDb.getOne(id);
	}
	
	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
	}
	
	@Override
	public void updateFlight(FlightModel flightModel, Long id) {
		FlightModel updatef = flightDb.getOne(id);
		updatef.setDestination(flightModel.getDestination());
		updatef.setFlightNumber(flightModel.getFlightNumber());
		updatef.setOrigin(flightModel.getOrigin());
		updatef.setTime(flightModel.getTime());
		flightDb.save(updatef);
	}
}
