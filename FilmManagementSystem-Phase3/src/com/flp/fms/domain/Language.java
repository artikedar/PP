package com.flp.fms.domain;

import java.util.Iterator;

public class Language {
	
	private int language_Id;
	private String name;

	
	//No Argument Constructor
	public Language(){}
	
	//Constructor with Fields
	public Language(int language_Id, String name) {
		super();
		this.language_Id = language_Id;
		this.name = name;
	
	};
	
	//Getters and Setters
	public int getLanguage_Id() {
		return language_Id;
	}

	public void setLanguage_Id(int language_Id) {
		this.language_Id = language_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Language [language_Id=" + language_Id + ", name=" + name + "]";
	}

	
}
