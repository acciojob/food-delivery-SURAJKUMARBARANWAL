package com.driver.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.Order;

@Entity(name = "foods")
public class FoodEntity{
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false,unique = true)
	private String foodId;
	
	@Column(nullable = false)
	private String foodName;
	
	@Column(nullable = false)
	private float foodPrice;
	
	@Column(nullable = false)
	private String foodCategory;


	@OneToMany(mappedBy = "foodEntity",cascade = CascadeType.ALL)
	private List<OrderEntity> orderEntityList;

	public List<OrderEntity> getOrderEntityList() {
		return orderEntityList;
	}

	public void setOrderEntityList(List<OrderEntity> orderEntityList) {
		this.orderEntityList = orderEntityList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public float getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
}
