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

import in.example.author.Author;
import in.example.author.AuthorInfo;
import in.example.author.AuthorMapper;
import in.example.author.AuthorRepository;
import in.example.book.Book;
import in.example.book.BookInfo;
import in.example.book.BookMapper;
import in.example.book.BookRepository;
import in.example.course.Course;
import in.example.course.CourseInfo;
import in.example.course.CourseMapper;
import in.example.course.CourseRepository;

@Service
public class UtilityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityService.class);
	
	@Autowired
	private List<Course> courses;
	
	@Autowired
	private List<BookInfo> bookInfos;
	
	@Autowired
	private List<AuthorInfo> authorInfos;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public List<CourseInfo> findCourses() {
		
		LOGGER.debug("Service to load all the courses : {}", this.courses);
		
		loadCourses();
		
		CourseMapper curMapper = Mappers.getMapper(CourseMapper.class);
		
		List<CourseInfo> mappedCourses = courses.stream().map(curMapper::toCourseInfo).collect(Collectors.toList());
		
		return mappedCourses;
	}

	private void loadCourses() {
		
		LOGGER.debug("Service to load courses : {}", this.courses);
		
		if(CollectionUtils.isEmpty(this.courses)) {
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
		
		LOGGER.debug("Service to load books : {}", this.bookInfos);
		
		List<Book> books = new ArrayList<>();
		
		if(CollectionUtils.isEmpty(this.bookInfos)) {
			books = bookRepository.findAll();
			
			BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
			
			this.bookInfos = books.stream().map(bookMapper::toBookInfo).collect(Collectors.toList());
		}
	}
	
	public List<AuthorInfo> findAuthorsByBook(UUID bookId) {
		LOGGER.debug("Service to find authors by book id : {}", bookId);
		
		loadAuthors();
		
		List<AuthorInfo> authors = this.authorInfos.stream().filter(author -> author.getBookIds().contains(bookId)).collect(Collectors.toList());
		
		return authors;
	}

	private void loadAuthors() {
		LOGGER.debug("Service to load authors : {}", this.authorInfos);
		
		if(CollectionUtils.isEmpty(this.authorInfos)) {
			List<Author> authors = authorRepository.findAll();
			
			LOGGER.debug("Number of authors found : {}", authors);
			;
			this.authorInfos = authors.stream().map(AuthorMapper::toAuthorInfo).collect(Collectors.toList());
		}
 		
	}
}
