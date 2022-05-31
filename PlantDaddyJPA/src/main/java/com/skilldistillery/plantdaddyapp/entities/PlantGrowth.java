package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(name="growth_snapshot")
public class PlantGrowth {

	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="growth_id")
		private int id;
	
		@Column(name="height_inches")
		private double height;
	
		@Column(name="spread_inches")
		private double spread;
	
		@Column(name="pot_diameter")
		private double potDiameter;
	
		@Column(name="create_date")
		private LocalDateTime createDate;
		
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name="user_plant_id")
		private UserPlant userPlant;
		
		
		public PlantGrowth() {
			super();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

		public double getSpread() {
			return spread;
		}

		public void setSpread(double spread) {
			this.spread = spread;
		}

		public double getPotDiameter() {
			return potDiameter;
		}

		public void setPotDiameter(double potDiameter) {
			this.potDiameter = potDiameter;
		}

		public LocalDateTime getCreateDate() {
			return createDate;
		}

		public void setCreateDate(LocalDateTime createDate) {
			this.createDate = createDate;
		}
		
		
		public UserPlant getUserPlant() {
			return userPlant;
		}

		public void setUserPlant(UserPlant userPlant) {
			this.userPlant = userPlant;
		}

		@Override
		public String toString() {
			return "PlantGrowth [id=" + id + ", height=" + height + ", spread=" + spread + ", potDiameter="
					+ potDiameter + ", createDate=" + createDate + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(createDate, height, id, potDiameter, spread);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PlantGrowth other = (PlantGrowth) obj;
			return Objects.equals(createDate, other.createDate)
					&& Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height) && id == other.id
					&& Double.doubleToLongBits(potDiameter) == Double.doubleToLongBits(other.potDiameter)
					&& Double.doubleToLongBits(spread) == Double.doubleToLongBits(other.spread);
		}
	
		
		
		
}
