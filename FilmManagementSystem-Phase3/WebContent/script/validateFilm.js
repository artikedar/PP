function titleValidation(){
	var filmTilte=createfilm.filmTilte.value;
	var letters = /^[A-Za-z]+$/;
	if(filmTilte.match(letters)){
		document.getElementById("titleErr").innerHTML="";
		return true;
	}
	else{
		document.getElementById("titleErr").innerHTML="*Please Enter a Title Starting with an Alphabet";
		filmTilte.focus();
		return false;
		
	}
}

function rentalDurationValidation(){
	var str=document.getElementById("datepicker1").value;
	var rentDur=document.getElementById("datepicker").value;
	if(str>rentDur){
		document.getElementById("renDurErr").innerHTML="Please Enter A Date Greater than Release Date";
		rentDur.focus();
		return false;
	}
	else{
		document.getElementById("renDurErr").innerHTML="";
		return true;
	}
	
}

function lengthValidation(){
	var length=createfilm.length.value;
	
	if(isNaN(length)||length<1||length>1000){
		document.getElementById("lengthErr").innerHTML="*Please Enter Length between 1 to 1000";
		lengthErr.focus();
		return false;
	}
	else{
		document.getElementById(lengthErr).innerHTML="";
		return true;
	}
}


function specialFeaturesValidation(){
	var spcFetVal=createfilm.decrp.value;
	var letters = /[A-Za-z0-9.,! ]+/;
	if(spcFetVal.match(letters)){
		document.getElementById("specialFeaturesErr").innerHTML="";
		return true;
	}
	else{
		document.getElementById("specialFeaturesErr").innerHTML="*Please Enter Valid Special Features";
		spcFetVal.focus();
		return false;
	}
}

function replacementCostValidation(){
	var repCost=createfilm.cost.value;
	
	if(isNaN(repCost)||repCost==0){
		document.getElementById("repCostErr").innerHTML="*Please Enter a Valid Replacement cost";
		repCostErr.focus();
		return false;
	}
	else{
		document.getElementById("repCostErr").innerHTML="";
		return true;
	}
	
}

function searchValidation(){
/*	var serFilmId=filmSearch.filmId.value;
	var serFilmTit=filmSearch.filmTilte.value;
	var serFilmAct=filmSearch.actor.value;
	var serFilmLang=filmSearch.orgLang.value;
	
	if(serFilmId==""||serFilmId==null){
		document.getElementById("filmIdSerErr").innerHTML="*Please Enter Id";
		flag=false;
		
	
	 if(serFilmTit==""||serFilmTit==null){
		document.getElementById("filmTitSerErr").innerHTML="*Please Enter Title";
		flag=false;
	
	}
	 if(serFilmAct==""||serFilmAct==null){
		document.getElementById("actorSerErr").innerHTML="*Please Select Actor";
		flag=false;
	
	}
	 if(serFilmLang==""||serFilmLang==null){
		document.getElementById("LangSerErr").innerHTML="*Please Select Language";
		flag=false;
	
	}
	}
	
	else{
		document.getElementById("filmIdSerErr"),innerHTML="";
		document.getElementById("filmTitSerErr"),innerHTML="";
		document.getElementById("actorSerErr"),innerHTML="";
		document.getElementById("LangSerErrdErr"),innerHTML="";
	
		
	}
	return flag;*/
	
	alert('Please Enter Any of the Fields');	
}


