package br.com.daniellima.refundservice;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@SpringBootApplication
public class RefundServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefundServiceApplication.class, args);
	}
	
	@Bean
	FilterRegistrationBean<?> corsFilter(@Value("${tagit.origin:*}") String origin) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(CorsFilter(origin));
		registration.addUrlPatterns("*");
		registration.setName("CorsFilter");
	    registration.setOrder(1);
		return registration;
	}
	
	@Bean(name = "CorsFilter")
	public Filter CorsFilter(String origin) {
	    return new br.com.daniellima.refundservice.filter.CorsFilter(origin);
	}
}
