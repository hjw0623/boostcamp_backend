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
	//�������̽��� DataSource���� ���������μ� DataSource�� ����ü��� ��� Ŭ������ ������ ������ �� �ְ�
	//����� DataSource ��ü�� ���� �������� �ʰ� �ܺο��� ���Ե� DataSource�� ����
	public BookDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int countBooks() {
		Map<String, Object> params = Collections.emptyMap();
		
		return jdbc.queryForObject(COUNT_BOOK, params, Integer.class);
		
	}
}