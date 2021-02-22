<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">


				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="cateName" name="cateName" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="description" name="description"
							value=""></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	//DOM이 생성되면 
	$("document").ready(function() {
		console.log("ready");
		//리스트 출력
		fetchList();
	});

	$("document").ready(function() {
		console.log("ready");
		//리스트 출력
		fetchList();
	});

	//카테고리 추가
	$("#btnAddCate").on("click", function() {
		var cvo = {
			cateName : $("#cateName").val(),
			description : $("#description").val()
		}

		console.log(cvo);

		//ajax방식으로 요청(저장)
		$.ajax({

			/*
			url : "${pageContext.request.contextPath}/{id}/admin/category",
			type : "post",
			//contentType : "application/json",
			data : guestVo,
			 */

			//json 
			url : "${pageContext.request.contextPath}/{id}/admin/category/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cvo),

			dataType : "json",
			success : function(cvo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(cvo);
				render(cvo); //게스트북 정보 출력

				/* 입력폼 비우기 */
				$("[name='cateName']").val("");
				$("[name='description']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	function render(cvo) {
		var str = "";
		str += '<table id="t-'+ cvo.cateNo +'" id="admin-cate-list">';
		str += '	<colgroup>';
		str += '		<col style="width: 50px;">';
		str += '		<col style="width: 200px;">';
		str += '		<col style="width: 100px;">';
		str += '		<col>';
		str += '		<col style="width: 50px;">';
		str += '	</colgroup>';
		str += '  <thead>';
		str += '	<tr>';
		str += '		<th>번호</th>';
		str += '		<th>카테고리명</th>';
		str += '		<th>포스트 수</th>';
		str += '		<th>설명</th>';
		str += '		<th>삭제</th>    ';
		str += '  	</tr>';
		str += '  </thead>';
		str += '<tbody id="cateList">';
		str += '	<tr>';
		str += '		<td>' + cvo.cateNo + '</td>';
		str += '		<td>' + cvo.cateName + '</td>';
		str += '		<td>count</td>';
		str += '		<td>' + cvo.description + '</td>';
		str += '		<td class="text-center">';
		str += '		<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '		</td>';
		str += '	</tr>';
		str += '</tbody>';
		str += '</table>';

		("#admin-content").append(str);

	}

	//전체리스트 출력
	function fetchList() {

		$.ajax({

			url : "${pageContext.request.contextPath}/{id}/admin/category",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},
			dataType : "json",
			success : function(cList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(cList);
				for (var i = 0; i < cList.length; i++) {
					render(cList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
</script>



</html>