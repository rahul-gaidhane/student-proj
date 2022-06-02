package in.example.course;

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

import in.example.book.BookInfo;
import in.example.book.BookTestData;
import in.example.config.UtilityService;

@ExtendWith(SpringExtension.class)
public class CourseServiceTest {
	
private UtilityService utilityService;
	
	private CourseServiceImpl courseService;
	
	@BeforeEach
	public void setup() {
		utilityService = mock(UtilityService.class);
		
		courseService = new CourseServiceImpl(utilityService);
	}
	
	@Test
	public void testFindBooksByCourse() {
		
		List<BookInfo> bookInfos = BookTestData.getBookInfos(2);
		
		when(utilityService.findBooksByCourseId(Mockito.any())).thenReturn(bookInfos);
		
		List<BookInfo> books = courseService.findBooksByCourse(UUID.randomUUID());
		
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(bookInfos.size(), books.size());
	}
	
	@Test
	public void testFindAllCourses() {
		
		List<CourseInfo> courses = CourseTestData.getCourseInfos(2);
		
		when(utilityService.findCourses()).thenReturn(courses);
		
		List<CourseInfo> courseInfos = courseService.findAll();
		
		assertNotNull(courseInfos);
		assertFalse(courseInfos.isEmpty());
		assertEquals(courses.size(), courseInfos.size());
	}
}
