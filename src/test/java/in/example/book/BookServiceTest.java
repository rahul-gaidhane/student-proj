package in.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.example.author.AuthorInfo;
import in.example.author.AuthorTestData;
import in.example.config.UtilityService;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {

	private UtilityService utilityService;
	
	private BookServiceImpl bookService;
	
	@BeforeEach
	public void setup() {
		utilityService = mock(UtilityService.class);
		
		bookService = new BookServiceImpl(utilityService);
	}
	
	@Test
	public void testFindAuthorsByBook() {
		
		List<AuthorInfo> authorInfos = AuthorTestData.getAuthorInfos(2);
		
		when(utilityService.findAuthorsByBook(Mockito.any())).thenReturn(authorInfos);
		
		List<AuthorInfo> authors = bookService.findAuthorsByBook(UUID.randomUUID());
		
		assertNotNull(authors);
		assertFalse(authors.isEmpty());
		assertEquals(authorInfos.size(), authors.size());
	}
	
}
