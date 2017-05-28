package kr.or.connect;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//ApplicationContext에서 관리할 대상 객체라는 것을 알리기 위해 약속된 애너테이션을 붙인다
//AppConfig는 DataSource를 생성하는 책임을 맡는다
@Configuration //D04~5
@ComponentScan //D06
@PropertySource("application.properties") //D07
public class AppConfig {
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {		
 		BasicDataSource dataSource = new BasicDataSource();
 		dataSource.setDriverClassName(driverClassName);
 		dataSource.setUrl(url);
 		dataSource.setUsername(username);
 		dataSource.setPassword(password);
 		return dataSource;
 	}
}
