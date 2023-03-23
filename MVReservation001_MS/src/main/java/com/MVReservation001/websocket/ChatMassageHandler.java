package com.MVReservation001.websocket;

import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ChatMassageHandler extends TextWebSocketHandler{
	// 웹소켓에 접속한 클라이언트들을 저장할 ArrayList
	private ArrayList<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("웹소켓 - 채팅 페이지 접속");
		System.out.println("접속한 세션 ID : " + session.getId());
		//1. 접속한 클라이언트에 접속성공 메세지 전송
		//session.sendMessage(new TextMessage("채팅 페이지 접속!"));
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("type", "notice"); // (key,데이터) 
		jsonObj.addProperty("msg", session.getId() + "가 접속했습니다.");
		
		//2. 기존에 접속되어 있는 클라이언트 들에게 새 참여자가 접속했다는 메세지 전송
		for(int i=0; i < sessionList.size(); i++) { 
			sessionList.get(i).sendMessage( new TextMessage( new Gson().toJson(jsonObj) ) );
		}
		/*
		 * for(WebSocketSession se : sessionList) { se.sendMessage( new TextMessage(
		 * session.getId() + "가 접속했습니다.") ); }
		 */
		sessionList.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("페이지에서 보낸 메세지 : " + message.getPayload()); 
		// Service - dao - insert 하면 대화내용이 db에 담기게 됨.
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("type", "chatMessage"); // 
		jsonObj.addProperty("sendId", session.getId()); // 
		jsonObj.addProperty("msg", message.getPayload());
		
		for(int i=0; i < sessionList.size(); i++) { 
			if( !session.getId().equals(sessionList.get(i).getId()) ) { 
				// 내가 보낸 메세지면 서버가 나한테 메세지를 보낼 필요 X
			sessionList.get(i).sendMessage( new TextMessage( new Gson().toJson(jsonObj) ) );
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("웹소켓 - 채팅 페이지 접속해제");
		sessionList.remove(session);
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("type", "notice"); // 
		jsonObj.addProperty("msg", session.getId() + "가 접속 해제했습니다.");
		
		for(int i=0; i < sessionList.size(); i++) { 
			sessionList.get(i).sendMessage( new TextMessage( new Gson().toJson(jsonObj) ) );
		}
	}
	
	
}
