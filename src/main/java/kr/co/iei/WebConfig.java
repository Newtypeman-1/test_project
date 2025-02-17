package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//스프링부트 설정파일임을 나타내는 어노테이션
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Value(value="${file.root}")
	private String root;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//너가 가지고 있는 것 중에 이 폴더를 찾아봐
		registry
			.addResourceHandler("/**")//*이 두개면 모든 폴더를 찾음
			.addResourceLocations("classpath:/templates/", "classpath:/static");
		
		registry
			.addResourceHandler("/photo/**")
			.addResourceLocations("file:///"+root+"/photo/");
		
		registry
			.addResourceHandler("/notice/editor/**")
			.addResourceLocations("file:///"+root+"/notice/editor/");
	}
}