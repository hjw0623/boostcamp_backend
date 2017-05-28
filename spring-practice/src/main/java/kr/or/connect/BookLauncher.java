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
DataSource �� ����
BookDao�� DataSource�� �����ϰ� ����
BookDao.countBooks()�� ȣ���Ͽ� �׽�Ʈ

D06
Component scan�� ���� �Ʒ� 2������ �ڵ����� �Ǿ���.
BookDao�� �����ڿ� DataSource ��ü�� �Ѱܼ� �����ϴ� ����
BookDao�� @Bean �ֳ����̼��� �̿��ؼ� ����ϴ� ����

�ǽ�D07: �Ӽ� ���� ���� 
DB ���� ������ ������ properties ���Ϸ� �и�

�ǽ�D08: 1�� ��ȸ
book ���̺��� id������ ��ȸ�ϴ� ������ �����ϰ� ����� RowMapper�� �̿��ؼ� ���ϴ� ��ü�� ��ȯ
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
