package kr.or.connect;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp2.BasicDataSource;


import kr.or.connect.persistence.BookDao;


/**
DataSource 를 생성
BookDao에 DataSource를 주입하고 생성
BookDao.countBooks()를 호출하여 테스트
 */
public class BookLauncher {

	public static void main(String[] args) {
		//DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DataSource dataSource = context.getBean(DataSource.class);
		//BasicDataSource dataSource = new BasicDataSource();

		BookDao dao = new BookDao(dataSource);
		int count = dao.countBooks();
		System.out.println(count);
		context.close();
	}

}
