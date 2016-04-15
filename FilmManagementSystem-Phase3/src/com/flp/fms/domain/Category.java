package com.flp.fms.domain;

public class Category {
	
	
	private int category_Id;
	private String name;
	
	
	//No Argument Constructor
	public Category(){};
	
	//Constructor with Fields
	public Category(int category_Id, String name) {
		super();
		this.category_Id = category_Id;
		this.name = name;
		
	}
	
	//Getters and Setters
	public int getCategory_Id() {
		return category_Id;
	}
	public void setCategory_Id(int category_Id) {
		this.category_Id = category_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		return "Category [category_Id=" + category_Id + ", name=" + name + "]";
	}

}
