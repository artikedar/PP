package com.flp.service;

import java.util.List;
import java.util.Map;

import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;

public interface IFilmService {

	public List<Language> getLanguages();
	public List<Category> getCategories();
	public void addFilm(Film film);
	public List<Film> getAllFilms();
	public int removeFilm(int id);
	public List<Film> searchFilm(int id, String title, String actor, String language);

	
}
