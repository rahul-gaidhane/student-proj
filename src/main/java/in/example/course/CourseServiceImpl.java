package in.example.course;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.book.Book;
import in.example.book.BookInfo;
import in.example.book.BookMapper;
import in.example.book.BookRepository;
import in.example.config.UtilityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepository;
	
	private BookRepository bookRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, BookRepository bookRepository) {
		this.courseRepository = courseRepository;
		this.bookRepository = bookRepository;
	}
	
	@PostConstruct
	public void postBean() {
		
		log.debug("Service to perform operations on course service bean creation...");
		
		List<Course> courses = courseRepository.findAll();
		
		log.debug("Number of courses : {}", courses.size());
		
		UtilityService.courses = courses;
		
		List<Book> books = bookRepository.findAll();
		
		BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
		
		UtilityService.bookInfos = books.stream().map(bookMapper::toBookInfo).collect(Collectors.toList());
	}
	
	
	@Override
	public List<CourseInfo> findAll() {
		log.debug("Service to find all courses...");
		
		List<CourseInfo> courses = UtilityService.findCourses();
		
		return courses;
	}

	@Override
	public List<BookInfo> findBooksByCourse(UUID courseId) {
		log.debug("Service to find books by course id : {}", courseId);
		
		List<BookInfo> books = UtilityService.findBooksByCourseId(courseId);
		
		return books;
	}
}
