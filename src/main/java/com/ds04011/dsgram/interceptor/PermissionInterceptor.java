package com.ds04011.dsgram.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{
	/*
	 * **
모달 설정하고 
js 버튼 연동 을 모달 눌렀을 때 뜨는 모달 버튼으로 등록을 하자

모달은 반복문 안에 들어있지않음. 
그래서 모달이 켜질 때에, 즉 모달 연동 버튼을 누를 때에, 
그 게시물에 대한 정보가 담겨있어야 모달에 정보를 넘겨줄 수 있음

근데 이게 타임리프로 안돼, 클라이언트에서 작동해야 함
서버 기준으로 작동할 수 있는 범위가 아니다. 

모달 연동 버튼에, js  클릭이벤트 등록해서, 
모달의 삭제 버튼에 대상 게시글의 post id 를 넘겨줘야 함. 

$("#deleteBtn").data("post-id", postId);
 
코드로 모달의 삭제 버튼에 속성부여완료 

**
아무나 삭제 못하게 방지 하는건 중요 

버튼을 아예 안보이게 하는것 도 가능하지만, 
동시에 api 에서 체크하는 기능도 중요 

session 에서 유저 정보 땡기고, post 아이디 는 넘겨 받아서 일치하는지 
확인하자. 

 return false 로 아예 안되게 하는것도 중요

아이콘 넣고, 그 아이콘 클릭시, 
다른 버튼이 클릭되게 코드를 짜면 아이콘으로 버튼의 역할을 대체가능
그러고 나서 버튼 숨기기

**
권한 구현
비 로그인 시 
여러 접근 제한, 근데 이게 지금은 에러로 뜬다 .
에러로 뜰게 아니라, 로그인페이지로 이동시켜줘야지.

그런데 이런 작동이 여러 페이지에 적용되어야 함.
반복되는 코드, 
반복되면 정리해야겠지?

-- 인터셉터 작동

필터 느낌 

	 * 
	 * */
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		// 로그인이 안된 경우, 여러 페이지에 접근 제어, 
		// 로그인 페이지로 다시 요청한다. 
		
		HttpSession session = request.getSession();
		
		Long userId = (Long)session.getAttribute("userId");
		
		String uri = request.getRequestURI();
		
		if(userId==null) {
			//로그인이 안된 경우

			// 어느 페이지에 접근하려는지 판별
			// /asdf 로 시작하는 url path 는 안돼
			if(uri.startsWith("/asdf")) {
				// 로그인 페이지 리다이렉트 
				response.sendRedirect("/user/view/login");
				
				//요청 진행 막아. 
				return false;			}
			
			
			
			
		} else {
			// 로그인이 된 경우 
			
			if(uri.startsWith("/user")) {
				//로그인이 되어있는데, 굳이 이쪽으로 갈 필요가 없지 
				// 타임라인으로 돌려보내자.
				response.sendRedirect("/post/view/timeline");
				return false;
			}
		}
		return true;
		
	}

}
