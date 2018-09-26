package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true)String id,
					  @RequestParam(value = "licenseNumber", required = true)String licenseNumber,
					  @RequestParam(value = "name", required = true)String name,
					  @RequestParam(value = "flyHour", required = true)int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
		public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
			PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}
	
	
	@RequestMapping(value= {"pilot/delete/id/{id}"})
	public String deletePilot(@PathVariable String id, Model model) {
		PilotModel archive = pilotService.getPilotDetailById(id);
		if(archive==null) {
			model.addAttribute("Founded", false);
		}
		else {
			for(int x = 0;x<pilotService.getPilotList().size();x++) {
				if(pilotService.getPilotList().get(x).getId().equals(archive.getId())) {
					pilotService.getPilotList().remove(x);
					model.addAttribute("Founded", true);
				}
			}
		}
		
		return "delete";
	}
	@RequestMapping(value= {"pilot/update/license-number/{licenseNumber}/fly-hour/{numberUpdate}"})
	public String updateFlyHour(@PathVariable String licenseNumber,@PathVariable String numberUpdate,Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		if(archive==null) {
			model.addAttribute("Founded", false);
		}
		else {
			model.addAttribute("Founded", true);
			
			pilotService.getPilotDetailByLicenseNumber(licenseNumber).setFlyHour(Integer.parseInt(numberUpdate));
		}
		return "update";
	}
	
	@RequestMapping(value = {"/pilot/view/license-number/{licenseNumber}"})
    public String viewLicense(@PathVariable("licenseNumber") String licenseNumber, Model model) {
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        if (archive == null){
            model.addAttribute("Founded", false);
        } else {
            model.addAttribute("Founded", true);
            model.addAttribute("pilot", archive);
        }
        return "viewpath-pilot";
	}
	
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		model.addAttribute("listPilot", archive);
		
		return "viewall-pilot";
	}
	
		
	
}
