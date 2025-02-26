package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.iei.util.AdminInterceptor;
import kr.co.iei.util.DoctorLoginInterceptor;
import kr.co.iei.util.MemberLoginInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Value(value="${file.root}")
	private String root;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/**")//*이 두개면 모든 폴더를 찾음
			.addResourceLocations("classpath:/templates/", "classpath:/static");
		
		registry
			.addResourceHandler("/doctor/**")
			.addResourceLocations("file:///"+root+"/doctor/");
		
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new MemberLoginInterceptor())
				.addPathPatterns("/member/mypage")
				.addPathPatterns("/member/delete")
				.addPathPatterns("/member/myMedicalRecordsPage")
				.addPathPatterns("/member/myOpinion")
				.addPathPatterns("/member/qna")
				.addPathPatterns("/member/update")
				.addPathPatterns("/member/logout")
				
				.addPathPatterns("treat/appointFrm")
				.addPathPatterns("/treat/appoint")
				
				.addPathPatterns("review/writeFrm")
				.addPathPatterns("review/write")
				
				.addPathPatterns("/board/writeFrm")
				.addPathPatterns("/board/write")
				.addPathPatterns("/board/delete");
		
		registry.addInterceptor(new DoctorLoginInterceptor())
				.addPathPatterns("/board/commentWriteFrm")
				.addPathPatterns("/board/commentWrite")
				
//				.addPathPatterns("/doctor/**")
//				.excludePathPatterns("/doctor/login",
//									"/doctor/findIdPwFrm",
//									"/doctor/findId",
//									"/doctor/findPw",
//									"/doctor/loginMsg");
				
				.addPathPatterns("/doctor/logout")
				.addPathPatterns("/doctor/mypage")
				.addPathPatterns("/doctor/update")
				.addPathPatterns("/doctor/qna")
				.addPathPatterns("/doctor/myMedicalRecordsPageFrm")
				.addPathPatterns("/doctor/MyOpinion")
				.addPathPatterns("/doctor/opinionSubmit");
				
		
		registry.addInterceptor(new AdminInterceptor())
				.addPathPatterns("/admin/**");
	}
	
	
}