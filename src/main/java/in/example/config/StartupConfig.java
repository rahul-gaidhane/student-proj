package in.example.config;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.example.author.Author;
import in.example.author.AuthorInfo;
import in.example.author.AuthorMapper;
import in.example.author.AuthorRepository;
import in.example.book.Book;
import in.example.book.BookInfo;
import in.example.book.BookMapper;
import in.example.book.BookRepository;
import in.example.course.Course;
import in.example.course.CourseRepository;

@Configuration
public class StartupConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StartupConfig.class);
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Bean
	public List<Course> courses() {
		
		LOGGER.debug("Service to create all courses bean...");
		
		List<Course> courses = courseRepository.findAll();
		
		LOGGER.debug("Number of courses : {}", courses.size());
		
		return courses;
	}
	
	@Bean
	public List<BookInfo> books() {
		
		LOGGER.debug("Service to get all books bean...");
		
		List<Book> books = bookRepository.findAll();
		
		BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
		
		return books.stream().map(bookMapper::toBookInfo).collect(Collectors.toList());
	}
	
	@Bean
	public List<AuthorInfo> authors() {
		
		LOGGER.debug("Service to get all authors bean...");
		
		List<Author> authors = authorRepository.findAll();
		
		return authors.stream().map(AuthorMapper::toAuthorInfo).collect(Collectors.toList());
	}
}
