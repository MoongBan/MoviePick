<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>MovieInfo - 영화 상세페이지</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
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
			<h1>MovieInfo.jsp</h1>
		</div>
		<!-- End Page Title -->

		<section class="section">
			<div class="col-10">
				<div class="card">
					<div class="card-body">
						<table>
							<tr>
								<th rowspan="6" class="card-title py-2"><img alt=""
									src="${mvInfo.mvpos }" height="200px" width="150px"></th>
								<th style="font-size: large;">&nbsp;${mvInfo.mvtitle }</th>

							</tr>
							<tr>
								<th>&nbsp;감독명 : ${mvInfo.mvdir }</th>
							</tr>
							<tr>
								<th>&nbsp;출연 : ${mvInfo.mvact }</th>
							</tr>
							<tr>
								<th>&nbsp;장르 : ${mvInfo.mvgenre }</th>
							</tr>
							<tr>
								<th>&nbsp;기본정보 : ${mvInfo.mvinfo }</th>
							</tr>
							<tr>
								<th>&nbsp;개봉일 : ${mvInfo.mvdate }</th>
							</tr>
						</table>
						<a class="btn btn-sm btn-danger"
							href="${pageContext.request.contextPath }/ticketPage?selectmovie=${mvInfo.mvcode }">예매하기</a>


					</div>
				</div>
			</div>
		</section>


		<!-- 관람평 출력 -->
		<section class="section">
			<div class="col-10">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-1">관람평</h1>
						</div>
						<hr>


						<div id=replyListArea>
							<div class="form-group row">
								<c:forEach items="${rvInfoList }" var="rvInfo">
									<table>
										<tr>
											<th rowspan="4" class="card-title py-2"><img
												src="${pageContext.request.contextPath }/resources/assets/img/notfoundprofile.png"
												alt="Profile" class="rounded-circle" height="150px"
												width="150px"></th>
											<th style="font-size: large;">${rvInfo.RVMID }</th>
										</tr>
										<tr>
											<th>${rvInfo.RVCOMMENT}</th>
										</tr>
										<tr>
											<th></th>
										</tr>
										<tr>
											<th>${rvInfo.RVDATE }</th>
										</tr>
									</table>
									<hr>
								</c:forEach>

							</div>
						</div>
						<div class="col-12 " style="text-align: center;">
							
							<c:forEach begin="${pageInfo.startPageNum }" end="${pageInfo.endPageNum }" var="pageNum" step="1"> <!-- for문처럼 사용 -->
							<c:choose>
								<c:when test="${pageNum == pageInfo.reviewPage }">
								${pageNum }
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath }/movieInfoPage?selectmovie=${mvInfo.mvcode}&reviewPage=${pageNum}">
								${pageNum }</a>
							</c:otherwise>
							</c:choose>
							</c:forEach>
							
							
						</div>
					</div>
				</div>
			</div>
		</section>


		<!-- 댓글 작성 폼 -->


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

</body>

</html>