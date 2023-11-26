package com.swuProject.secound.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resistry) {
        resistry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // React 애플리케이션의 도메인
                .allowedHeaders("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name()
                )
                .exposedHeaders("Authorization", "RefreshToken", "X-AUTH-TOKEN")
                .allowCredentials(true); // .allowedOriginPatterns("*") 이렇게 와일드 카드로 설정하면 이거 쓰면 에러남 ( 실행 조차  X )
    };



}
