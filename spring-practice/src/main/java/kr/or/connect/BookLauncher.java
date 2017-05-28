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

import kr.or.connect.domain.Book;
import kr.or.connect.persistence.BookDao;


/**
D05
DataSource 를 생성
BookDao에 DataSource를 주입하고 생성
BookDao.countBooks()를 호출하여 테스트

D06
Component scan에 의해 아래 2과정이 자동으로 되었다.
BookDao의 생성자에 DataSource 객체를 넘겨서 주입하는 과정
BookDao를 @Bean 애너테이션을 이용해서 등록하는 과정

실습D07: 속성 관리 도입 
DB 접속 정보를 별도의 properties 파일로 분리

실습D08: 1건 조회
book 테이블을 id값으로 조회하는 쿼리를 실행하고 결과를 RowMapper를 이용해서 원하는 객체로 변환
 */
public class BookLauncher {

	public static void main(String[] args) {
		//DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		//DataSource dataSource = context.getBean(DataSource.class);
		//BasicDataSource dataSource = new BasicDataSource();

		//BookDao dao = new BookDao(dataSource);
		BookDao dao = context.getBean(BookDao.class);
		int count = dao.countBooks();
		System.out.println(count);
		
		//08 query call
		Book book = dao.selectById(1);
		System.out.println(book);
		context.close();
	}

}
