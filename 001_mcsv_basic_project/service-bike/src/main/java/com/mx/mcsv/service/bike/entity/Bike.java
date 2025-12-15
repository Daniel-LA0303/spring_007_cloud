package com.mx.mcsv.service.bike.entity;

/*import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;*/
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String brand;

	private String model;

	private int userId;

	public Bike() {

	}

	public Bike(int id, String brand, String model, int userId) {

		this.id = id;
		this.brand = brand;
		this.model = model;
		this.userId = userId;
	}

	public String getBrand() {
		return brand;
	}

	public int getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public int getUserId() {
		return userId;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}