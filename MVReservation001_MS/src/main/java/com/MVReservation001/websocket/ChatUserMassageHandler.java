package com.MVReservation001.websocket;

import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ChatUserMassageHandler extends TextWebSocketHandler{
	// 웹소켓에 접속한 클라이언트들을 저장할 ArrayList
		private ArrayList<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
		
		// 채팅페이지 접속 했을 때
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("웹소켓 - 채팅페이지 접속");
			sessionList.add(session); // 새로 접속한 회원(세션)을 클라이언트 세션목록에 추가
			
			// 처리타입, 아이디, 출력메세지
			String loginId = (String)session.getAttributes().get("loginId"); // 로그인된 회원아이디
			/* WebSocket의 session.getAttributes : 세션의 모든 애트리뷰트를 가져오기 때문에 그중에 loginId를 get해야함 */
			JsonObject sendData = new JsonObject();
			sendData.addProperty("type", "connectUser"); 				// sock.onmessage 이벤트로 처리할 타입
			sendData.addProperty("userid", loginId);					// 새로 접속한 아이디
			sendData.addProperty("sendmsg", " 회원이 입장 했습니다.");  // 채팅창에 출력할 메세지
			Gson gson = new Gson();
			String sendData_json = gson.toJson(sendData);
			
			for(WebSocketSession ConnectSession : sessionList) { /* for(int i=0; i<sessionList.size();i++) {} */
				if( !session.getId().equals( ConnectSession.getId() ) ) { // 지금 접속한 세션id와 원래 있던 사람들의 세션id가 다른경우
					// 이전에 접속중인 클라이언트들에게 '새로운 클라이언트 입장' 정보 전송
					//ConnectSession.sendMessage(new TextMessage( new Gson().toJson(sendData) ));
					ConnectSession.sendMessage( new TextMessage( sendData_json ) );
					
				}
			}
			// 새로 접속한 클라이언트에게 '이전에 접속중인 클라이언트 목록' 전송 :: json들의 리스트
			JsonArray userList = new JsonArray(); // ArrayList<MemberDto> memberList = new ArrayList<MemberDto>(); 와 같다고 보면 된다.
												  // memberList.add(member);
			for(WebSocketSession connectSession : sessionList) {
				JsonObject userInfo = new JsonObject(); // 한명의 회원 정보를 담을 객체
				String UserLoginId = (String)connectSession.getAttributes().get("loginId"); // 접속한 회원의 아이디
				userInfo.addProperty("userid", UserLoginId);
				// userInfo.addProperty("userProfile", "프로필파일명");
				userList.add(userInfo); // 모아진 회원정보를 목록에 추가
			}
			//userList >> 접속중인 회원 정보 목록
			
			JsonObject sendUserList = new JsonObject();
			sendUserList.addProperty("type", "connectUserList"); // 처리방식
			sendUserList.add("userList", userList); // 보내줄 데이터
			String sendUserList_json = gson.toJson(sendUserList);
			session.sendMessage( new TextMessage( sendUserList_json ) ); // 전송
			// session.sendMessage( new TextMessage( new Gson().toJson(sendUserList) ) ); // 한줄로 줄임
			
			
			}
			
			
		
		// 채팅페이지 접속해제 했을 때
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			System.out.println("웹소켓 - 채팅 페이지 접속해제");
			sessionList.remove(session); // 접속해제한 회원(세션)을 클라이언트 세션목록에서 삭제
			/* 접속중인 클라이언트들에게 퇴장 정보를 전송 */
			String loginId = (String)session.getAttributes().get("loginId"); // 로그인된 회원아이디
			JsonObject sendData = new JsonObject();
			sendData.addProperty("type", "disconnectUser"); 			// sock.onmessage 이벤트로 처리할 타입
			sendData.addProperty("userid", loginId);					// 퇴장아이디
			sendData.addProperty("sendmsg", " 회원이 퇴장 했습니다.");  // 채팅창에 출력할 메세지
			Gson gson = new Gson();
			String sendData_json = gson.toJson(sendData);
			for(WebSocketSession ConnectSession : sessionList) { /* for(int i=0; i<sessionList.size();i++) {} */
				// 접속중인 클라이언트들에게 퇴장정보를 전송 (이미 나간 상태이므로 나인지 확인하는 if문 필요없음)
				ConnectSession.sendMessage( new TextMessage( sendData_json ) );
			}
			
			
			
			
			
		}
		
		
		// 메세지 전송 했을 때
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("type", "chatMessage");
			jsonObj.addProperty("sendid", session.getId());
			jsonObj.addProperty("msg", message.getPayload());
			for(int i = 0; i < sessionList.size(); i++) {
				if(!session.getId().equals(sessionList.get(i).getId())) {
					sessionList.get(i).sendMessage(new TextMessage(new Gson().toJson(jsonObj)));
				}
			}
			
			

		}
		

	
}
