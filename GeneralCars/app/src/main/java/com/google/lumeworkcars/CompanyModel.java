package com.google.lumeworkcars;

public class CompanyModel
{
	String name, address;
	byte[] image;
	long Id;

	public CompanyModel(String name, String address, byte[] image, long id) {
		this.name = name;
		this.address = address;
		this.image = image;
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public CompanyModel() {
	}
}
