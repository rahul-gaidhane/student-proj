package in.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.example.author.Author;
import in.example.author.AuthorRepository;
import in.example.book.Book;
import in.example.book.BookRepository;
import in.example.course.Course;
import in.example.course.CourseRepository;
import in.example.student.Student;
import in.example.student.StudentRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		LOGGER.debug("Service to load data...");
		
		Course course1 = new Course();
		course1.setId(UUID.randomUUID());
		course1.setName("AeroEnginer");
		course1.setStudents(new ArrayList<>());
		
		Course savedCourse1 = courseRepository.save(course1);
		
		Course course2 = new Course();
		course2.setId(UUID.randomUUID());
		course2.setName("DataEnginer");
		course2.setStudents(new ArrayList<>());
		
		Course savedCourse2 = courseRepository.save(course2);
		
		Author aeroAuthor1 = new Author();
		aeroAuthor1.setGender("male");
		aeroAuthor1.setId(UUID.randomUUID());
		aeroAuthor1.setName("Aero Author Book 1");
		
		Author savedAeroAuthor1 = authorRepository.save(aeroAuthor1);
		
		Author aeroAuthor2 = new Author();
		aeroAuthor2.setGender("female");
		aeroAuthor2.setId(UUID.randomUUID());
		aeroAuthor2.setName("Aero Author Book 2");
		
		Author savedAeroAuthor2 = authorRepository.save(aeroAuthor2);
		
		Author dataAuthor1 = new Author();
		dataAuthor1.setGender("male");
		dataAuthor1.setId(UUID.randomUUID());
		dataAuthor1.setName("Data Author Book 1");
		
		Author savedDataAuthor1 = authorRepository.save(dataAuthor1);
		
		Author dataAuthor2 = new Author();
		dataAuthor2.setGender("female");
		dataAuthor2.setId(UUID.randomUUID());
		dataAuthor2.setName("Data Author Book 2");
		
		Author savedDataAuthor2 = authorRepository.save(dataAuthor2);
		
		Book aeroBook1 = new Book();
		aeroBook1.setCourseId(savedCourse1.getId());
		aeroBook1.setDateOfPublish(LocalDate.now());
		aeroBook1.setId(UUID.randomUUID());
		aeroBook1.setName("aeroBook1");
		aeroBook1.setPublicationHouse("Aero Publishers");
		aeroBook1.setAuthors(List.of(savedAeroAuthor1));
		
		bookRepository.save(aeroBook1);
		
		Book aeroBook2 = new Book();
		aeroBook2.setCourseId(savedCourse1.getId());
		aeroBook2.setDateOfPublish(LocalDate.now());
		aeroBook2.setId(UUID.randomUUID());
		aeroBook2.setName("aeroBook2");
		aeroBook2.setPublicationHouse("Aero Not Publishers");
		aeroBook2.setAuthors(List.of(savedAeroAuthor2));
		
		bookRepository.save(aeroBook2);
		
		Book dataBook1 = new Book();
		dataBook1.setCourseId(savedCourse2.getId());
		dataBook1.setDateOfPublish(LocalDate.now());
		dataBook1.setId(UUID.randomUUID());
		dataBook1.setName("dataBook1");
		dataBook1.setPublicationHouse("data Publishers");
		dataBook1.setAuthors(List.of(savedDataAuthor1));
		
		bookRepository.save(dataBook1);
		
		Book dataBook2 = new Book();
		dataBook2.setCourseId(savedCourse2.getId());
		dataBook2.setDateOfPublish(LocalDate.now());
		dataBook2.setId(UUID.randomUUID());
		dataBook2.setName("dataBook2");
		dataBook2.setPublicationHouse("Data Not Publishers");
		dataBook2.setAuthors(List.of(savedDataAuthor2));
		
		bookRepository.save(dataBook2);
		
		Student student1 = new Student();
		student1.setGender("male");
		student1.setName("Person1");
		student1.setCourses(List.of(savedCourse1));
		student1.setId(UUID.randomUUID());
		studentRepository.save(student1);
		
		Student student2 = new Student();
		student2.setGender("male");
		student2.setName("Person2");
		student2.setCourses(List.of(savedCourse2));
		student2.setId(UUID.randomUUID());
		studentRepository.save(student2);
		
		Student student3 = new Student();
		student3.setGender("male");
		student3.setName("Person3");
		student3.setCourses(List.of(savedCourse1));
		student3.setId(UUID.randomUUID());
		studentRepository.save(student3);
		
	}
	
}
