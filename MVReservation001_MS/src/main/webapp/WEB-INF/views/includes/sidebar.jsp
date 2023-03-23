<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">

		<li class="nav-item"><a class="nav-link "
			href="${pageContext.request.contextPath }/"> <i
				class="bi bi-grid"></i> <span>메인페이지</span>
		</a></li>
		<!-- End Dashboard Nav -->

		<li class="nav-heading">Movies</li>

		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#movies-nav" data-bs-toggle="collapse" href="#">
				<i class="bi bi-menu-button-wide"></i><span>영화메뉴</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="movies-nav" class="nav-content collapse "
				data-bs-parent="#sidebar-nav">
				<li><a href="${pageContext.request.contextPath }/moviePage">
						<i class="bi bi-circle"></i><span>영화</span>
				</a></li>
				<li><a href="${pageContext.request.contextPath }/theaterPage">
						<i class="bi bi-circle"></i><span>극장</span>
				</a></li>
				<li><a href="${pageContext.request.contextPath }/ticketPage">
						<i class="bi bi-circle"></i><span>예매</span>
				</a></li>

			</ul></li>
		<!-- End movies-nav -->

		<li class="nav-heading">Members</li>

		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#members-nav" data-bs-toggle="collapse" href="#">
				<i class="bi bi-journal-text"></i><span>회원메뉴</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="members-nav" class="nav-content collapse "
				data-bs-parent="#sidebar-nav">


				<c:choose>
					<c:when test="${sessionScope.loginId == null }">
						<%-- 로그인이 되어 있지 않을 경우 --%>
						<a class="collapse-item"
							href="${pageContext.request.contextPath }/memberJoinForm">회원가입</a>
						<a class="collapse-item"
							href="${pageContext.request.contextPath }/memberLoginForm">로그인</a>
					</c:when>

					<c:otherwise>
						<%-- 로그인이 되어 있을 경우 --%>
						<a class="collapse-item"
							href="${pageContext.request.contextPath }/reservationInfo">예매정보
							확인</a>
						<a class="collapse-item"
							href="${pageContext.request.contextPath }/memberLogout">로그아웃</a>
					</c:otherwise>
				</c:choose>



			</ul></li>

		<li class="nav-heading">Others</li>

		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#others-nav" data-bs-toggle="collapse" href="#">
				<i class="bi bi-menu-button-wide"></i><span>기타메뉴</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
		
			<ul id="others-nav" class="nav-content collapse "
				data-bs-parent="#sidebar-nav">
				<li><a href="${pageContext.request.contextPath }/chatPage">
						<i class="bi bi-circle"></i><span>채팅</span>
				</a></li>

			</ul>
			<ul id="others-nav" class="nav-content collapse "
				data-bs-parent="#sidebar-nav">
				<li><a href="${pageContext.request.contextPath }/chatUserPage">
						<i class="bi bi-circle"></i><span>채팅유저페이지</span>
				</a></li>

			</ul>
			<ul id="others-nav" class="nav-content collapse "
				data-bs-parent="#sidebar-nav">
				<li><a href="${pageContext.request.contextPath }/noticeSendPage">
			<i class="bi bi-circle"></i><span>공지전송</span>
				</a></li>

			</ul>
			
			</li>

		<!-- End members-nav -->

	</ul>

</aside>