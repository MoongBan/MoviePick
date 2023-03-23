<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>adminPage.jsp - 관리자페이지</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">

<link
	href="${pageContext.request.contextPath }/resources/assets/img/favicon.png"
	rel="icon">
<link
	href="${pageContext.request.contextPath }/resources/assets/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/quill/quill.snow.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/quill/quill.bubble.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/remixicon/remixicon.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/assets/vendor/simple-datatables/style.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link
	href="${pageContext.request.contextPath }/resources/assets/css/style.css"
	rel="stylesheet">

<!-- =======================================================
  * Template Name: NiceAdmin - v2.4.1
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

	<!-- ======= Header ======= -->
	<%@ include file="/WEB-INF/views/includes/header.jsp"%>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<%@ include file="/WEB-INF/views/includes/sidebar.jsp"%>
	<!-- End Sidebar-->

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>adminPage.jsp</h1>
			<h1>관리자 페이지 입니다.</h1>

		</div>



		<%-- <section class="section">
			<div class="row" style="min-width: 565px;">

				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">회원목록</h5>

							<div class="list-group reserveArea" id="mvListArea">
								<c:forEach items="${memList }" var="member">
									<button type="button"
										class="list-group-item list-group-item-action"
										id="${member.mid }">
										<span class="listTitle font-bold" title="${member.mid }">${member.mid }</span>
									</button>
									<!-- img 태그 src속성의 값 변경 >> mvpos , 영화제목출력 태그 text값을 변경 -->

								</c:forEach>
							</div>

						</div>
					</div>

				</div>
			</div>
		</section> --%>


		<section class="section">

			<span style="background-color:#fff;">회원목록</span>
			<table id="memberList_tbl" style="background-color:#fff;">
				<thead>
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
						<th>생년월일</th>
						<th>주소</th>
						<th>이메일</th>
						<th>회원상태 (0 : 사용중/ 1 : 정지)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${memList }" var="member">
						<tr>
							<td>${member.mid }</td>
							<td>${member.mpw}</td>
							<td>${member.mname }</td>
							<td>${member.mbirth }</td>
							<td>${member.maddr }</td>
							<td>${member.memail }</td>
							<td>${member.mstate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</section>



	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp"%>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script
		src="${pageContext.request.contextPath }/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script
		src="${pageContext.request.contextPath }/resources/assets/vendor/tinymce/tinymce.min.js"></script>

	<!-- Template Main JS File -->
	<script
		src="${pageContext.request.contextPath }/resources/assets/js/main.js"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#memberList_tbl').DataTable();
		});
	</script>
</body>

</html>