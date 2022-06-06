package in.example.config;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;

import in.example.author.AuthorInfo;
import in.example.book.BookInfo;
import in.example.course.Course;
import in.example.course.CourseInfo;
import in.example.course.CourseMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilityService {
	
	public static List<Course> courses;
	
	public static List<BookInfo> bookInfos;
	
	public static List<AuthorInfo> authorInfos;
	
	public static List<CourseInfo> findCourses() {
		
		log.debug("Service to load all the courses : {}", courses);
		
		CourseMapper curMapper = Mappers.getMapper(CourseMapper.class);
		
		List<CourseInfo> mappedCourses = courses.stream().map(curMapper::toCourseInfo).collect(Collectors.toList());
		
		return mappedCourses;
	}

	public static List<BookInfo> findBooksByCourseId(UUID courseId) {
		
		log.debug("Service to find books by course id : {}", courseId);
		
		List<BookInfo> books = bookInfos.stream().filter(book -> book.getCourseId().equals(courseId)).collect(Collectors.toList());
		
		return books;
	}

	public static List<AuthorInfo> findAuthorsByBook(UUID bookId) {
		log.debug("Service to find authors by book id : {}", bookId);
		
		List<AuthorInfo> authors = authorInfos.stream().filter(author -> {
			List<UUID> bookIds = author.getBooks().stream().map(bk -> bk.getId()).collect(Collectors.toList());
			
			return bookIds.contains(bookId);
		}).collect(Collectors.toList());
		
		return authors;
	}
	
	public static String test(String sr) { return null;}
}
