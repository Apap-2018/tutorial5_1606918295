package com.apap.tutorial5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public void deletePilot(PilotModel pilot) {
		pilotDb.delete(pilot);
		
	}
	
	@Override
	public void updatePilot(PilotModel pilot, String licenseNumber) {
		PilotModel update = pilotDb.findByLicenseNumber(licenseNumber);
		update.setFlyHour(pilot.getFlyHour());
		update.setName(pilot.getName());
		pilotDb.save(update);
		
	}
	
	public PilotDB getManager() {
        return pilotDb;
    }
	
}
