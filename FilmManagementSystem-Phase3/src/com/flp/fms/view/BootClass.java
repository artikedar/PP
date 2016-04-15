package com.flp.fms.view;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.flp.fms.domain.Film;
import com.flp.service.ActorServiceImpl;
import com.flp.service.FilmServiceImpl;
import com.flp.service.IActorService;
import com.flp.service.IFilmService;

public class BootClass {
public static void main(String[] args){
		
		
		UserInteraction userInter=new UserInteraction();
		IFilmService filmService=new FilmServiceImpl();
		IActorService actorService=new ActorServiceImpl(); 
		
		int opt;
		int opt1;
		int opt2;
		
		String choice;
		
		
		do{
			menuSelection();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Your Option: ");
			opt=sc.nextInt();
		switch (opt) {
		case 1:
			Film film=userInter.addFilm(filmService.getLanguages(),filmService.getCategories(),actorService.getActors());
			filmService.addFilm(film);
//			System.out.println(film);
			break;
		
		case 3:
			System.out.println("1. Remove Film By Id");
			System.out.println("2. Remove Film By Rating");
			System.out.println("3. Remove Film By Title");
			System.out.println("Enter Your Option: ");
			opt1=sc.nextInt();
			
			switch(opt1) {
			case 1:
				List<Film> filmList31=filmService.getAllFilms();
				Collection<Film> filrem=filmList31.values();  
				userInter.removeFilm(filrem);
				break;
				
			case 2:
				List<Film> filmList32=filmService.getAllFilms();
				Collection<Film> filrem1=filmList32.values();  
				userInter.removeFilmByRating(filrem1);
				break;
				
			case 3:
				List<Film> filmList33=filmService.getAllFilms();
				Collection<Film> filrem2=filmList33.values();  
				userInter.removeFilmByTitle(filrem2);
				break;
				}
				
			break;
	
			
			
			
		case 4:
			System.out.println("1. Search By Film Id");
			System.out.println("2. Search By Film Rating");
			System.out.println("3. Search By Film Title");
			System.out.println("Enter Your Option: ");
			opt2=sc.nextInt();
			
			switch(opt2) {
			
			case 1:
				Map<Integer, Film> filmList3=filmService.getAllFilms();
				Collection<Film> filser=filmList3.values();  
				userInter.searchFilm(filser);
				break;
				
			case 2: 
				Map<Integer, Film> filmList42=filmService.getAllFilms();
				Collection<Film> filser42=filmList42.values();  
				userInter.searchFilmByRating(filser42);
				break;
				
			case 3: 
				Map<Integer, Film> filmList43=filmService.getAllFilms();
				Collection<Film> filser43=filmList43.values();  
				userInter.searchFilmByTitle(filser43);
				break;
			}
			break;
			
		case 5:
			Map<Integer, Film> filmList=filmService.getAllFilms();
			Collection<Film> filmList2=filmList.values();
			userInter.getAllFilm(filmList2);
			break;
			
			}
		System.out.println("Wish to Continue?[y|n]");
		choice=sc.next();
		}while(choice.charAt(0)=='y'||choice.charAt(0)=='Y');
		
	}
	
	
	
	public static void menuSelection(){
		System.out.println("1.Add Film");
		System.out.println("2.Modify Film");
		System.out.println("3.Remove Film");
		System.out.println("4.Search Film");
		System.out.println("5.GetAll Film");
		System.out.println("6.Exit");
		
	}
	
	
	
	
		
		
		
		
		
	
	
}
