<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>MVRESERVATION - 예매정보 페이지</title>
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
			<h1>ReservationInfo.jsp</h1>
		</div>
		<!-- End Page Title -->
		<c:set var="nowDate" value="<%=new java.util.Date()%>"></c:set>
		<c:forEach items="${reInfoList }" var="reInfo">
			<section class="section">
				<div class="col-10">
					<div class="card">
						<div class="card-body">

							<table>
								<tr>
									<th rowspan="6" class="card-title py-2"><img alt=""
										src="${reInfo.mvpos }" height="200px" width="150px"></th>
									<th style="font-size: large;">&nbsp;${reInfo.mvtitle}</th>

								</tr>
								<tr>
									<th>&nbsp;예매번호 : ${reInfo.recode }</th>
								</tr>
								<tr>
									<th>&nbsp;관람극장 : ${reInfo.thname } ${reInfo.reroom }</th>
								</tr>
								<tr>
									<th>&nbsp;관람일시 : ${reInfo.redate }</th>
								</tr>
								<tr>
									<th>&nbsp;예매인원 : ${reInfo.renumber }</th>
								</tr>
							</table>
							<fmt:parseDate value="${reInfo.redate}"
								pattern="yyyy-MM-dd HH:mm" var="reserveDate"></fmt:parseDate>
							<c:choose>
								<c:when test="${nowDate < reserveDate }">
									<a class="btn btn-light btn-outline-dark" href="${pageContext.request.contextPath }/reCancelPage?selectmovie=${reInfo.mvcode }">예매취소</a>
								</c:when>
								
								<c:otherwise>
								<c:choose>
									<c:when test="${reInfo.rvrecode == null }">
									<a class="btn btn-outline-primary " href="javascript:writeReview('${reInfo.recode }','${reInfo.mvtitle}')">관람평 작성</a>
									</c:when>
									<c:otherwise>
									<a class="btn btn-outline-primary " href="javascript:openReview('${reInfo.recode }','${reInfo.mvtitle}')">관람평 조회</a>
									</c:otherwise>
								</c:choose>
								</c:otherwise>
							</c:choose>
							
							
							
							
		<script type="text/javascript">
			function writeReview(recode,mvtitle) {
				window.open('${pageContext.request.contextPath }/writeReviewForm?recode='+recode+'&mvtitle='+mvtitle,'reviewWriteFormPage',"width=750 , height=400" );
			}
		</script>
		
		<script type="text/javascript">
			function openReview(recode,mvtitle) {
				window.open('${pageContext.request.contextPath }/openReviewForm?recode='+recode+'&mvtitle='+mvtitle,'reviewOpenFormPage',"width=750 , height=400" );
			}
			
		</script>
		
		
		
		<script>
    		var msg = '${redirectMsg}';
    		if(msg === '리뷰가 등록되었습니다.') {
     		   alert("리뷰가 등록되었습니다.");
   			 }
</script>
						</div>
					</div>
				</div>
				
			</section>

		</c:forEach>






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