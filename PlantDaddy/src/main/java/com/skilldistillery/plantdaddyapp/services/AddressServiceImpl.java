package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Address;
import com.skilldistillery.plantdaddyapp.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> index() {
		return addressRepo.findAll();
	}

	@Override
	public Address findById(int id) {
		return addressRepo.findById(id).get();
	}

	@Override
	public Address updateAddress(Address address, int id, String username) {
		Address managed = addressRepo.findById(id).get();
		if (managed != null) {
			managed.setStreet(address.getStreet());
			managed.setStreet2(address.getStreet2());
			managed.setCity(address.getCity());
			managed.setState(address.getState());
			managed.setAreaCode(address.getAreaCode());
			addressRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public List<Address> listByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return addressRepo.findByStreetLikeOrStateLikeOrCityLike(keyword, keyword, keyword);
	}

	@Override
	public Address addAddress(Address address, String username) {
		address = addressRepo.saveAndFlush(address);
		return address;
	}

}
