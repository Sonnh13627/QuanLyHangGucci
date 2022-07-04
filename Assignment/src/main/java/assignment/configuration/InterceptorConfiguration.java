package assignment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import assignment.interceptor.LoggerInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{
	@Autowired
	LoggerInterceptor loggerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor).addPathPatterns("/**").excludePathPatterns("/login/**");
	}
	
	
}