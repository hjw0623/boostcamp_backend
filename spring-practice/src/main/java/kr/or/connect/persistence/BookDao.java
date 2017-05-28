package kr.or.connect.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import kr.or.connect.domain.Book;

@Repository
public class BookDao {
	private NamedParameterJdbcTemplate jdbc;
	private static final String COUNT_BOOK = "SELECT COUNT(*) FROM book";
	//08 query added
	private static final String SELECT_BY_ID = "SELECT id, title, author, pages FROM book where id = :id";
	
	//D10
	private SimpleJdbcInsert insertAction;
	//09 BeanPropertyRowMapper�� RowMapper ���� && ������� ���� �� �ʱ�ȭ�� ���� 
	private RowMapper<Book> rowMapper = BeanPropertyRowMapper.newInstance(Book.class);
	//08 query ȣ���ϴ� selectById() �޼��带 �ۼ�
	public Book selectById(Integer id){

	/*08 lambda	
		RowMapper<Book> rowMapper = (rs, i)->{
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setPages((Integer) rs.getObject("pages"));
			return book;			
		};
	*/
	/* equal to lambda
	 RowMapper<Book> rowMapper = new RowMapper<Book>() {
	 	@Override
		public Book mapRow(ResultSet rs, int i) throws SQLException {
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setPages((Integer) rs.getObject("pages"));
			return book;
		}
	};
	 */
		Map <String, Object>params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(SELECT_BY_ID, params, rowMapper);
	}
	/*
	public BookDao(DriverManagerDataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	*/
	//�������̽��� DataSource���� ���������μ� DataSource�� ����ü��� ��� Ŭ������ ������ ������ �� �ְ�
	//����� DataSource ��ü�� ���� �������� �ʰ� �ܺο��� ���Ե� DataSource�� ����
	public BookDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		//D10
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("book")
			.usingGeneratedKeyColumns("id");
	}
	//D10
	public Integer insert(Book book){
		SqlParameterSource params = new  BeanPropertySqlParameterSource(book);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	public int countBooks() {
		Map<String, Object> params = Collections.emptyMap();
		
		return jdbc.queryForObject(COUNT_BOOK, params, Integer.class);
		
	}
}
