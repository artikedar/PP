package org.capgemini.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
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
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;

/**
 * Servlet implementation class AddFilm
 */
public class AddFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Film film=new Film();
		
		
		Actor actors=new Actor();
		Category categories=new Category();
		
		
		
		
		String title=request.getParameter("filmTilte");
		film.setTitle(title);
		
		String description=request.getParameter("descrp");
		film.setDescription(description);
		
		String relDate1=request.getParameter("relDate");
		Date dat=new Date(relDate1);
		film.setReleaseYear(dat);
		
		Language lan=new Language();
		lan.setLanguage_Id(Integer.parseInt(request.getParameter("orgLang")));
		film.setOriginalLanguage(lan);
		
		String[] otherLanguages=request.getParameterValues("otherLang");
		List<Language> langList=new ArrayList<>();
		for(String langs:otherLanguages){
			Language langOther=new Language();
			langOther.setLanguage_Id(Integer.parseInt(langs));
			langList.add(langOther);
				                                                                                       
		}
		film.setLanguages(langList);
		
		String[] actorNew=request.getParameterValues("actors");
		Set<Actor> actorList=new HashSet<>();
		for(String actor2:actorNew){
			Actor actor3=new Actor();
			actor3.setActor_Id(Integer.parseInt(actor2));
			actorList.add(actor3);
		}
		film.setActors(actorList);
		
		
		String relDuration=request.getParameter("relDur");
		Date renDat=new Date(relDuration);
		film.setRentalDuration(renDat);
		
	
		String rat=request.getParameter("rating");
		int rating=Integer.parseInt(rat);
		film.setRatings(rating);
		
		String spcFeatures=request.getParameter("spcfeat");
		film.setSpecialFeatures(spcFeatures);
		
		String len=request.getParameter("length");
		int length=Integer.parseInt(len);
		film.setLength(length);
		
		
		String repcost=request.getParameter("cost");
		double replcost=Double.parseDouble(repcost);
		film.setReplacementCost(replcost);
		
		String category=request.getParameter("category");
		Category category2=new Category();
		int category3=Integer.parseInt(category);
		category2.setCategory_Id(category3);
		film.setCategory(category2);
		
		
		FilmDaoImplForList filmDaoImpl=new FilmDaoImplForList();
		filmDaoImpl.addFilm(film);
		System.out.println(film);
		
		
	}

}
