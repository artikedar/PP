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

import com.flp.fms.dao.ActorDaoImplForList;
import com.flp.fms.dao.FilmDaoImplForList;
import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Language;
import com.flp.service.ActorServiceImpl;
import com.flp.service.FilmServiceImpl;

/**
 * Servlet implementation class CreateFilm
 */
public class CreateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*FilmDaoImplForList filmDaoImpl=new FilmDaoImplForList();
		ActorDaoImplForList actorDaoImpl=new ActorDaoImplForList();
		
		List<Language> lang=filmDaoImpl.getLanguages();
		List<Category> categories=filmDaoImpl.getCategories();
		Set<Actor> actors=actorDaoImpl.getActors();*/
		
		FilmServiceImpl filmServiceImpl=new FilmServiceImpl();
		ActorServiceImpl actorServiceImpl=new ActorServiceImpl();
		
		List<Language> lang=filmServiceImpl.getLanguages();
		List<Category> categories=filmServiceImpl.getCategories();
		Set<Actor> actors=actorServiceImpl.getActors();
		
		
		PrintWriter out=response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>Create Film</title>"
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
				+ "<form method='post' action='AddFilm' name='createfilm'>"
				+ "<table>"
				+ "<h3>Create New Film</h3> "
				
				
				//film title
				+ "<tr>"
				+ "<td>Title</td>"
				+ "<td><input type='text' name='filmTilte' onmouseout='return titleValidation();'>"
				+ "<div id='titleErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				
				//description
				+ "<tr>"
				+ "<td>Description</td>"
				+ "<td><input type='text' name='descrp'></td>"
				+ "</tr>"
				
				
				//release Year
				+ "<tr>"
				+ "<td>Release Year</td>"
				+ "<td><input type='text' name='relDate' id='datepicker1' onmouseout='return releaseYearValidation();'>"
				+ "<div id='relYearErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				
				//original language
				+ "<tr>"
				+ "<td>Select Original Language</td>"
				+ "<td><select name='orgLang'>");
				
				for(Language language:lang){
					out.println("<option value=" +language.getLanguage_Id()+ ">"+language.getName()+"</option>");
				}
				
				out.println(
				"</td></select>"
				
				
				//other languages
				+ "<tr>"
				+ "<td>Select Other Language</td>"
				+ "<td><select name='otherLang' multiple>");
				
				for(Language language:lang){
					out.println("<option value=" +language.getLanguage_Id()+ ">"+language.getName()+"</option>");
				}
				
				out.println(
				"</td></select>");
				
				//actors
				out.println("<tr>"
						+ "<td>Select Actors</td>"
						+ "<td><select name='actors' multiple>");
				
				for(Actor act:actors){
					out.println("<option value=" +act.getActor_Id()+ ">"+act.getFirst_Name()+act.getLast_Name()+"</option>");
				}
				out.println(
						"</td></select>");
					
				
				//rental duration								
				out.println("<tr>"
				+ "<td>Rental Duration</td>"
				+ "<td><input type='text' name='relDur' id='datepicker' onmouseout='return rentalDurationValidation();'>"
				+ "<div id='renDurErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				
				//rating
				+ "<tr>"
				+ "<td>Rating</td>"
				+ "<td><select name='rating'>"
				+ "<option value='1'>1</option>"
				+ "<option value='2'>2</option>"
				+ "<option value='3'>3</option>"
				+ "<option value='4'>4</option>"
				+ "<option value='5'>5</option>"
				+ "</select></td>"
				+ "</tr>"
				
				
				//special features
				+ "<tr>"
				+ "<td>Special Features</td>"
				+ "<td><input type='text' name='spcfeat' onmouseout='return specialFeaturesValidation();'>"
				+ "<div id='specialFeaturesErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				
				//length
				+ "<tr>"
				+ "<td>Length</td>"
				+ "<td><input type='text' name='length' onmouseout='return lengthValidation();'>"
				+ "<div id='lengthErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				
				//replacement cost
				+ "<tr>"
				+ "<td>Replacement Cost:</td>"
				+ "<td> <input type='text' name='cost' onmouseout='return replacementCostValidation();'>"
				+ "<div id='repCostErr' class='errMsg'></div></td>"
				+ "</tr>"
				
				+ "<tr>"
				+ "<td>Select Category</td>"
				+ "<td><select name='category'>");
				
				for(Category category:categories){
					out.println("<option value='"+category.getCategory_Id()+"'>"+category.getName()+"</option>");
				}

		
				out.println("</td></select>"
				
				+ "<tr>"
				+ "<td></td>"
				+ "<td>"
				+ "<input type='submit' value='Save'>"
				+ "<input type='reset' value='Clear'>"
				+ "</td>"
				+ "</tr>"							
				+ "</table>"
				+ "</body>"
				+ "</html>");
	}

}
