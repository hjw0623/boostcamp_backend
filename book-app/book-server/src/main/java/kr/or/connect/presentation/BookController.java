package kr.or.connect.presentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.domain.Book;
import kr.or.connect.service.BookService;

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
}
