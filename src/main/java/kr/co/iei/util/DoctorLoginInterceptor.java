package kr.co.iei.util;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.iei.doctor.model.vo.Doctor;

public class DoctorLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		
		if(d == null){
			response.sendRedirect("/doctor/loginMsg");
			return false;
		}else {
			return true;
		}
	}

}
