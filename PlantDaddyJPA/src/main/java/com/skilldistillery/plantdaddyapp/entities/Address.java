package com.skilldistillery.plantdaddyapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String street;
	
	private String street2;
	
	private String city;
	
	private String state;
	
	
	@Column(name="area_code")
	private String areaCode;
	
	@OneToOne(mappedBy="address")
	private Store store;

	
	@OneToOne(mappedBy="address")
	private User user;
	  
	
	public Address() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", street2=" + street2 + ", city=" + city + ", state="
				+ state + ", areaCode=" + areaCode + ", store=" + store + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaCode, city, id, state, store, street, street2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(areaCode, other.areaCode) && Objects.equals(city, other.city)
				&& Objects.equals(id, other.id) && Objects.equals(state, other.state)
				&& Objects.equals(store, other.store) && Objects.equals(street, other.street)
				&& Objects.equals(street2, other.street2);
	}
	
	
	
	
}
