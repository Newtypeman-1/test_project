package kr.co.iei.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("member");
		Doctor d = (Doctor) session.getAttribute("doctor");

		if(m == null && d == null) {
			response.sendRedirect("/member/loginMsg");
			return false;
		}else if(m == null){
			response.sendRedirect("/member/loginMsg");
			return false;
		}else if(d.getDoctorId() != "admin"){
			response.sendRedirect("/doctor/loginMsg");
			return false;
		}else {
			return true;
		}
		
	}
	
}
