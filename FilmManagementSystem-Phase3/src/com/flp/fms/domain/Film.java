package com.flp.fms.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Film {
	private int film_ID;
	private String title;
	private String description;
	private Date releaseYear;
	private List<Language> languages;
	private Language originalLanguage;
	private int length;
	private Double replacementCost;
	private int ratings;
	private String specialFeatures;
	private Set<Actor> actors;
	private Category category;
	private Date rentalDuration;
	
	//No Argument Constructor
	public Film(){}

	//Constructor with Fields
	public Film(int film_ID, String title, String description, Date releaseYear, List<Language> languages,
			Language originalLanguage, int length, Double replacementCost, int ratings, String specialFeatures,
			Set<Actor> actors, Category category, Date rentalDuration) {
		super();
		this.film_ID = film_ID;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languages = languages;
		this.originalLanguage = originalLanguage;
		this.length = length;
		this.replacementCost = replacementCost;
		this.ratings = ratings;
		this.specialFeatures = specialFeatures;
		this.actors = actors;
		this.category = category;
		this.rentalDuration = rentalDuration;
	}
	
	
	//Getters and Setters
	public int getFilm_ID() {
		return film_ID;
	}

	public void setFilm_ID(int film_ID) {
		this.film_ID = film_ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Language getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Date rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	
	//To string Method to print the details
	@Override
	public String toString() {
		return "Film [film_ID=" + film_ID + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", languages=" + languages + ", originalLanguage=" + originalLanguage + ", length="
				+ length + ", replacementCost=" + replacementCost + ", ratings=" + ratings + ", specialFeatures="
				+ specialFeatures + ", actors=" + actors + ", category=" + category + ", rentalDuration="
				+ rentalDuration + "]";
	}
	

}
