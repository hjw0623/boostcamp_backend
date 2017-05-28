package kr.or.connect.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;

import kr.or.connect.AppConfig;
import kr.or.connect.domain.Book;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BookDaoTest {
	@Autowired
	private BookDao dao;
	
	@Test
	public void shouldCount(){
		int count = dao.countBooks();
		System.out.println(count);
	}
	
	@Test
	public void shouldInsertAndSelect() {
		// given
		Book book = new Book("Java 웹개발", "네이버", 342);

		// when
		Integer id = dao.insert(book);

		// then
		Book selected = dao.selectById(id);
		System.out.println(selected);
	//	assertThat(selected.getTitle(), is("Java 웹개발"));
	}

}
