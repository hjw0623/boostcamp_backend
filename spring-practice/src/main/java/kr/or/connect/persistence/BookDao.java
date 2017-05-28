package kr.or.connect.persistence;

import java.util.Collections;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class BookDao {
	private NamedParameterJdbcTemplate jdbc;
	private static final String COUNT_BOOK = "SELECT COUNT(*) FROM book";
	
	/*
	public BookDao(DriverManagerDataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	*/
	//인터페이스인 DataSource에만 의존함으로서 DataSource의 구현체라면 어느 클래스가 오던지 동작할 수 있게
	//사용할 DataSource 객체를 직접 결정하지 않고 외부에서 주입된 DataSource를 의존
	public BookDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int countBooks() {
		Map<String, Object> params = Collections.emptyMap();
		
		return jdbc.queryForObject(COUNT_BOOK, params, Integer.class);
		
	}
}
