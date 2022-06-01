package in.example.course;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.book.Book;
import in.example.book.BookInfo;
import in.example.book.BookMapper;
import in.example.config.UtilityService;

@Service
public class CourseServiceImpl implements CourseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private UtilityService utilityService;
	
	@Override
	public List<CourseInfo> findAll() {
		LOGGER.debug("Service to find all courses...");
		
		List<Course> courses = utilityService.findCourses();
		
		List<CourseInfo> mappedCourses = courses.stream().map(CourseMapper::toCourseInfo).collect(Collectors.toList());
		
		return mappedCourses;
	}

	@Override
	public List<BookInfo> findBooksByCourse(UUID courseId) {
		LOGGER.debug("Service to find books by course id : {}", courseId);
		
		List<Book> books = utilityService.findBooksByCourseId(courseId);
		
		List<BookInfo> mappedBooks = books.stream().map(BookMapper::toBookInfo).collect(Collectors.toList());
		
		return mappedBooks;
	}
	
	
}
