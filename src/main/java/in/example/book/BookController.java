package in.example.book;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.example.author.AuthorInfo;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/{bookId}/authors")
	public ResponseEntity<List<AuthorInfo>> findAuthorsByBook(@PathVariable("bookId") UUID bookId) {
		LOGGER.debug("Request to find authors by book id : {}", bookId);
		
		List<AuthorInfo> authors = bookService.findAuthorsByBook(bookId);
		
		return new ResponseEntity<List<AuthorInfo>>(authors, HttpStatus.OK);
		
	}
}
