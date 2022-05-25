package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{

}
