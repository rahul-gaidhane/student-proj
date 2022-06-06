package in.example.course;

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

import in.example.book.BookInfo;
import in.example.book.BookRepository;
import in.example.book.BookTestData;
import in.example.config.UtilityService;

@ExtendWith(SpringExtension.class)
public class CourseServiceTest {
	
	private CourseRepository courseRepository;
	
	private BookRepository bookRepository;
	
	private CourseServiceImpl courseService;
	
	@BeforeEach
	public void setup() {
		courseRepository = mock(CourseRepository.class);
		
		bookRepository = mock(BookRepository.class);
		
		courseService = new CourseServiceImpl(courseRepository, bookRepository);
	}
	
	@Test
	public void testFindBooksByCourse() {
		
		List<BookInfo> bookInfos = BookTestData.getBookInfos(2);
		
		UUID courseId = UUID.randomUUID();
		
		try(MockedStatic<UtilityService> utilities = Mockito.mockStatic(UtilityService.class)) {
			
			utilities.when(() -> UtilityService.findBooksByCourseId(courseId)).thenReturn(bookInfos);
			
			List<BookInfo> books = courseService.findBooksByCourse(courseId);
			
			assertNotNull(books);
			assertFalse(books.isEmpty());
			assertEquals(bookInfos.size(), books.size());
		}
	}
	
	@Test
	public void testFindAllCourses() {
		
		List<CourseInfo> courses = CourseTestData.getCourseInfos(2);
		
		try(MockedStatic<UtilityService> utilities = Mockito.mockStatic(UtilityService.class)) {
			
			utilities.when(UtilityService::findCourses).thenReturn(courses);
			
			List<CourseInfo> courseInfos = courseService.findAll();
			
			assertNotNull(courseInfos);
			assertFalse(courseInfos.isEmpty());
			assertEquals(courses.size(), courseInfos.size());
		}
		
	}
}
