package com.flp.fms.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.util.Validate;

public class UserInteraction {
	Scanner sc=new Scanner(System.in);
	String title;
	String releaseDate;
	String rentalDuration;
	String specialFeatures;
	int length;
	int rating;
	Date releaseDate1;
	Date rentalDuration1;
	boolean flag=false;
	boolean flag1=false;
	ArrayList<String> list=new ArrayList<>();

	//return fully qualified film object
	public Film addFilm(List<Language> languages, List<Category> categories, Set<Actor> actors){
		Film film=new Film();
		
		//Getting Title and checking if it is valid		
		do{
		System.out.println("Enter Title: ");
		title=sc.nextLine();
		flag=Validate.isValidTitle(title);
		if(!flag)
			System.out.println("Please Enter a Valid Title.");
		}while(!flag);
		film.setTitle(title);
		
		//Getting Release date and checking if it is valid
		do{
		do{
		System.out.println("Enter Release Date: ");
		releaseDate=sc.next();
		flag=Validate.isValidDate(releaseDate);
		if(!flag)
			System.out.println("Please Enter Date in this format-(dd-MMM-yyyy!)");
		}while(!flag);
		releaseDate1=new Date(releaseDate);
		Date today=new Date();
		if(releaseDate1.before(today)||releaseDate1.equals(today))
			flag1=true;
		if(!flag1)
			System.out.println("Date should be Past Date or Current Date");
		}while(!flag1);
		film.setReleaseYear(releaseDate1);
		
		//Getting length and checking if it is valid
		do{
		System.out.println("Enter Length: ");
		int length=sc.nextInt();
		flag=Validate.isValidLength(length);
		if(!flag)
		System.out.println("Length must be between 0-1000 mins");
		}while(!flag);
		film.setLength(length);
		
		//Getting rental duration and checking if it is valid
		do{
		do{
		System.out.println("Enter Rental Duration: ");
		rentalDuration=sc.next();
		flag=Validate.isValidDate(rentalDuration);
		if(!flag)
			System.out.println("Please Enter Date in this format-(dd-MMM-yyyy!)");
		}while(!flag);
		rentalDuration1=new Date(rentalDuration);
		Date today=new Date();
		if(rentalDuration1.after(releaseDate1))
			flag1=true;
		if(!flag1)
			System.out.println("Date should be Future Date");
		}while(!flag1);
			
		//Getting Rating and checking if it is valid
		do{
		System.out.println("Enter Rating [1-5]: ");
		rating=sc.nextInt();
		flag=Validate.isValidRating(rating);
		if(!flag)
		System.out.println("Please Enter rating betweeen 1-5");
		}while(!flag);
		film.setRatings(rating);
		
		//Choose Original Language
		System.out.println("Choose Original Language:");
		Language language= selectLanguage(languages);
		film.setOriginalLanguage(language);
		
		//Select All Other Languages in which the Film is Released
		List<Language> language1=new ArrayList<>();
		String choice;
		boolean flag_lang=false;
		do{
		System.out.println("Enter Other Languages in which the Film is released:");
		Language languages2= selectLanguage(languages);
		
		
		flag_lang=Validate.checkDuplicateLanguage(language1,languages2);
		
		if(!flag_lang){
			language1.add(languages2);
		}
		else{
			System.out.println("Language Exists. Please Enter other Language");
		}
		System.out.println("wish to add more languages?[y|n]");
		choice=sc.next();
		}while(choice.charAt(0)=='y'||choice.charAt(0)=='Y');
		film.setLanguages(language1);
		
		//Choose Category
				System.out.println("Choose Category:");
				Category category= selectCategory(categories);
				film.setCategory(category);
				
		
		//Add All Actors
				Set<Actor> actors2=new HashSet<>();
				do{
				System.out.println("Choose All Acotrs for the Film: ");
				Actor actor=addActor(actors);
				actors2.add(actor);
				
				System.out.println("Wish to add more actors?[y|n]");
				choice=sc.next();
				}while(choice.charAt(0)=='y'||choice.charAt(0)=='Y');
				film.setActors(actors2);
				
		//Get Replacement Cost
				Double d;
				System.out.println("Enter Replacement Cost: ");
				d=sc.nextDouble();
				film.setReplacementCost(d);
				
		//Get Description
				String sNew;
				System.out.println("Enter Description: ");
				sNew=sc.next();
				film.setDescription(sNew);;
				
		//Get Special Features
				String sNew2;
				System.out.println("Enter Special Features: ");
				sNew2=sc.next();
				film.setSpecialFeatures(sNew2);
						
		return film;		
	}

	//Choose valid Category from list of Categories
	public Category selectCategory(List<Category> categories) {
		Category sel_Category=null;
		boolean flag;
		//Print Category Details
		do{
			for(Category category:categories)
				System.out.println(category.getCategory_Id()+"\t"+category.getName());
			System.out.println("Choose Category Id:");
			int option=sc.nextInt();
			flag=false;
			//Check for language object
			for(Category category:categories){
				if(option==category.getCategory_Id()){
					sel_Category=category;
					flag=true;
					break;
					}
				}
			//Print Error message
			if(!flag)
				System.out.println("Invalid Id. Please select valid Category Id ");
		}while(!flag);
		return sel_Category;
		}
	
	
	//Choose valid language from the list of languages
		public Language selectLanguage(List<Language> languages) {
			Language sel_Language=null;
			boolean flag;
			//Print Language Details
			do{
				for(Language language:languages)
					System.out.println(language.getLanguage_Id()+"\t"+language.getName());
				System.out.println("Choose Language Id:");
				int option=sc.nextInt();
				flag=false;
				//Check Language object
				for(Language language:languages){
					if(option==language.getLanguage_Id()){
						sel_Language=language;
						flag=true;
						break;
						}
					}
				//Print Error message
				if(!flag)
					System.out.println("Invalid Id. Please select valid Language Id ");
			}while(!flag);
			return sel_Language;
		}
		
		//Choose valid object from the list of Actors
		public Actor addActor(Set<Actor> actors) {
			Actor sel_Actor=null;
			boolean flag=false;
			do{
				for(Actor actor:actors)
					System.out.println(actor.getActor_Id()+"\t"+actor.getFirst_Name()+"\t"+actor.getLast_Name());
				System.out.println("Choose Actor");
				int option=sc.nextInt();
				flag=false;
				//Check Actor Object
				for(Actor actor: actors){
					if(option==actor.getActor_Id()){
						sel_Actor=actor;
						flag=true;
						break;
					}
				}
				//Print Error Message
				if(!flag)
					System.out.println("Please Select Valid Actor ID");
				
			}while(!flag);
			return sel_Actor;
		}
		

		//Printing all the film attributes
		public void getAllFilm(Collection<Film> filmList2) {
			for(Film film:filmList2){
				String s=" ";
				String s2=" ";
				for(Language lang:film.getLanguages()){
					s=s+lang.getName()+" ";
				}
				for(Actor act:film.getActors()){
					s2=s2+act.getFirst_Name()+" "+act.getLast_Name()+" ";
				}
				System.out.println("Film ID: "+film.getFilm_ID()+"\tFilm Title: "+film.getTitle()+"\tOriginal Language: "+film.getOriginalLanguage().getName()+"\tFilm Rating: "+film.getRatings()+"\tReplacement Cost: "+film.getReplacementCost()+"\tCategory: "+film.getCategory().getName()+"\tDescription: "+film.getDescription()+"\tRelease Year: "+film.getReleaseYear()+"\tSpecial Features: "+film.getSpecialFeatures()+"\tList of Languages: "+s+"\tList of Actors: "+s2);	
			}
		}
		
		//Search Film By Id
		public void searchFilm(Collection<Film> filser){
			System.out.println("Enter Film Id to be Searched: ");
			int serId=sc.nextInt();
			boolean flag=false;
			if(!(((Collection<Film>)filser).isEmpty())){
				for(Film film:filser){
					if(serId==film.getFilm_ID()){
						System.out.println(film);
						flag=true;
					}
					else{ 
						flag=false;
						System.out.println("Film Not Found");
					}
				}
			}
			else
				System.out.println("Repository is Empty");
		}
			
			
			
			
		//Remove Film by Id
		public void removeFilm(Collection<Film> filrem){
			System.out.println("Enter Film Id to Remove the film: ");
			int remId=sc.nextInt();
			boolean flag=false;
			for(Film film:filrem){
				if(remId==film.getFilm_ID()){
					filrem.remove(film);
					break;
				}
				flag=true;
				}
				if(flag=false)
					System.out.println("Film Not Found");
		}
			


		//Search Film by Rating
		public void searchFilmByRating(Collection<Film> filserRat){
			System.out.println("Enter Film Rating to be Searched: ");
			int serRat=sc.nextInt();
			boolean flag=false;
			if(!(((Collection<Film>)filserRat).isEmpty())){
				for(Film film:filserRat){
					if(serRat==film.getRatings()){
						System.out.println(film);
						flag=true;
					}
					else{ 
						flag=false;
						System.out.println("Film Not Found");
					}
				}
			}
			else
				System.out.println("repository is empty");
		}


		//Remove Film By Rating
		public void removeFilmByRating(Collection<Film> filremRat){
			System.out.println("Enter Film Rating to Remove the film: ");
			int remRat=sc.nextInt();
			boolean flag=false;
			for(Film film:filremRat){
				if(remRat==film.getRatings()){
					filremRat.remove(film);
					break;
				}
				flag=true;
			}
			if(flag=false)
				System.out.println("Film Not Found");
		}

		//Search Film by Title
		public void searchFilmByTitle(Collection<Film> filserTitl){
			System.out.println("Enter Film Title to be Searched: ");
			String serTitl=sc.next();
			boolean flag=false;
			if(!(((Collection<Film>)filserTitl).isEmpty())){
				for(Film film:filserTitl){
					if(serTitl.equals(film.getTitle())){
						System.out.println(film);
						flag=true;
						}
					else{ 
						flag=false;
						System.out.println("Film Not Found");
					}
				}
			}
			else
				System.out.println("repository is empty");
		}



		//Remove Film By Title
		public void removeFilmByTitle(Collection<Film> filremTitl){
			System.out.println("Enter Film Title to Remove the film: ");
			String remTitl=sc.next();
			boolean flag=false;
			for(Film film:filremTitl){
				if(remTitl.equals(film.getTitle())){
					filremTitl.remove(film);
					break;
				}
				flag=true;
			}
			if(flag=false)
				System.out.println("Film Not Found");
		}

	}
		
	
	
	

