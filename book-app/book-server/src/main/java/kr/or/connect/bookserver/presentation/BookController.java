package kr.or.connect.bookserver.presentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.bookserver.domain.Book;
import kr.or.connect.bookserver.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookService service;
	
	@Autowired
	public BookController(BookService service){
		this.service = service;
	}
	
	@GetMapping
	Collection<Book> readList(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	Book read(@PathVariable Integer id){
		return service.findById(id);

	}
	//클래스 레벨에서 /api/books에 대한 주소를 받는다고 이전 실습에서 선언.
	// 추가적인 U경로를 지정할 필요없이 @PostMapping만 단순히 붙이면 된다.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Book create(@RequestBody Book book) {
		return service.create(book);
	}
	//update annotation
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody Book book){
		book.setId(id);
		service.update(book);
	}
	
	//delete annotation
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id){
		service.delete(id);
	}
}
