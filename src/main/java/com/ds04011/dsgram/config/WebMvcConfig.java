package com.ds04011.dsgram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ds04011.dsgram.common.FileManager;
import com.ds04011.dsgram.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PermissionInterceptor())
		.addPathPatterns("/user/**")
		.excludePathPatterns("/user/logout", "/static/**", "/images/**");
	}

}
