package in.example.book;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.example.author.AuthorInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/{bookId}/authors")
	public ResponseEntity<List<AuthorInfo>> findAuthorsByBook(@PathVariable("bookId") UUID bookId) {
		log.debug("Request to find authors by book id : {}", bookId);
		
		List<AuthorInfo> authors = bookService.findAuthorsByBook(bookId);
		
		return new ResponseEntity<List<AuthorInfo>>(authors, HttpStatus.OK);
		
	}
}
