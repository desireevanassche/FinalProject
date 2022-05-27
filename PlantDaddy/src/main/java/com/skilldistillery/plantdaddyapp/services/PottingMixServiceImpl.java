package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.PottingMix;
import com.skilldistillery.plantdaddyapp.repositories.PottingMixRepository;

@Service
public class PottingMixServiceImpl implements PottingMixService {

	@Autowired
	private PottingMixRepository potRepo;
	
	@Override
	public List<PottingMix> index() {
		return potRepo.findAll();
	}

	@Override
	public PottingMix findById(int id) {
		return potRepo.findById(id).get();
	}

	@Override
	public PottingMix addPottingMix(PottingMix potMix, String username) {
		potMix = potRepo.saveAndFlush(potMix);
		return potMix;
	}

	@Override
	public PottingMix updatePottingMix(PottingMix potMix, int id, String username) {
		PottingMix managed = potRepo.findByUser_UsernameAndId(username, id);
		if(managed != null) {
			managed.setBrand(potMix.getBrand());
			managed.setName(potMix.getName());
			managed.setType(potMix.getType());
			managed.setQuantity(potMix.getQuantity());
			managed.setUnit(potMix.getUnit());
			managed.setImageUrl(potMix.getImageUrl());
			potRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public PottingMix deactivate(PottingMix potMix, int id, String username) {
		PottingMix deactivate = potRepo.findByUser_UsernameAndId(username, id);
		if (deactivate != null) {
			deactivate.setActive(false);
			potRepo.saveAndFlush(deactivate);
		}
		return deactivate;
	}

	@Override
	public List<PottingMix> listPottingMixByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		
		return potRepo.findByBrandLikeOrNameLikeOrTypeLike(keyword, keyword, keyword);
	}

	@Override
	public List<PottingMix> listPottingMixByUsername(String name) {
		return potRepo.findByUser_Username(name);
	}

}
