package in.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.example.author.AuthorInfo;
import in.example.author.AuthorRepository;
import in.example.author.AuthorTestData;
import in.example.config.UtilityService;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {
	
	private AuthorRepository authorRepository; 
	
	private BookServiceImpl bookService;
	
	@BeforeEach
	public void setup() {
		authorRepository = mock(AuthorRepository.class);
		
		bookService = new BookServiceImpl(authorRepository);
	}
	
	@Test
	public void testFindAuthorsByBook() {
		
		List<AuthorInfo> authorInfos = AuthorTestData.getAuthorInfos(2);
		
		UUID bookId = UUID.randomUUID();
		
		try(MockedStatic<UtilityService> utilities = Mockito.mockStatic(UtilityService.class)) {
			utilities.when(() -> UtilityService.findAuthorsByBook(bookId)).thenReturn(authorInfos);
			
			List<AuthorInfo> authors = bookService.findAuthorsByBook(bookId);
			
			assertNotNull(authors);
			assertFalse(authors.isEmpty());
			assertEquals(authorInfos.size(), authors.size());
		}
	}
}
