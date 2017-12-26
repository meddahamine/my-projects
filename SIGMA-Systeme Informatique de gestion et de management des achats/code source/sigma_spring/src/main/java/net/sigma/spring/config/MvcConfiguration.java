package net.sigma.spring.config;

import javax.sql.DataSource;

import net.sigma.spring.dao.AdminDAO;
import net.sigma.spring.dao.ArchivesDAO;
import net.sigma.spring.dao.EntiteDAO;
import net.sigma.spring.dao.FournisseurDAO;
import net.sigma.spring.dao.IAdminDAO;
import net.sigma.spring.dao.IArchivesDAO;
import net.sigma.spring.dao.IEntiteDAO;
import net.sigma.spring.dao.IFournisseurDAO;

import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.dao.UserDAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="net.sigma.spring")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sigmadb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames(new String[] {"validation"});
		return resourceBundleMessageSource;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		//set max upload per file is 20 Mbs
		commonsMultipartResolver.setMaxUploadSize(20*1024*1024);
		return commonsMultipartResolver;
	}
	
	@Bean
	public IFournisseurDAO getFournisseurDAO() {
		return new FournisseurDAO(getDataSource());
	}
	
	@Bean
	public IUserDAO getUserDAO() {
		return new UserDAO(getDataSource());
	}
	
	@Bean
	public IAdminDAO getAdminDAO() {
		return new AdminDAO(getDataSource());
	}
	
	@Bean
	public IArchivesDAO getArchivesDAO() {
		return new ArchivesDAO(getDataSource());
	}
	
	@Bean
	public IEntiteDAO getEntiteDAO() {
		return new EntiteDAO(getDataSource());
	}
}
