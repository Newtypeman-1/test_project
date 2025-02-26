package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.iei.util.LoginInterceptor;

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
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/member/mypage")
				.addPathPatterns("/member/delete")
				.addPathPatterns("/member/myMedicalRecordsPage")
				.addPathPatterns("/member/myOpinion")
				.addPathPatterns("/member/qna")
				.addPathPatterns("/member/update")
				.addPathPatterns("/member/logout")
				
				.addPathPatterns("treat/appointFrm")
				
				.addPathPatterns("/board/writeFrm")
				.addPathPatterns("/board/delete")
				.addPathPatterns("/board/commentWrite")
				
				.addPathPatterns("/admin/addAcount")
				.addPathPatterns("/admin/delAcount")
				.addPathPatterns("/admin/allSchedule")
				.addPathPatterns("/admin/allMember");
	}
	
	
}