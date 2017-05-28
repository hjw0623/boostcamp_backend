package kr.or.connect;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//ApplicationContext에서 관리할 대상 객체라는 것을 알리기 위해 약속된 애너테이션을 붙인다
//AppConfig는 DataSource를 생성하는 책임을 맡는다
@Configuration
@ComponentScan
public class AppConfig {
	@Bean
	public DataSource dataSource() {
 		BasicDataSource dataSource = new BasicDataSource();
 		dataSource.setDriverClassName("org.h2.Driver");
 		dataSource.setUrl("jdbc:h2:~/javaweb/db;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");
 		dataSource.setUsername("sa");
 		dataSource.setPassword("sa");
 		return dataSource;
 	}
}
