package in.example.course;

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

import in.example.book.BookInfo;
import in.example.book.BookTestData;

@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CourseService courseService;
	
	@Test
	public void testFindBooksByCourse() throws Exception {
		
		List<BookInfo> bookInfos = BookTestData.getBookInfos(2);
		
		when(courseService.findBooksByCourse(Mockito.any())).thenReturn(bookInfos);
		
		mockMvc.perform(get("/api/courses/" + UUID.randomUUID() + "/books"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(bookInfos.size())));
	}
	
	@Test
	public void testFindAllCourses() throws Exception {
		
		List<CourseInfo> courseInfos = CourseTestData.getCourseInfos(2);
		
		when(courseService.findAll()).thenReturn(courseInfos);
		
		mockMvc.perform(get("/api/courses"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(courseInfos.size())));
	}

}
