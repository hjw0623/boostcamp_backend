package kr.or.connect.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import kr.or.connect.AppConfig;
import kr.or.connect.domain.Book;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional //해당 클래스가 트랜잭션 안에서 실행되는 것을 표시. 디폴트 정책으로 테스트 메서드가 실행이 끝나는 시점에 트랜잭션을 롤백
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
		Book book = new Book("Java 웹개발","네이버", 342);

		// when
		Integer id = dao.insert(book);

		// then
		Book selected = dao.selectById(id);
		System.out.println(selected);
		assertThat(selected.getTitle(), is("Java 웹개발"));
	}
	
	@Test
	public void shouldDelete(){
		//given
		Book book = new Book("네이버 자바", "네이버", 142);
		Integer id = dao.insert(book);
		
		//when
		int affected = dao.deleteById(id);
		
		//Then
		assertThat(affected, is(1));
	}

}
