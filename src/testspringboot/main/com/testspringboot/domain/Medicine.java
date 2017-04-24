package com.testspringboot.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicine")
public class Medicine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private long manufacturedDate;
	private long expiredDate;
	private double price;
	private int quantity;
	@CreatedDate
	private Date createdDate;
	
	public Medicine() {
		super();
	}

	public Medicine(String name, long manufacturedDate, long expiredDate,
			double price) {
		super();
		this.name = name;
		this.manufacturedDate = manufacturedDate;
		this.expiredDate = expiredDate;
		this.price = price;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(long manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public long getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(long expiredDate) {
		this.expiredDate = expiredDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
