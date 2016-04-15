package org.capgemini.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.service.ActorServiceImpl;
import com.flp.service.FilmServiceImpl;
import com.flp.service.IActorService;
import com.flp.service.IFilmService;

/**
 * Servlet implementation class SearchFilm
 */
public class SearchFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFilmService filmService=new FilmServiceImpl();
		List<Film> films=new ArrayList<>();
		films=filmService.getAllFilms();
		
		IActorService actorService=new ActorServiceImpl();
		Actor actor=new Actor();
		
		List<Language> lang=filmService.getLanguages();
		List<Category> categories=filmService.getCategories();
		Set<Actor> actors=actorService.getActors();
		
		PrintWriter out=response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>Search Film</title>"
				+ "<link rel='stylesheet' href='//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css'>"
				+ "<script src='//code.jquery.com/jquery-1.10.2.js'></script>"
				+ "<script src='//code.jquery.com/ui/1.11.4/jquery-ui.js'></script>"
				+ "<script type='text/javascript' src='script/validateFilm.js'></script>"
				+ "<link type='text/css' rel='stylesheet' href='css/MyStyle.css'>"
				+ "<script>$(function() {$( '#datepicker' ).datepicker();});</script>"
				+ "<script>$(function() {$( '#datepicker1' ).datepicker();});</script>"
				+ "<script>$(document).ready(function() {$('#start_datepicker')datepicker();$('#end_datepicker').datepicker();});</script>"
				+ "</head>"
				
				
				                           
				+ "<body>"
				+ "<form  action='FilmSearch' name='filmSearch'>"
				
				+ "<table>"
				+ "<h3>Search Films By</h3> "
				
				//film Id
				+ "<tr>"
				+ "<td>Film Id:</td>"
				+ "<td><input type='text' name='filmId'>"
				+ "<div id='filmIdSerErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				
				//film title
				+ "<tr>"
				+ "<td>Title</td>"
				+ "<td><input type='text' name='filmTilte'>"
				+ "<div id='filmTitSerErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				//actor
				+ "<tr>"
				+ "<td>Select Actor</td>"
				+ "<td><select name='actor'>"
				+ "<option value='' name='select'></option>");
				for(Actor acts:actors){
					
					out.println("<option value=" +acts.getActor_Id()+">"+acts.getFirst_Name()+" "+acts.getLast_Name()+"</option>");
				}
				out.println(
				"<div id='actorSerErr' class='errMsg'></div></td>"
				+"</select>"
				+ "</tr>");
		
							
				//original language
				out.println(
				"<tr>"
				+ "<td>Select Original Language</td>"
				+ "<td><select name='orgLang'>"
				+ "<option value='' name='select'></option>");
				
				for(Language language:lang){
					out.println("<option value=" +language.getLanguage_Id()+ ">"+language.getName()+"</option>");
				}
				
				out.println(
				"<div id='LangSerErr' class='errMsg'></div></td>"
				+"</select>"
				+ "</tr>"
						
				+ "<tr>"
				+ "<td></td>"
				+ "<td>"
				+ "<input type='submit' value='Search' >"
				+ "<input type='reset' value='Clear'>"
				+ "</td>"
				+ "</tr>"
				
				+ "</table>"
				+ "</body>"
				+ "</html>");
		
		
		
	}

}
