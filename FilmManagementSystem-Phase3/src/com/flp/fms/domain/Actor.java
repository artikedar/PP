package com.flp.fms.domain;

public class Actor {
	
	
	
	private int actor_Id;
	private String first_Name;
	private String last_Name;

	
	//No Argument Constructor
	public Actor(){}
	
	//Constructor with Fields
	public Actor(int actor_Id, String first_Name, String last_Name, int film_Id) {
		super();
		this.actor_Id = actor_Id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
	
	};
	
	//Getters and Setters
	public int getActor_Id() {
		return actor_Id;
	}
	public void setActor_Id(int actor_Id) {
		this.actor_Id = actor_Id;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	
	//Generate toString method
	@Override
	public String toString() {
		return "Actor [actor_Id=" + actor_Id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + "]";
	}
	
}
