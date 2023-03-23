<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>MVRESERVATION - 채팅메인페이지</title>
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
			<h1>chatPage.jsp</h1>
			<h1>로그인아이디 : ${sessionScope.loginId }</h1>

		</div>
		<!-- End Page Title -->
		<hr>
		
		<!-- Content Row -->
		<div class="card mx-auto" style="width: 700px;">
			<div class="card-body">
				<div class="row mb-3 p-2" >
					<div id="chatList" class="col p-4" style="color:black; background-color: lightblue;">
					
						<div style="text-align: left;" class="my-3">
							<span class="p-2 msg" style="background-color: white;">받은 메세지</span>
						</div>
					
						<div style="text-align: right;" class="my-3">
							<span class="p-2 msg" style="background-color: yellow;">보낸메세지</span>
						</div>
					
					</div>
				</div>
			
				<div class="row">
					<div class="col">
						<input class="form-control" type="text" id="msg">
					</div>
					<div class="col-auto">
						<button class="btn btn-primary" 
						onclick="sendMsgTest()">보내기</button>
					</div>
				</div>
				
			</div>
			
		</div>

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

	<script src="${pageContext.request.contextPath }/resources/assets/vendor/tinymce/tinymce.min.js"></script>

	<!-- Template Main JS File -->
	<script
		src="${pageContext.request.contextPath }/resources/assets/js/main.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	
	<script type="text/javascript">

		var sock = new SockJS('${pageContext.request.contextPath }/chatMessage');
		sock.onopen = function() {
			console.log('open');
			//sock.send('test 메세지');
		};

		sock.onmessage = function(e) {
			console.log('받은 메세지', e.data);
			var msgData = JSON.parse(e.data); // json형식으로 형변환
			var output = "";
			if(msgData.type == "notice") {
				console.log("공지");
			output = "<div style=\"text-align: center;\" class=\"my-3\">";
			output += "<span class=\"p-2 msg\" style=\"background-color: white;\">"+msgData.msg+"</span>";
			output += "</div>";
			} else {
				console.log("채팅메세지");
				output = "<div style=\"text-align: left;\" class=\"my-3\">";
				output += "<span class=\"p-2 msg\" style=\"background-color: white;\">"+msgData.msg+"</span>";
				output += "</div>";
			}
			
			$("#chatList").append(output);
			//sock.close();
		};

		sock.onclose = function() {
			console.log('close');
		};
		
		
		function sendMsgTest() {
			var inputValue = document.getElementById('msg').value;
			console.log("보낸 메세지 : " + inputValue);
			sock.send(inputValue);
			var output = "<div class=\"my-3\" style=\"text-align: right;\">";
			output += "<span class=\"p-2 msg\" style=\"background-color: yellow;\" >"+inputValue+"</span>";
			output += "</div>";
			$("#chatList").append(output);
			document.getElementById('msg').value;
		}
		
		
		
	</script>
	
<!-- 	<div style="text-align: right;">
			<span class="p-2 msg" style="background-color: yellow;">보낸메세지 메세지</span>
		</div> -->


</body>

</html>