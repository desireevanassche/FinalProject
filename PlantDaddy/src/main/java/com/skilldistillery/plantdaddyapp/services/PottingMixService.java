package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.PottingMix;

public interface PottingMixService {
	
	public List<PottingMix> index();
	
	public PottingMix findById(int id);
	
	public PottingMix addPottingMix(PottingMix potMix, String username);
	
	public PottingMix updatePottingMix(PottingMix potMix, int id, String username);
	
	public PottingMix deactivate(PottingMix potMix, int id, String username);
	
	public List<PottingMix> listPottingMixByKeyword(String keyword);
	
	public List<PottingMix> listPottingMixByUsername(String name);
	
	

}
