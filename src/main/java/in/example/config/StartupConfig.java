package in.example.config;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StartupConfig {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Bean
	public List<Course> courses() {
		
		log.debug("Service to create all courses bean...");
		
		List<Course> courses = courseRepository.findAll();
		
		log.debug("Number of courses : {}", courses.size());
		
		return courses;
	}
	
	@Bean
	public List<BookInfo> books() {
		
		log.debug("Service to get all books bean...");
		
		List<Book> books = bookRepository.findAll();
		
		BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
		
		return books.stream().map(bookMapper::toBookInfo).collect(Collectors.toList());
	}
	
	@Bean
	public List<AuthorInfo> authors() {
		
		log.debug("Service to get all authors bean...");
		
		List<Author> authors = authorRepository.findAll();
		
		AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);
		
		return authors.stream().map(authorMapper::toAuthorInfo).collect(Collectors.toList());
	}
}
