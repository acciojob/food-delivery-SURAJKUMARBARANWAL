package com.driver.io.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "orders")
public class OrderEntity {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false,unique = true)
	private String orderId;

	@Column(nullable = false)
	private float cost;

	@Column(nullable = false)
	private String[] items;

	@Column(nullable = false)
	private boolean status;
	@Column(nullable = false)
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToOne
	@JoinColumn
	private UserEntity userEntity;

	//FK for food table
	@ManyToOne
	@JoinColumn
	private FoodEntity foodEntity;

	public FoodEntity getFoodEntity() {
		return foodEntity;
	}

	public void setFoodEntity(FoodEntity foodEntity) {
		this.foodEntity = foodEntity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
