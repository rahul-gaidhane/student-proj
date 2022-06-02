package in.example.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import in.example.book.Book;
import in.example.book.BookInfo;
import in.example.book.BookMapper;
import in.example.book.BookRepository;
import in.example.course.Course;
import in.example.course.CourseRepository;

@Service
public class UtilityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityService.class);
	
	@Autowired
	private List<Course> courses;
	
	@Autowired
	private List<BookInfo> bookInfos;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Course> findCourses() {
		
		LOGGER.debug("Service to load all the courses...");
		
		loadCourses();
		
		return courses;
	}

	private void loadCourses() {
		
		LOGGER.debug("Service to load courses : {}", courses);
		
		if(CollectionUtils.isEmpty(courses)) {
			this.courses = courseRepository.findAll();
		}
	}

	public List<BookInfo> findBooksByCourseId(UUID courseId) {
		
		LOGGER.debug("Service to find books by course id : {}", courseId);
		
		loadBooks();
		
		List<BookInfo> books = bookInfos.stream().filter(book -> book.getCourseId().equals(courseId)).collect(Collectors.toList());
		
		return books;
	}

	private void loadBooks() {
		
		LOGGER.debug("Service to load books : {}", bookInfos);
		
		List<Book> books = new ArrayList<>();
		
		if(CollectionUtils.isEmpty(bookInfos)) {
			books = bookRepository.findAll();
		}
		
		BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
		
		bookInfos = books.stream().map(bookMapper::toBookInfo).collect(Collectors.toList());
	}
}
