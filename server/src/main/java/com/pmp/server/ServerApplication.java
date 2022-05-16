package com.pmp.server;

import com.joutvhu.dynamic.jpa.support.DynamicJpaRepositoryFactoryBean;
import com.pmp.server.configuration.FileStorageProperties;
import org.hibernate.Cache;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJpaAuditing // so that it will generate code in base entity
@EnableJpaRepositories(repositoryFactoryBeanClass = DynamicJpaRepositoryFactoryBean.class)
public class ServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	//Creating bean keycloakConfigResolver
	@Bean
	public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/target/static/").setCacheControl(CacheControl.noCache());

		WebMvcConfigurer.super.addResourceHandlers(registry);
	}


}
