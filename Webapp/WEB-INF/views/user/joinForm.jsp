<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="get" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="" value="">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	</div>

</body>
<script type="text/javascript">
$("#btnIdCheck").on("click", function(){
	var id = $("#txtId").val();
	var password = $("#txtPassword").val();
	var name = $("#txtUserName").val();
	var check = $("#chkAgree").is(":checked");
	
	console.log("id:"+id+" password:"+password+" name:"+name + "check:" + check);

	//ajax 데이터만 받기
	$.ajax({
		
		url : "${pageContext.request.contextPath}/user/idcheck",		
		type : "post",
		//contentType : "application/json",

		data : {id: id, 
				password: password,
				name: name,
				check: check}, //url파라미터값만들기

		dataType : "text",
		success : function(result){
			/*성공시 처리해야될 코드 작성*/
			
			if(result == 'empty'){
				console.log("empty")
				$("#tdMsg").html("아이디를 입력해주세요")
			}else if(result == 'can'){
				console.log("can")
				$("#tdMsg").html("사용할 수 있는 아이디 입니다")
			} else{
				console.log("cant")
				$("#tdMsg").html("다른 아이디로 가입해 주세요.")
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});


$("#joinForm").on("submit", function(){
	
	
	var id = $("#txtId").val();
	var password = $("#txtPassword").val();
	var name = $("#txtUserName").val();
	var check = $("#chkAgree").is(":checked");
	var idcheck = $("#tdMsg").html();
	
	console.log("id: "+id+", password: "+password+", name: "+name+", idcheck: "+idcheck);
	
	if(id==null || id==""){
		alert("아이디를 입력해주세요.");
		return false;
	}
	if(password==null || password==""){
		alert("패스워드를 입력해주세요.");
		return false;
	}
	if(name==null || name==""){
		alert("이름을 입력해주세요.");
		return false;
	}
	if(!check){ 
		alert("약관에 동의해주세요");
		return false;
	}
	if(idcheck != "사용할 수 있는 아이디 입니다"){
		alert("아이디 중복확인해주세요");
		return false;
	}
	
	return true;
})

</script>

</html>