package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Address findByUser_UsernameAndId(String username, int id);

	List<Address> findByStreetLikeOrStateLikeOrCityLike(String keyword, String keyword2, String keyword3);

}
