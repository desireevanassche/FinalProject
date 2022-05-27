package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Address;

public interface AddressService {

	public List<Address> index();
	
	public Address findById(int id);
	
	public Address addAddress(Address address, String username);
	
	public Address updateAddress(Address address, int id, String username);
	
	public List<Address> listByKeyword(String keyword);
}
