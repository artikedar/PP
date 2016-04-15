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
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.service.FilmServiceImpl;
import com.flp.service.IFilmService;

/**
 * Servlet implementation class FilmSearch
 */
public class FilmSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFilmService filmService=new FilmServiceImpl();
		List<Film> films=new ArrayList<>();
		films=filmService.getAllFilms();
		Actor actor=new Actor();
		
		int serFilmId=Integer.parseInt(request.getParameter("filmId"));
		String title=request.getParameter("filmTilte");
		String actor3=request.getParameter("actor");
		String languages=request.getParameter("orgLang");
		
		System.out.println(serFilmId+title+actor3+languages);
		films=filmService.searchFilm(serFilmId, title, actor3, languages);
		System.out.println(films);
		
		//Print the Found Film
		PrintWriter out=response.getWriter();
		out.println("<html>"
				+ "<h4>Got following Results</h4>"
				+ "<head>Search Results</head>"
				+ "<body>"
				+ "<table border='1'>"
				
				+ "<tr>"
				+ "<th>FilmId</th>"
				+ "<th>Title</th>"
				+ "<th>Description</th>"
				+ "<th>Release Year</th>"
				+ "<th>Original Language</th>"
				+ "<th>Rental Duration</th>"
				+ "<th>Length</th>"
				+ "<th>Replacement Cost</th>"
				+ "<th>Rating</th>"
				+ "<th>Special Features</th>"
				+ "<th>Category</th>"
				+ "<th>List of Actors</th>"
				+ "<th>List of Other Languages</th>"
				+ "</tr>");
				
				
		for(Film allFilms:films){
			
			out.println(
					
			"<tr>"
			+"<td>"+allFilms.getFilm_ID()+"</td>"
			+"<td>"+allFilms.getTitle()+"</td>"
			+"<td>"+allFilms.getDescription()+"</td>"
			+"<td>"+allFilms.getReleaseYear()+"</td>"
			
			
			+"<td>"+(allFilms.getOriginalLanguage()).getName()+"</td>"
			
			
			
			+"<td>"+allFilms.getRentalDuration()+"</td>"
			+"<td>"+allFilms.getLength()+"</td>"
			+"<td>"+allFilms.getReplacementCost()+"</td>"
			+"<td>"+allFilms.getRatings()+"</td>"
			+"<td>"+allFilms.getSpecialFeatures()+"</td>"
			+"<td>"+(allFilms.getCategory()).getName()+"</td>");
			
			Set<Actor> act=allFilms.getActors();
			out.println("<td>");
			for(Actor actors:act){
				String actor2=actors.getFirst_Name()+actors.getLast_Name();
				out.println(actor2); 
			}
			out.println("</td>");
			
			List<Language> langs=allFilms.getLanguages();
			out.println("<td>");
			for(Language otherLan:langs){
				String lang=otherLan.getName();
				out.println(lang); 
			}
			out.println("</td>");
			
			out.println(
			
			 "</tr>");
		}
		
		out.println(		
		"</table>"
		+ "</body>"
		+ "</html>");

		
	}

}
