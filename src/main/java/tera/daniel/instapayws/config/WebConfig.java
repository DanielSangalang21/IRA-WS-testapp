package tera.daniel.instapayws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // Allow all origins
	                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
	                .allowedHeaders("*"); // Allowed headers
	    }
}
