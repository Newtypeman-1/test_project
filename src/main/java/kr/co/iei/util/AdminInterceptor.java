package kr.co.iei.util;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.iei.doctor.model.vo.Doctor;

public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		System.out.println(d.getDoctorId());
		if(d != null && d.getDoctorId().equals("admin")) {
			return true;
		}else {
			response.sendRedirect("/doctor/adminMsg");
			return false;
		}
	}

}
