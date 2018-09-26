package com.apap.tutorial3.service;

import java.util.List;
import java.util.ArrayList;

import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.Service;


public interface PilotService {
	void addPilot (PilotModel pilot);
	PilotModel getPilotDetailById(String id);
	List<PilotModel> getPilotList();
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	

}
@Service
class PilotInMemoryService implements PilotService{
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
		
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	@Override
	public List<PilotModel> getPilotList(){
		return archivePilot;
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String id) {
		for(PilotModel p: archivePilot) {
			if(p.getLicenseNumber().equals(id)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public PilotModel getPilotDetailById(String id) {
		for(PilotModel p: archivePilot) {
			
			System.out.println("bacot");
			if(p.getId().equals(id)) {
				
				return p;
				
			}
		}
		return null;
	}
}
