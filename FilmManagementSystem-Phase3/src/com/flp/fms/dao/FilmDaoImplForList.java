package com.flp.fms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;

import sun.launcher.resources.launcher;

public class FilmDaoImplForList implements IFilmDao{
	
/*	private Map<Integer, Film> film_Repository=new HashMap<>();*/
	private List<Film> film_Repository=new ArrayList<>();
	
	
	public Connection getConnection(){
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/filmdb", "root", "Pass1234");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@Override
	public List<Language> getLanguages() {
			List<Language> languages=new ArrayList<>();
			Connection con=getConnection();
			String sql="SELECT * FROM languages";
						
			try {
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				/*Statement stm=con.createStatement();
				 ResultSet rs=stm.executeQuery(sql);*/
				 while(rs.next())
				 {
				Language language=new Language();
				language.setLanguage_Id(rs.getInt(1));
				language.setName(rs.getString(2));
				
				languages.add(language);
				 }
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			
			
			/*languages.add(new Language(1, "English"));
			languages.add(new Language(2, "Hindi"));
			languages.add(new Language(3, "Marathi"));
			languages.add(new Language(4, "Tamil"));
			languages.add(new Language(5, "Telegu"));
		*/
			return languages;
			}

	@Override
	public List<Category> getCategories() {
		List<Category> categories=new ArrayList<>();
		Connection con=getConnection();
		String sql="SELECT * FROM category";
		try {
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next())
			 {
			Category category=new Category();
			category.setCategory_Id(rs.getInt(1));
			category.setName(rs.getString(2));
			categories.add(category);
			 }
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		/*categories.add(new Category(1, "Horror"));
		categories.add(new Category(2, "Comedy"));
		categories.add(new Category(3, "Romance"));
		categories.add(new Category(4, "Drama"));
		categories.add(new Category(5, "Action"));
		categories.add(new Category(6, "Documentry"));*/
		
		return categories;
	}

	@Override
	public void addFilm(Film film) {
		Connection con=getConnection();
		String sql="insert into film(title,description,releaseYear,originalLanguage,rentalduration,length,replacementCost,rating,specialFeatures,category)"+"values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, film.getTitle());
			pst.setString(2,film.getDescription());
			pst.setDate(3,new Date(film.getReleaseYear().getTime()));
			pst.setInt(4,(film.getOriginalLanguage()).getLanguage_Id());
			pst.setDate(5, new Date(film.getRentalDuration().getTime()));
			pst.setInt(6, film.getLength());
			pst.setDouble(7, film.getReplacementCost());
			pst.setInt(8, film.getRatings());
			pst.setString(9, film.getSpecialFeatures());
			pst.setInt(10, film.getCategory().getCategory_Id());
			
			int count=pst.executeUpdate();
			
			System.out.println(count);
			
			if(count>0){
				//insert into third party tables
				
				//Getting Film Id
				int filmId=0;
				String sqlFilmId="select * from film order by filmId desc limit 1";
				PreparedStatement pst2=con.prepareStatement(sqlFilmId);
				ResultSet rs2=pst2.executeQuery();
				while(rs2.next()){
					filmId=rs2.getInt(1);
				}
				
				//Inserting into Actor table
				String sqlActorAdd="insert into film_actors(filmId,actorId) values(?,?)";
				pst=con.prepareStatement(sqlActorAdd);
				
				Set<Actor> actors=film.getActors();
				for(Actor act:actors){
					pst.setInt(1, filmId);
					pst.setInt(2, act.getActor_Id());
					
					count=pst.executeUpdate();
				}
				
				//Inserting into Languages table
				String sqllanguageAdd="insert into film_languages(filmId,languageId) values(?,?)";
				pst=con.prepareStatement(sqllanguageAdd);
				
				List<Language> languages=film.getLanguages();
				for(Language lang:languages){
					pst.setInt(1, filmId);
					pst.setInt(2, lang.getLanguage_Id());
					
					count=pst.executeUpdate();
					
				}
						
			}
							
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	/*	film_Repository.put(film.getFilm_ID(), film);*/
		}

	@Override
	public List<Film> getAllFilms() {
		List<Film> films=new ArrayList<>();
		Connection con=getConnection();
		String sqlGetFilm="select * from film";
		try {
			PreparedStatement pst4=con.prepareStatement(sqlGetFilm);
			ResultSet rs=pst4.executeQuery();
			
			/*Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sqlGetFilm);*/
			
			while(rs.next()){
				Film film1=new Film();
				
				//Get FilmId
				film1.setFilm_ID(rs.getInt(1));
				
				//Get Film Title
				film1.setTitle(rs.getString(2));
				
				//Get Film Description
				film1.setDescription(rs.getString(3));
				
				//Get Release Year
				film1.setReleaseYear(rs.getDate(4).valueOf(rs.getDate(4).toString()));
				
				//Get Original Language
				/*int orgLangId=rs.getInt(5);
				Language lang=getLanguages(orgLangId);*/
				
				//Get Rental Duration
				film1.setRentalDuration(rs.getDate(6).valueOf(rs.getDate(6).toString()));
				
				//Get Length
				film1.setLength(rs.getInt(7));
				
				//Get Replacement Cost
				film1.setReplacementCost(rs.getDouble(8));
				
				//Get Ratings
				film1.setRatings(rs.getInt(9));
			
				//Get special Features
				film1.setSpecialFeatures(rs.getString(10));
				
				//Get Category
				int categoryId=rs.getInt(11);
				Category category=getCategory(categoryId);
				film1.setCategory(category);
				//films.add(film1);
				
				
				
				
				/*film1.setCategory(rs.getString(11));*/
				
				//Get List of Actors
				Set<Actor> actors=new HashSet<>();
				List<Integer> actorId=getActorIds(film1.getFilm_ID());
				for(Integer actor:actorId){
					actors.add(getActors(actor));
				}
				film1.setActors(actors);
				//films.add(film1);
				
				//Get Original Language
				int langID=rs.getInt(5);
				Language lang=getOriginalLanguage(langID);
				film1.setOriginalLanguage(lang);
				//films.add(film1);
				
				
				
				//Get List Other LAnguages
				List<Language> otherLangName=new ArrayList<>();
				List<Integer> otherLangId=getLanguageId(film1.getFilm_ID());
				for(Integer langId:otherLangId){
					
					otherLangName.add(getOriginalLanguage(langId));
				}
				film1.setLanguages(otherLangName);
				films.add(film1);
				
				System.out.println(films);
				
				
			
				
				/*String sqlCategoryAdd="select * from category where            "
				*/
				
				
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return films;
	}
	
	
	//Method to Get the first name and Last Name of Actors
	private Actor getActors(int actor){
		Actor act=new Actor();
		Connection con=getConnection();
		String sqlGetActors="select * from actors where actorId=?";
		
		try {
			PreparedStatement pst=con.prepareStatement(sqlGetActors);
			pst.setInt(1, actor);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				act.setFirst_Name(rs.getString(2));
				act.setLast_Name(rs.getString(3));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return act;
		
	}
	
	
	//Method to Get the IDs of Actors
	private List<Integer> getActorIds(int filmId1){
		List<Integer> actorIds=new ArrayList<>();
		Connection con=getConnection();
		String sqlActorIds="select * from film_actors where filmId=?";
		try {
			PreparedStatement pst=con.prepareStatement(sqlActorIds);
			pst.setInt(1, filmId1);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				actorIds.add(rs.getInt(2));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return actorIds;
		
	}
	
	//Method to Get Language Ids
	private List<Integer> getLanguageId(int filmId1){
		List<Integer> langId=new ArrayList<>();
		Connection con=getConnection();
		String sqlLangId="select * from film_languages where filmId=?";
		
		try {
			PreparedStatement pst=con.prepareStatement(sqlLangId);
			pst.setInt(1, filmId1);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				langId.add(rs.getInt(2));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return langId;
		
		
	}
	
	//Method to GetLanguages
	private Language getOriginalLanguage(int langId){
		Language lang1=new Language();
		Connection con=getConnection();
		String sqlOrgLang="select * from languages where LangId=?";
		
		try {
			PreparedStatement pst=con.prepareStatement(sqlOrgLang);
			pst.setInt(1, langId);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				lang1.setName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lang1;
		
	}

	//Method to get Category
	private Category getCategory(int categoryID){
		Category cat=new Category();
		Connection con=getConnection();
		String sqlGetCategory="select * from category where categoryId=?";
		try {
			PreparedStatement pst=con.prepareStatement(sqlGetCategory);
			pst.setInt(1, categoryID);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				cat.setName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cat;
		
	}

	@Override
	public int removeFilm(int id) {
		int count=0;
		System.out.println(id);
		Connection con=getConnection();
		String sqlDeleteFromFilm="delete from film where filmId=?";
		String sqlDeleteFromFilmLang="delete from film_languages where filmId=?";
		String sqlDeleteFromFilmAct="delete from film_actors where filmId=?";
		
		try {
			PreparedStatement pst=con.prepareStatement(sqlDeleteFromFilm);
			pst.setInt(1, id);
			count=pst.executeUpdate();
			
			PreparedStatement pst2=con.prepareStatement(sqlDeleteFromFilmLang);
			pst.setInt(1, id);
			count=pst.executeUpdate();
			
			PreparedStatement pst3=con.prepareStatement(sqlDeleteFromFilmAct);
			pst.setInt(1, id);
			count=pst.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return count;
	}

	@Override
	public List<Film> searchFilm(int id, String title, String actor,String language) {
		List<Film> resFilms=new ArrayList<>();
		Connection
		con=getConnection();
		
		//8-only id is entered
		if((id!=0)&&((title.equals(""))&&(actor.equals(""))&&(language.equals("")))){
			String sqlSerById="select * from film where filmId=?";
			
			try {
				PreparedStatement pst8=con.prepareStatement(sqlSerById);
				pst8.setInt(1, id);
				ResultSet rs=pst8.executeQuery();
				
				while(rs.next()){
					Film film1=new Film();
					film1.setFilm_ID(rs.getInt(1));
					film1.setTitle(rs.getString(2));
					film1.setDescription(rs.getString(3));
					film1.setReleaseYear(rs.getDate(4).valueOf(rs.getDate(4).toString()));
					film1.setRentalDuration(rs.getDate(6).valueOf(rs.getDate(6).toString()));
					film1.setLength(rs.getInt(7));
					film1.setReplacementCost(rs.getDouble(8));
					film1.setRatings(rs.getInt(9));
					film1.setSpecialFeatures(rs.getString(10));
					int categoryId=rs.getInt(11);
					Category category=getCategory(categoryId);
					film1.setCategory(category);
					Set<Actor> actors=new HashSet<>();
					List<Integer> actorId=getActorIds(film1.getFilm_ID());
					for(Integer actor2:actorId){
						actors.add(getActors(actor2));
					}
					film1.setActors(actors);
					int langID=rs.getInt(5);
					Language lang=getOriginalLanguage(langID);
					film1.setOriginalLanguage(lang);
					//Get List Other LAnguages
					List<Language> otherLangName=new ArrayList<>();
					List<Integer> otherLangId=getLanguageId(film1.getFilm_ID());
					for(Integer langId:otherLangId){
						
						otherLangName.add(getOriginalLanguage(langId));
					}
					film1.setLanguages(otherLangName);
					resFilms.add(film1);
					
					System.out.println(resFilms);
							
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		//4- only title is entered
				else if((title!=null)&&((id==0)&&(actor.equals(""))&&(language.equals("")))){
					System.out.println("Film Found");
					String sqlSerByTitl="select * from film where title=?";
					
					try {
						PreparedStatement pst4=con.prepareStatement(sqlSerByTitl);
						pst4.setString(2, title);
						ResultSet rs=pst4.executeQuery();
						while(rs.next()){
							Film film1=new Film();
							film1.setFilm_ID(rs.getInt(1));
							film1.setTitle(rs.getString(2));
							film1.setDescription(rs.getString(3));
							film1.setReleaseYear(rs.getDate(4).valueOf(rs.getDate(4).toString()));
							film1.setRentalDuration(rs.getDate(6).valueOf(rs.getDate(6).toString()));
							film1.setLength(rs.getInt(7));
							film1.setReplacementCost(rs.getDouble(8));
							film1.setRatings(rs.getInt(9));
							film1.setSpecialFeatures(rs.getString(10));
							int categoryId=rs.getInt(11);
							Category category=getCategory(categoryId);
							film1.setCategory(category);
							Set<Actor> actors=new HashSet<>();
							List<Integer> actorId=getActorIds(film1.getFilm_ID());
							for(Integer actor2:actorId){
								actors.add(getActors(actor2));
							}
							film1.setActors(actors);
							int langID=rs.getInt(5);
							Language lang=getOriginalLanguage(langID);
							film1.setOriginalLanguage(lang);
							//Get List Other LAnguages
							List<Language> otherLangName=new ArrayList<>();
							List<Integer> otherLangId=getLanguageId(film1.getFilm_ID());
							for(Integer langId:otherLangId){
								
								otherLangName.add(getOriginalLanguage(langId));
							}
							film1.setLanguages(otherLangName);
							resFilms.add(film1);
							
							System.out.println(resFilms);
									
						}
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
				}
				
		
		
		//12-id & title is entered		
		else if(((id!=0)&&(title!=null))&&((actor.equals(""))&&(language.equals("")))){
			String sqlSerByIdnTitl="select * from film where filmId=? and title=?";
			
			try {
				PreparedStatement pst12=con.prepareStatement(sqlSerByIdnTitl);
				pst12.setString(2, title);
				pst12.setInt(1, id);
				ResultSet rs=pst12.executeQuery();
				
				while(rs.next()){
					Film film1=new Film();
					film1.setFilm_ID(rs.getInt(1));
					film1.setTitle(rs.getString(2));
					film1.setDescription(rs.getString(3));
					film1.setReleaseYear(rs.getDate(4).valueOf(rs.getDate(4).toString()));
					film1.setRentalDuration(rs.getDate(6).valueOf(rs.getDate(6).toString()));
					film1.setLength(rs.getInt(7));
					film1.setReplacementCost(rs.getDouble(8));
					film1.setRatings(rs.getInt(9));
					film1.setSpecialFeatures(rs.getString(10));
					int categoryId=rs.getInt(11);
					Category category=getCategory(categoryId);
					film1.setCategory(category);
					Set<Actor> actors=new HashSet<>();
					List<Integer> actorId=getActorIds(film1.getFilm_ID());
					for(Integer actor2:actorId){
						actors.add(getActors(actor2));
					}
					film1.setActors(actors);
					int langID=rs.getInt(5);
					Language lang=getOriginalLanguage(langID);
					film1.setOriginalLanguage(lang);
					//Get List Other LAnguages
					List<Language> otherLangName=new ArrayList<>();
					List<Integer> otherLangId=getLanguageId(film1.getFilm_ID());
					for(Integer langId:otherLangId){
						
						otherLangName.add(getOriginalLanguage(langId));
					}
					film1.setLanguages(otherLangName);
					resFilms.add(film1);
					
					System.out.println(resFilms);
							
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		//14-id, title & actor is entered
		else if(!((id==0)&&(title.equals(""))&&(actor.equals("")))&&(language.equals(""))){
			
		}
		
		//15-all fields are entered
		else if(!((id==0)&&(title.equals(""))&&(actor.equals(""))&&(language.equals("")))){
			
		}
		
		//10-id & actor is entered
		else if(!((id==0)&&(actor.equals("")))&&((title.equals(""))&&(language.equals("")))){
			
		}
		
		//9- id & language is entered
		else if(!((id==0)&&(language.equals("")))&&((title.equals(""))&&(actor.equals("")))){
			
		}
		
		//6- title & actor is entered
		else if(!((title.equals(""))&&(actor.equals("")))&&((id==0)&&(language.equals("")))){
			
		}
		
		//5- title & language is entered
		else if(!((title.equals(""))&&(language.equals("")))&&((id==0)&&(actor.equals("")))){
			
		}
		
		//1- only language is entered
		else if((language!=null)&&((id==0)&&(title==null)&&(actor==null))){
			String sqlSerByLang="select * from film where (originalLanguage=(select LangId from languages where Lang=?))";
			
			try {
				PreparedStatement pst1=con.prepareStatement(sqlSerByLang);
				pst1.setString(1, language);
				ResultSet rs=pst1.executeQuery();
				
				while(rs.next()){
					Film film1=new Film();
					film1.setFilm_ID(rs.getInt(1));
					film1.setTitle(rs.getString(2));
					film1.setDescription(rs.getString(3));
					film1.setReleaseYear(rs.getDate(4).valueOf(rs.getDate(4).toString()));
					film1.setRentalDuration(rs.getDate(6).valueOf(rs.getDate(6).toString()));
					film1.setLength(rs.getInt(7));
					film1.setReplacementCost(rs.getDouble(8));
					film1.setRatings(rs.getInt(9));
					film1.setSpecialFeatures(rs.getString(10));
					int categoryId=rs.getInt(11);
					Category category=getCategory(categoryId);
					film1.setCategory(category);
					Set<Actor> actors=new HashSet<>();
					List<Integer> actorId=getActorIds(film1.getFilm_ID());
					for(Integer actor2:actorId){
						actors.add(getActors(actor2));
					}
					film1.setActors(actors);
					int langID=rs.getInt(5);
					Language lang=getOriginalLanguage(langID);
					film1.setOriginalLanguage(lang);
					//Get List Other LAnguages
					List<Language> otherLangName=new ArrayList<>();
					List<Integer> otherLangId=getLanguageId(film1.getFilm_ID());
					for(Integer langId:otherLangId){
						
						otherLangName.add(getOriginalLanguage(langId));
					}
					film1.setLanguages(otherLangName);
					resFilms.add(film1);
					
					System.out.println(resFilms);
							
				}
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		//2- only actor is entered
		else if(!(actor.equals(""))&&((id==0)&&(title.equals(""))&&(language.equals("")))){
			
		}
		
		//3- actor & language is entered
		else if(!((actor.equals(""))&&(language.equals("")))&&((id==0)&&(title.equals("")))){
			
		}
		
		
		//7- title, actor & language is entered
		else if(!((title.equals(""))&&(actor.equals(""))&&(language.equals("")))&&(id==0)){
			
		}
		
		//11- id, actor and language is entered
		else if(!((id==0)&&(actor.equals(""))&&(language.equals("")))&&(title.equals(""))){
			
		}
		
		//13 id, title & language is entered
		else if(((id!=0)&&(title!=null)&&(language!=null))&&(actor==null)){
			String sqlSerByLang="select * from film where (originalLanguage=(select LangId from languages where LangId=?))";
			
			try {
				PreparedStatement pst1=con.prepareStatement(sqlSerByLang);
				pst1.setString(1, language);
				ResultSet rs=pst1.executeQuery();
				
				while(rs.next()){
					Film film1=new Film();
					film1.setFilm_ID(rs.getInt(1));
					film1.setTitle(rs.getString(2));
					film1.setDescription(rs.getString(3));
					film1.setReleaseYear(rs.getDate(4).valueOf(rs.getDate(4).toString()));
					film1.setRentalDuration(rs.getDate(6).valueOf(rs.getDate(6).toString()));
					film1.setLength(rs.getInt(7));
					film1.setReplacementCost(rs.getDouble(8));
					film1.setRatings(rs.getInt(9));
					film1.setSpecialFeatures(rs.getString(10));
					int categoryId=rs.getInt(11);
					Category category=getCategory(categoryId);
					film1.setCategory(category);
					Set<Actor> actors=new HashSet<>();
					List<Integer> actorId=getActorIds(film1.getFilm_ID());
					for(Integer actor2:actorId){
						actors.add(getActors(actor2));
					}
					film1.setActors(actors);
					int langID=rs.getInt(5);
					Language lang=getOriginalLanguage(langID);
					film1.setOriginalLanguage(lang);
					//Get List Other LAnguages
					List<Language> otherLangName=new ArrayList<>();
					List<Integer> otherLangId=getLanguageId(film1.getFilm_ID());
					for(Integer langId:otherLangId){
						
						otherLangName.add(getOriginalLanguage(langId));
					}
					film1.setLanguages(otherLangName);
					resFilms.add(film1);
					
					System.out.println(resFilms);
							
				}
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		/*String sqlSerById="select * from film where filmId=?";
		String sqlSerByActor="select * from film where film_actors IN(select * from )filmId=?";
		String sqlSerByTitle="select * from film where title=?";
		String sqlSerByLanguage="select * from film_languages where filmId=?";*/
		
		
		
		
		return resFilms;
	}
	
	
	
	
	

	
	


	
	
	
	}

	

