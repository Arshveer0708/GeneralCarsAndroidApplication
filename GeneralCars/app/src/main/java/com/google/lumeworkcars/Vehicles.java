package com.google.lumeworkcars;

public class Vehicles {

	String makers, model_of_vehicle,condition_of_vehicle,engine_cylinders_of_vehicle, year_of_manufacturing,number_of_doors,price_of_vehicle;
	String color_of_vehicle, date_sold;
	byte[] image;
	long id;

	public Vehicles() {

	}

	public Vehicles(String makers, String model_of_vehicle, String condition_of_vehicle, String engine_cylinders_of_vehicle, String year_of_manufacturing, String number_of_doors, String price_of_vehicle, String color_of_vehicle, String date_sold, byte[] image, long id) {
		this.makers = makers;
		this.model_of_vehicle = model_of_vehicle;
		this.condition_of_vehicle = condition_of_vehicle;
		this.engine_cylinders_of_vehicle = engine_cylinders_of_vehicle;
		this.year_of_manufacturing = year_of_manufacturing;
		this.number_of_doors = number_of_doors;
		this.price_of_vehicle = price_of_vehicle;
		this.color_of_vehicle = color_of_vehicle;
		this.date_sold = date_sold;
		this.image = image;
		this.id = id;
	}


	public String getMakers() {
		return makers;
	}

	public void setMakers(String makers) {
		this.makers = makers;
	}

	public String getModel_of_vehicle() {
		return model_of_vehicle;
	}

	public void setModel_of_vehicle(String model_of_vehicle) {
		this.model_of_vehicle = model_of_vehicle;
	}

	public String getCondition_of_vehicle() {
		return condition_of_vehicle;
	}

	public void setCondition_of_vehicle(String condition_of_vehicle) {
		this.condition_of_vehicle = condition_of_vehicle;
	}

	public String getEngine_cylinders_of_vehicle() {
		return engine_cylinders_of_vehicle;
	}

	public void setEngine_cylinders_of_vehicle(String engine_cylinders_of_vehicle) {
		this.engine_cylinders_of_vehicle = engine_cylinders_of_vehicle;
	}

	public String getYear_of_manufacturing() {
		return year_of_manufacturing;
	}

	public void setYear_of_manufacturing(String year_of_manufacturing) {
		this.year_of_manufacturing = year_of_manufacturing;
	}

	public String getNumber_of_doors() {
		return number_of_doors;
	}

	public void setNumber_of_doors(String number_of_doors) {
		this.number_of_doors = number_of_doors;
	}

	public String getPrice_of_vehicle() {
		return price_of_vehicle;
	}

	public void setPrice_of_vehicle(String price_of_vehicle) {
		this.price_of_vehicle = price_of_vehicle;
	}

	public String getColor_of_vehicle() {
		return color_of_vehicle;
	}

	public void setColor_of_vehicle(String color_of_vehicle) {
		this.color_of_vehicle = color_of_vehicle;
	}

	public String getDate_sold() {
		return date_sold;
	}

	public void setDate_sold(String date_sold) {
		this.date_sold = date_sold;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
