package com.flp.fms.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.flp.fms.domain.Language;

public class Validate {
	
	public static boolean isValidTitle(String title){
		return title.matches("[A-Za-z!, ]+");
		}
	public static boolean isValidDate(String myDate){
		return myDate.matches("[0123]\\d{1}-(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)-[12][890]\\d{2}");
	}
	public static boolean isValidLength(int length){
		if(length>0&&length<1000)
		return true;
		else 
			return false;
	}
	public static boolean isValidRating(int rating){
		if(rating>0&&rating<=5)
			return true;
		else
			return false;
	}
	public static boolean checkDuplicateLanguage(List<Language> languages, Language language) {
		boolean flag=false;
		Iterator<Language> itr=languages.iterator();
		if(languages.isEmpty())
		{
			flag=false;
		}else{
		while(itr.hasNext()){
			Language language2=itr.next();
			if(language.equals(language2)){
				flag=true;
				break;
			}
			
		}
		}
		return flag;
	}
	
	
	
	
	
}