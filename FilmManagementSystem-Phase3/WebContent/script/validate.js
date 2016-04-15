function loginvalidation(){
	var flag=true;
	var uname=loginform.uname.value;
	var upwd=loginform.upwd.value;
	
		if(uname==""||uname==null){
			document.getElementById("unameErr").innerHTML="*Please Enter UserName";
			flag=false;
			}
		else
			document.getElementById("unameErr"),innerHTML="";
		
		if(upwd==""||upwd==null){
			document.getElementById("upwdErr").innerHTML="*Please Enter Password";
			flag=false;
			}
		else
			document.getElementById("upwdErr"),innerHTML="";

		return flag;
		
}