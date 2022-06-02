package in.example.book;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import in.example.author.AuthorInfo;
import in.example.author.AuthorTestData;

@WebMvcTest(controllers = BookController.class)
public class BookResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	public void testFindAuthorsByBook() throws Exception {
		List<AuthorInfo> authors = AuthorTestData.getAuthorInfos(2);
		
		when(bookService.findAuthorsByBook(Mockito.any())).thenReturn(authors);
		
		UUID bookId = UUID.randomUUID();
		
		mockMvc.perform(get("/api/books/" + bookId + "/authors"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(authors.size())));
 	}
}
