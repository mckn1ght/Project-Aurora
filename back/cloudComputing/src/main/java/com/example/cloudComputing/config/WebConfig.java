package com.example.cloudComputing.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:4200")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
      .allowedHeaders("*")
      .allowCredentials(true);
  }


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // This path should be the absolute path to your files
    String absolutePath = "file:/Users/robertcostache/ProjectAurora/back/cloudComputing/uploads/avatars/";
    System.out.println("Serving files from: " + absolutePath);

    registry.addResourceHandler("/uploads/avatars/**")
      .addResourceLocations(absolutePath);
    registry.addResourceHandler("/uploads/jobs/**")
      .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/jobs/");

  }

}
